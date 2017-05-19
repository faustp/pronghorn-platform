package com.pccw.ad.pronghorn.model.exception;

/**
 * Created by FaustineP on 06/04/2017.
 */
public class ProfileException extends Exception {

    /**
     * {@inheritDoc}
     */
    public ProfileException() {
    }

    /**
     * {@inheritDoc}
     */
    public ProfileException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ProfileException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public ProfileException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public ProfileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
