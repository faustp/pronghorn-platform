package com.pccw.ad.pronghorn.common.validation;

import com.pccw.ad.pronghorn.common.exception.ValidationException;

/**
 * Created by FaustineP on 12/05/2017.
 */
public class Validate {


    public static void isEmptyOrNull(String... strings) throws ValidationException {
        if (strings == null) {
            throw new ValidationException("data contains null or empty value");
        }
        for (String s : strings) {
            if (s == null || s.isEmpty()) throw new ValidationException("data contains null or empty value");
        }
    }

    public static boolean isCSSSelector(String filename){
        String pattern = "[a-z]?:(\\\\(.*))?\\.(png|jpg|bmp|jpeg|tiff)?";
        return !filename.toLowerCase().matches(pattern);
    }
}
