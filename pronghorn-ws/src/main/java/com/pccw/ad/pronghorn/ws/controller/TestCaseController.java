package com.pccw.ad.pronghorn.ws.controller;

import com.pccw.ad.pronghorn.model.profile.Profile;
import com.pccw.ad.pronghorn.model.tc.Script;
import com.pccw.ad.pronghorn.model.tc.TestCase;
import com.pccw.ad.pronghorn.ws.reponse.ResponseMessage;
import com.pccw.ad.pronghorn.ws.service.TestCaseService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by FaustineP on 03/04/2017.
 */
@RestController
@RequestMapping(path = "/profiles")
public class TestCaseController {

    @Autowired
    TestCaseService testCaseService;

    @RequestMapping(path = "/{name}/tcs", method = GET, produces = APPLICATION_JSON_VALUE, name = "Get Test Case")
    public ResponseMessage<Iterable<TestCase>> getTestCases(@PathVariable String name) {
        return testCaseService.getTestCases();
    }

    @RequestMapping(path = "/{name}/tcs/{testId}", method = GET, produces = APPLICATION_JSON_VALUE, name = "Get Scripts")
    public ResponseMessage<TestCase> getTestCase(@PathVariable String name, @PathVariable String testId) {
        return null;
    }

    @RequestMapping(path = "/{name}/tcs/{testId}", method = PUT, produces = APPLICATION_JSON_VALUE, name = "Get Scripts")
    public ResponseMessage<TestCase> updateTestCase(@PathVariable String name, @PathVariable String testId,
                                                    @RequestBody TestCase testCase) {
        return null;
    }

    @RequestMapping(path = "/{name}/tcs", method = POST, consumes = APPLICATION_JSON_VALUE, name = "Add Test Case")
    public String addTestCase(@RequestBody TestCase testCase, @PathVariable String name) {
        return testCaseService.addTestCase(testCase);
    }

}
