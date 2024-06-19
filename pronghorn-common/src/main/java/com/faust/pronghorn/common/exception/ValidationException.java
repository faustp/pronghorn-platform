package com.faust.pronghorn.common.exception;

/**
 * Created by FaustineP on 12/05/2017.
 */
public class ValidationException extends Exception {

    /**
     * {@inheritDoc}
     */
    public ValidationException() {
    }

    /**
     * {@inheritDoc}
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public ValidationException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}