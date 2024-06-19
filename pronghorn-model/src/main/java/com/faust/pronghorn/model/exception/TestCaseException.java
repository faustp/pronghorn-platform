package com.faust.pronghorn.model.exception;

/**
 * Created by FaustineP on 06/04/2017.
 */
public class TestCaseException extends Exception {

    /**
     * {@inheritDoc}
     */
    public TestCaseException() {
    }

    /**
     * {@inheritDoc}
     */
    public TestCaseException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public TestCaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public TestCaseException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public TestCaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
