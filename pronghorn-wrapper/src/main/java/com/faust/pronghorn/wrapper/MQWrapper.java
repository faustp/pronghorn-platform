package com.faust.pronghorn.wrapper;


import com.faust.pronghorn.message.Message;

import java.io.File;

/**
 * Created by FaustineP on 28/03/2017.
 */
public interface MQWrapper {

    String KEY_PROP_MQ_HOST = "rabbit.mq.server.host";
    String KEY_PROP_MQ_PORT = "rabbit.mq.server.port";
    String KEY_PROP_MQ_USER = "rabbit.mq.user";
    String KEY_PROP_MQ_PASSWORD = "rabbit.mq.password";
    String KEY_PROP_MQ_CHANNEL_IN = "rabbit.mq.channel.in";
    String KEY_PROP_MQ_CHANNEL_OUT = "rabbit.mq.channel.out";

    String MQ_CONFIG_PROP_PATH = System.getProperty("user.dir").concat(File.separator).concat("config");

    Message doWork(Object message);
}
