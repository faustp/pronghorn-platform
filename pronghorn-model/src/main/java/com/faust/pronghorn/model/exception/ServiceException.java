package com.faust.pronghorn.model.exception;

/**
 * Created by FaustineP on 06/04/2017.
 */
public class ServiceException extends Exception {

    /**
     * {@inheritDoc}
     */
    public ServiceException() {
    }

    /**
     * {@inheritDoc}
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
