package com.pccw.ad.pronghorn.wrapper.consumer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.ad.pronghorn.message.Message;
import com.pccw.ad.pronghorn.mq.client.IMQClient;
import com.pccw.ad.pronghorn.mq.client.MQClient;
import com.pccw.ad.pronghorn.mq.client.exception.MQClientException;
import com.pccw.ad.pronghorn.wrapper.MQWrapper;
import com.pccw.ad.pronghorn.wrapper.exception.MQWrapperException;
import com.pccw.ad.pronghorn.wrapper.profile.MQExchange;
import com.rabbitmq.client.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * Created by FaustineP on 28/03/2017.
 */
public abstract class MQWrapperAbs implements MQWrapper {


    private static ConnectionFactory factory = null;
    private static MQClient imqClient;
    private static Connection connection = null;
    private static Channel channel = null;
    public static boolean FORCE_SHUTDOWN;
    protected static MQExchange MQ_EXCHANGE;
    static protected Properties MQ_PROP = new Properties();


    public MQWrapperAbs(String name) throws IOException, MQWrapperException, TimeoutException, MQClientException {
        loadMQProperties(name);
        setMQExchange();
        initialize();
        imqClient = new MQClient("");
    }

    protected static void loadMQProperties(String name) throws IOException, MQWrapperException {
        if (name == null) throw new MQWrapperException("Property file name must not be null");
        String configPath = MQ_CONFIG_PROP_PATH.concat(File.separator).concat(name);
        if (!new File(configPath).exists())
            throw new MQWrapperException("Property file " + configPath + " does not exists!");
        FileInputStream fis = new FileInputStream(configPath);
        MQ_PROP.load(fis);
    }

    private static void setMQExchange() throws MQWrapperException {
        MQ_EXCHANGE = new MQExchange();
        if (MQ_PROP.getProperty(KEY_PROP_MQ_HOST) == null || MQ_PROP.getProperty(KEY_PROP_MQ_HOST).isEmpty())
            throw new MQWrapperException("Value for key property rabbit.mq.server.host cannot be null or empty");
        if (MQ_PROP.getProperty(KEY_PROP_MQ_USER) == null || MQ_PROP.getProperty(KEY_PROP_MQ_USER).isEmpty())
            throw new MQWrapperException("Value for key property rabbit.mq.user cannot be null or empty");
        if (MQ_PROP.getProperty(KEY_PROP_MQ_PASSWORD) == null || MQ_PROP.getProperty(KEY_PROP_MQ_PASSWORD).isEmpty())
            throw new MQWrapperException("Value for key property rabbit.mq.password cannot be null or empty");
        if (MQ_PROP.getProperty(KEY_PROP_MQ_CHANNEL_IN) == null || MQ_PROP.getProperty(KEY_PROP_MQ_CHANNEL_IN).isEmpty())
            throw new MQWrapperException("Value for key property rabbit.mq.source cannot be null or empty");
        if (MQ_PROP.getProperty(KEY_PROP_MQ_CHANNEL_OUT) == null || MQ_PROP.getProperty(KEY_PROP_MQ_CHANNEL_OUT).isEmpty())
            throw new MQWrapperException("Value for key property rabbit.mq.destination cannot be null or empty");
        MQ_EXCHANGE.setHost(MQ_PROP.getProperty(KEY_PROP_MQ_HOST));
        MQ_EXCHANGE.setPort(MQ_PROP.getProperty(KEY_PROP_MQ_PORT));
        MQ_EXCHANGE.setUsername(MQ_PROP.getProperty(KEY_PROP_MQ_USER));
        MQ_EXCHANGE.setPassword(MQ_PROP.getProperty(KEY_PROP_MQ_PASSWORD));
        MQ_EXCHANGE.setSource(MQ_PROP.getProperty(KEY_PROP_MQ_CHANNEL_IN));
        MQ_EXCHANGE.setDest(MQ_PROP.getProperty(KEY_PROP_MQ_CHANNEL_OUT));
    }

    protected static void initialize() throws IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setHost(MQ_EXCHANGE.getHost());
        factory.setUsername(MQ_EXCHANGE.getUsername());
        factory.setPassword(MQ_EXCHANGE.getPassword());
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public static Message getMessage(String queue) throws IOException, InterruptedException {
        GetResponse response = channel.basicGet(queue, true);
        if (response == null) return null;
        String jsonMessage = new String(response.getBody());
        return new ObjectMapper().readValue(jsonMessage, Message.class);
    }

    public static void sendMessage(Message message, String queue) throws IOException {
        channel.queueDeclare(queue, true, false, false, null);
        String jsonMessage = new ObjectMapper().writeValueAsString(message);
        channel.basicPublish("", queue, null, jsonMessage.getBytes());
        System.out.println("Message [" + message.getUuid() + "] sent to ->" + queue);
    }


    public static void main(String args[]) {
        // Setup consumer properties
        // Consume Message
        try {
            MQWrapper consumer = new ConsumerImpl("consumer.properties");
            while (!FORCE_SHUTDOWN) {
                Message message = getMessage(MQ_EXCHANGE.getSource());
                if (message == null) continue;
                consumer.doWork(message);
            }
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
