package com.pccw.ad.pronghorn.message.helper;

import com.pccw.ad.pronghorn.message.Message;
import com.pccw.ad.pronghorn.model.exception.ProfileException;
import com.pccw.ad.pronghorn.model.exception.ServiceException;
import com.pccw.ad.pronghorn.model.exception.TestCaseException;
import com.pccw.ad.pronghorn.model.profile.Selector;
import com.pccw.ad.pronghorn.model.profile.Profile;
import com.pccw.ad.pronghorn.model.profile.Service;
import com.pccw.ad.pronghorn.model.tc.Script;
import com.pccw.ad.pronghorn.model.tc.Status;
import com.pccw.ad.pronghorn.model.tc.TestCase;

import java.util.*;

/**
 * Created by FaustineP on 10/05/2017.
 */
public class MessageFactory {

    public static Message generateMessage() throws TestCaseException, ServiceException, ProfileException {
        Message message = new Message();
        message.setProfile(generateProfile());

        return message;
    }

    public static Set<Message> generateMessages(int count) throws TestCaseException, ServiceException, ProfileException {
        Set<Message> messages = new HashSet<>();
        for (int ctr = 0; ctr <= count; ctr++) {
            messages.add(generateMessage());
        }
        return messages;
    }

    private static Profile generateProfile() throws ProfileException, ServiceException, TestCaseException {
        Profile profile = new Profile(generateString(), generateService(1));
        profile.setSelector(new Selector(generateSelectors(3)));
        return profile;
    }

    private static LinkedHashSet<Service> generateService(int count) throws TestCaseException, ServiceException {
        LinkedHashSet<Service> services = new LinkedHashSet<>();
        for (int ctr = 0; ctr <= count; ctr++) {
            Service service = new Service(generateString(), generateTestCase(generateNumber(1, 5)));
            services.add(service);
        }
        return services;

    }


    private static HashSet<TestCase> generateTestCase(int count) throws TestCaseException {
        String string = generateString();
        HashSet<TestCase> testCases = new HashSet<>();
        for (int ctr = 0; ctr <= count; ctr++) {
            TestCase testCase = new TestCase.Builder().addIdentifier(generateString()).
                    addExecutedBy(string).
                    addAuthor(string).
                    addObjective(string).
                    addIsActive(true).
                    addStatus(Status.PASSED).
                    addScripts(generateScript(generateNumber(1, 10))).
                    build();
            testCases.add(testCase);
        }
        return testCases;
    }


    private static List<Script> generateScript(int count) {
        List<Script> scripts = new ArrayList<>();
        for (int ctr = 0; ctr <= count; ctr++) {
            Script script = new Script(generateString(), generateString(), generateString(), generateString());
            scripts.add(script);
        }
        return scripts;
    }

    private static String generateString() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    private static int generateNumber(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    private static HashMap<String, HashMap<String, String>> generateSelectors(int count) {
        HashMap<String, HashMap<String, String>> selectors = new HashMap<>();
        HashMap<String, String> selector = new HashMap<>();
        for (int ctr = 0; ctr < count; ctr++) {
            selector.put(generateString(), generateString());
            selectors.put("global", selector);
        }
        return selectors;
    }
}
