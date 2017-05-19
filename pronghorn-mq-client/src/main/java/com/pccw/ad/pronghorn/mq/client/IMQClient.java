package com.pccw.ad.pronghorn.mq.client;

import com.pccw.ad.pronghorn.common.exception.MessageSerializerException;
import com.pccw.ad.pronghorn.message.AbstractMessage;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by FaustineP on 10/05/2017.
 */
public interface IMQClient<T extends AbstractMessage> {

    String KEY_PROP_MQ_HOST = "rabbit.mq.server.host";
    String KEY_PROP_MQ_PORT = "rabbit.mq.server.port";
    String KEY_PROP_MQ_USER = "rabbit.mq.user";
    String KEY_PROP_MQ_PASSWORD = "rabbit.mq.password";


    /**
     * @param message Message to be sent to MQ
     */
    void send(T message, String queue) throws IOException, MessageSerializerException;

    /**
     * @param queue Queue name where to get the message
     * @return Message from MQ broker
     */
    T receive(String queue,Class<T> clazz) throws IOException, TimeoutException, MessageSerializerException;

    void closeConnection() throws IOException, TimeoutException;
}
