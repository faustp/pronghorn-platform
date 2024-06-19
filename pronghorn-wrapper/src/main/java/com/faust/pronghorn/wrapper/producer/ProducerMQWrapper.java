package com.faust.pronghorn.wrapper.producer;


import com.faust.pronghorn.message.Message;
import com.faust.pronghorn.wrapper.MQWrapper;

/**
 * Created by FaustineP on 28/03/2017.
 */
public abstract class ProducerMQWrapper implements MQWrapper {


    private static boolean sendMessage(String channel, Message message) {
        return true;
    }
}
