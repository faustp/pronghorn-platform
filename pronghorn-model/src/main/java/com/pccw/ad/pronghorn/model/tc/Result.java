package com.pccw.ad.pronghorn.model.tc;

import java.io.Serializable;

/**
 * Created by FaustineP on 28/03/2017.
 */
public class Result implements Serializable {

    private String expectedResult;
    private String actualResult;
    private static final long serialVersionUID = 200614354L;


    public Result() {
    }

    public Result(String expectedResult, String actualResult) {
        this.expectedResult = expectedResult;
        this.actualResult = actualResult;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }
}
