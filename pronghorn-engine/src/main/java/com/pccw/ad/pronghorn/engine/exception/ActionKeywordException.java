package com.pccw.ad.pronghorn.engine.exception;

import org.openqa.selenium.NoSuchElementException;

/**
 * Created by FaustineP on 14/03/2017.
 */
public class ActionKeywordException extends NoSuchElementException {


    public ActionKeywordException(String reason) {
        super(reason);
    }

    public ActionKeywordException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
