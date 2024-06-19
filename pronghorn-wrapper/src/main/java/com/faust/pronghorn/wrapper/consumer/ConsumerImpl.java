package com.faust.pronghorn.wrapper.consumer;


import com.faust.pronghorn.engine.IEngine;
import com.faust.pronghorn.engine.PronghornEngine;
import com.faust.pronghorn.message.Message;
import com.faust.pronghorn.model.exception.ProfileException;
import com.faust.pronghorn.mq.client.exception.MQClientException;
import com.faust.pronghorn.wrapper.exception.MQWrapperException;
import org.sikuli.script.FindFailed;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeoutException;

/**
 * Created by FaustineP on 28/03/2017.
 */
public class ConsumerImpl extends MQWrapperAbs {


    public ConsumerImpl(String name) throws IOException, MQWrapperException, TimeoutException, MQClientException {
        super(name);
    }

    @Override
    public Message doWork(Object message) {
        IEngine pronghornEngine = new PronghornEngine((Message) message);
        try {
            pronghornEngine.execute();
        } catch (ProfileException | InvocationTargetException | IllegalAccessException | FindFailed | IOException e) {
            e.printStackTrace();
        }
        return (Message) message;
    }
}
