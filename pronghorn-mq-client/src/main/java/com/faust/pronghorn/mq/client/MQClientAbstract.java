package com.faust.pronghorn.mq.client;


import com.faust.pronghorn.common.exception.MessageSerializerException;
import com.faust.pronghorn.common.message.serializer.MessageSerializer;
import com.faust.pronghorn.message.AbstractMessage;
import com.faust.pronghorn.mq.client.exception.MQClientException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * Created by FaustineP on 10/05/2017.
 */
public abstract class MQClientAbstract<T extends AbstractMessage> implements IMQClient<T> {

    private static Properties MQ_PROP = null;
    private static ConnectionFactory factory = null;
    private static Connection connection = null;
    private static Channel channel = null;
    private MessageSerializer<T> messageSerializer = new MessageSerializer();

    public MQClientAbstract(String configFileProp) throws MQClientException, IOException, TimeoutException {
        loadConfigFile(configFileProp);
        initializeConnection();
    }

    @Override
    public void send(T message, String queue) throws IOException, MessageSerializerException {
        channel.queueDeclare(queue, true, false, false, null);
        String jsonMessage = messageSerializer.serialize(message);
        channel.basicPublish("", queue, null, jsonMessage.getBytes());
    }

    @Override
    public T receive(String queue, Class<T> clazz) throws IOException, TimeoutException, MessageSerializerException {
        GetResponse response = channel.basicGet(queue, true);
        if (response == null) return null;
        return messageSerializer.deserialize(response.getBody(), clazz);
    }

    private static void loadConfigFile(String configFileProp) throws MQClientException, IOException {
        if (configFileProp == null || configFileProp.isEmpty())
            throw new MQClientException("Property file name must not be null");
        if (!new File(configFileProp).exists())
            throw new MQClientException("Property file " + configFileProp + " does not exists!");
        if (MQ_PROP == null) {
            MQ_PROP = new Properties();
            MQ_PROP.load(new FileInputStream(configFileProp));
        }
    }

    private static void initializeConnection() throws IOException, TimeoutException {
        if (factory == null) {
            factory = new ConnectionFactory();
            factory.setHost(MQ_PROP.getProperty(KEY_PROP_MQ_HOST));
            factory.setPort(Integer.parseInt(MQ_PROP.getProperty(KEY_PROP_MQ_PORT).trim()));
            factory.setUsername(MQ_PROP.getProperty(KEY_PROP_MQ_USER));
            factory.setPassword(MQ_PROP.getProperty(KEY_PROP_MQ_PASSWORD));
        }
        if (connection == null) {
            connection = factory.newConnection();
            channel = connection.createChannel();
        }
    }

    @Override
    public void closeConnection() throws IOException, TimeoutException {
        if (connection != null) {
            channel.close();
            connection.close();
        }
    }
}
