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


import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by FaustineP on 03/04/2017.
 */
@RestController
public class TestCaseController {

    @Autowired
    TestCaseService testCaseService;

    @RequestMapping(path = "/tc", method = GET, produces = MediaType.APPLICATION_JSON_VALUE, name = "Get Test Case")
    public ResponseMessage<Iterable<TestCase>> getTestCases() {
        return testCaseService.getTestCases();
    }

    @RequestMapping(path = "/tc/{testId}/scripts", method = GET, produces = MediaType.APPLICATION_JSON_VALUE, name = "Get Scripts")
    public ResponseMessage<Iterable<Script>> getTestScripts(@PathVariable String testId) {
        return testCaseService.getTestScript(testId);
    }

    @RequestMapping(path = "/tc/{testId}/profile", method = GET, produces = MediaType.APPLICATION_JSON_VALUE, name = "Get Profile")
    public ResponseMessage<Profile> getTestCaseProfile(@PathVariable String testId) {
        return testCaseService.getTestCaseProfile(testId);
    }

    @RequestMapping(path = "tc", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE, name = "Add Test Case")
    public String addTestCase(@RequestBody TestCase testCase) {
        return testCaseService.addTestCase(testCase);
    }

}
