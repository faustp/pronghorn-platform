package com.faust.pronghorn.mq.client;

import com.faust.pronghorn.common.exception.MessageSerializerException;
import com.faust.pronghorn.message.AbstractMessage;
import com.faust.pronghorn.mq.client.exception.MQClientException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by FaustineP on 10/05/2017.
 */
public final class MQClient<T extends AbstractMessage> extends MQClientAbstract<T> {


    public MQClient(String configFileProp) throws MQClientException, IOException, TimeoutException {
        super(configFileProp);
    }

    @Override
    public void send(T message, String queue) throws IOException, MessageSerializerException {
        super.send(message, queue);
    }

    @Override
    public T receive(String queue, Class<T> clazz) throws IOException, TimeoutException, MessageSerializerException {
        return super.receive(queue, clazz);
    }
}
