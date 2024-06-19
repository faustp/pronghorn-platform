package com.faust.pronghorn.message.helper;

import com.faust.pronghorn.message.Message;
import com.faust.pronghorn.model.exception.ProfileException;
import com.faust.pronghorn.model.exception.ServiceException;
import com.faust.pronghorn.model.exception.TestCaseException;
import com.faust.pronghorn.model.profile.Selector;
import com.faust.pronghorn.model.profile.Profile;
import com.faust.pronghorn.model.profile.Service;
import com.faust.pronghorn.model.tc.Script;
import com.faust.pronghorn.model.tc.Status;
import com.faust.pronghorn.model.tc.TestCase;

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
        Profile profile = new Profile();
        profile.setId((long) generateNumber(1, 4));
        profile.setName(generateString());
        profile.setServices(generateService(3));
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
                    addStatus(Status.PASS).
                    addScripts(generateScript(generateNumber(1, 10))).
                    build();
            testCases.add(testCase);
        }
        return testCases;
    }


    private static List<Script> generateScript(int count) {
        List<Script> scripts = new ArrayList<>();
        for (int ctr = 0; ctr <= count; ctr++) {
            Script script = new Script();
            script.setAction(generateString());
            script.setDescription(generateString());
            script.setInputData(generateString());
            script.setSelector(generateSelector());
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

    private static Selector generateSelector() {
        Selector selector = new Selector();
        selector.setKey(generateString());
        selector.setValue(generateString());
        return selector;
    }
}
