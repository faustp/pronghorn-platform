package com.faust.pronghorn.ws.controller;

import com.faust.pronghorn.model.tc.TestCase;
import com.faust.pronghorn.ws.reponse.ResponseMessage;
import com.faust.pronghorn.ws.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return testCaseService.getTestCase(testId);
    }

    @RequestMapping(path = "/{name}/tcs/{testId}", method = PUT, produces = APPLICATION_JSON_VALUE, name = "Get Scripts")
    public ResponseMessage<TestCase> updateTestCase(@PathVariable String name, @PathVariable String testId,
                                                    @RequestBody TestCase testCase) {
        return testCaseService.updateTestCase(testCase);
    }

    @RequestMapping(path = "/{name}/tcs", method = POST, consumes = APPLICATION_JSON_VALUE, name = "Add Test Case")
    public String addTestCase(@RequestBody TestCase testCase, @PathVariable String name) {
        return testCaseService.addTestCase(testCase);
    }

}
