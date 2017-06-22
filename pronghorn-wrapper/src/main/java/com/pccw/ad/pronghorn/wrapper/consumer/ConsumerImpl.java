package com.pccw.ad.pronghorn.wrapper.consumer;


import com.pccw.ad.pronghorn.engine.IEngine;
import com.pccw.ad.pronghorn.engine.PronghornEngine;
import com.pccw.ad.pronghorn.message.Message;
import com.pccw.ad.pronghorn.model.exception.ProfileException;
import com.pccw.ad.pronghorn.mq.client.exception.MQClientException;
import com.pccw.ad.pronghorn.wrapper.exception.MQWrapperException;
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
