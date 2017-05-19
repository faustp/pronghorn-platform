package com.pccw.ad.pronghorn.common.validation;

import com.pccw.ad.pronghorn.common.exception.ValidationException;
import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by FaustineP on 16/05/2017.
 */
public class ValidateTest {

    @Test
    public void isEmptyOrNull() throws Exception {
        try {
            Validate.isEmptyOrNull(null);
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof ValidationException);
        }
    }

}