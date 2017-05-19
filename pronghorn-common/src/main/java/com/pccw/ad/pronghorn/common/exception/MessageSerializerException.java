package com.pccw.ad.pronghorn.common.exception;

/**
 * Created by FaustineP on 12/05/2017.
 */
public class MessageSerializerException extends Exception {


    public MessageSerializerException() {
    }

    public MessageSerializerException(String message) {
        super(message);
    }

    public MessageSerializerException(String message, Throwable cause) {
        super(message, cause);
    }
}
