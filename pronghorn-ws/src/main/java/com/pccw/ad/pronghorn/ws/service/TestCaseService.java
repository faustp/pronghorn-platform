package com.pccw.ad.pronghorn.ws.service;

import com.pccw.ad.pronghorn.model.profile.Profile;
import com.pccw.ad.pronghorn.model.tc.Script;
import com.pccw.ad.pronghorn.model.tc.TestCase;
import com.pccw.ad.pronghorn.ws.reponse.ResponseMessage;
import com.pccw.ad.pronghorn.ws.repository.TestCaseRepository;
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
}
