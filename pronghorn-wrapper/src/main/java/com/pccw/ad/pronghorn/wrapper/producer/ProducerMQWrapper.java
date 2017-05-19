package com.pccw.ad.pronghorn.wrapper.producer;


import com.pccw.ad.pronghorn.message.Message;
import com.pccw.ad.pronghorn.wrapper.MQWrapper;

/**
 * Created by FaustineP on 28/03/2017.
 */
public abstract class ProducerMQWrapper implements MQWrapper {


    private static boolean sendMessage(String channel, Message message) {
        return true;
    }
}
