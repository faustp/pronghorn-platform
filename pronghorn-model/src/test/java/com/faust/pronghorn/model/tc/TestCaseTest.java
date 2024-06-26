package com.faust.pronghorn.model.tc;

import com.faust.pronghorn.model.exception.ProfileException;
import com.faust.pronghorn.model.exception.TestCaseException;
import org.junit.Test;

/**
 * Created by FaustineP on 06/04/2017.
 */
public class TestCaseTest {


    @Test
    public void testBuilder() throws ProfileException, TestCaseException {
        TestCase a = new TestCase.Builder()
                .addAuthor("Faust")
                .addExecutedBy("Faust")
                .addIdentifier("Sub-001")
                .addIsActive(true)
                .addObjective("End to End")
                .addPrecondition("None")
                .build();
        System.out.println(a);
    }


}