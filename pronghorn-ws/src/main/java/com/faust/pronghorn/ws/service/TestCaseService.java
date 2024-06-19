package com.faust.pronghorn.ws.service;

import com.faust.pronghorn.model.profile.Profile;
import com.faust.pronghorn.model.tc.Script;
import com.faust.pronghorn.model.tc.TestCase;
import com.faust.pronghorn.ws.reponse.ResponseMessage;
import com.faust.pronghorn.ws.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by FaustineP on 03/04/2017.
 */
@Service
public class TestCaseService {

    @Autowired
    TestCaseRepository testCaseRepository;

    public ResponseMessage<Iterable<TestCase>> getTestCases() {
        return new ResponseMessage<>(200, "", testCaseRepository.findAll());
    }

    public ResponseMessage<Iterable<Script>> getTestScript(String testId) {
        TestCase testCase = testCaseRepository.findByIdentifier(testId);
        return new ResponseMessage<>(200, "", testCase.getScripts());
    }

    public ResponseMessage<Profile> getTestCaseProfile(String testId) {
        TestCase testCase = testCaseRepository.findByIdentifier(testId);
        return new ResponseMessage<>(200, "", null);
    }

    public String addTestCase(TestCase testCase) {
        testCaseRepository.save(testCase);
        return "202";
    }

    public ResponseMessage<TestCase> updateTestCase(TestCase testCase) {
        testCaseRepository.save(testCase);
        return new ResponseMessage(202, "success", testCase);
    }

    public ResponseMessage<TestCase> getTestCase(String testId) {
        return new ResponseMessage<>(200, "success", testCaseRepository.findByIdentifier(testId));
    }
}
