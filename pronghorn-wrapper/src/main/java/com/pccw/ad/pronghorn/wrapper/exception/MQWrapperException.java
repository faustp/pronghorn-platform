package com.pccw.ad.pronghorn.wrapper.exception;

/**
 * Created by FaustineP on 04/04/2017.
 */
public class MQWrapperException extends Exception {

    public MQWrapperException() {
    }

    public MQWrapperException(String message) {
        super(message);
    }

    public MQWrapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public MQWrapperException(Throwable cause) {
        super(cause);
    }

    public MQWrapperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
