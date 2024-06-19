package com.faust.pronghorn.mq.client.exception;

/**
 * Created by FaustineP on 10/05/2017.
 */
public class MQClientException extends Exception {

    public MQClientException() {
    }

    public MQClientException(String message) {
        super(message);
    }

    public MQClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public MQClientException(Throwable cause) {
        super(cause);
    }

    public MQClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
