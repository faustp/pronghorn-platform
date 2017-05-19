package com.pccw.ad.pronghorn.wrapper.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.ad.pronghorn.message.Message;
import com.pccw.ad.pronghorn.model.exception.ProfileException;
import com.pccw.ad.pronghorn.model.exception.ServiceException;
import com.pccw.ad.pronghorn.model.exception.TestCaseException;
import com.pccw.ad.pronghorn.model.profile.Profile;
import com.pccw.ad.pronghorn.model.profile.Service;
import com.pccw.ad.pronghorn.model.tc.Script;
import com.pccw.ad.pronghorn.model.tc.TestCase;
import com.rabbitmq.client.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;

/**
 * Created by FaustineP on 04/04/2017.
 */
public class ConsumerImplTest {
    String QUEUE_NAME = "com.pccw.ad.pronghorn.in";
    ConnectionFactory factory;
    Connection connection;
    Channel channel;

    @Before
    public void setUp() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        connection = factory.newConnection();
        channel = connection.createChannel();

    }

    @After
    public void tearDown() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    @Test
    public void send() throws IOException, TimeoutException {
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        for (int x = 0; x < 5; x++) {
            Message message = new Message();
            String jsonMessage = new ObjectMapper().writeValueAsString(message);
            channel.basicPublish("", QUEUE_NAME, null, jsonMessage.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
    }


    @Test
    public void receive() throws IOException, InterruptedException {
        GetResponse response = channel.basicGet(QUEUE_NAME, true);
        String jsonMessage = new String(response.getBody());
        Message message = new ObjectMapper().readValue(jsonMessage, Message.class);
        System.out.println(" [x] Received '" + QUEUE_NAME + "':'" + message + "'");
    }

    @Test
    public void buildMessage() throws IOException, ProfileException, TestCaseException, ServiceException {
        LinkedHashSet<Service> services = new LinkedHashSet<>();
        HashSet<TestCase> testCaseSet = new HashSet<>();

        TestCase testCase = new TestCase.Builder()
                .addAuthor("Faust")
                .addExecutedBy("Faust")
                .addIdentifier("Sub-001")
                .addIsActive(true)
                .addObjective("Test")
                .addScripts(null).build();
        testCaseSet.add(testCase);
        Service service = new Service("UDS", testCaseSet);

        services.add(service);
        Profile profile = new Profile("bpss", services);
        Map<String, String> selector = new HashMap<>();
        selector.put("loginUserTxt", "loginUserTxt");
        selector.put("loginPasswordTxt", "loginPasswordTxt");
        selector.put("loginBtn", "loginBtn");
        profile.setSelector(null);

        List<Script> scripts = new ArrayList<>();
        scripts.add(buildTestScript("openBrowser", "IE", "", ""));
        scripts.add(buildTestScript("navigate", "http://google.com", "", ""));
        scripts.add(buildTestScript("closeBrowser", "", "", ""));

        Message message = new Message();
        message.setProfile(profile);
        String jsonMessage = new ObjectMapper().writeValueAsString(message);
        Message message1 = new ObjectMapper().readValue(jsonMessage, Message.class);
        System.out.println(new ObjectMapper().writeValueAsString(testCase));


    }


    private Script buildTestScript(String action, String input, String key, String description) {
        Script script = new Script(null, null, null, null);
        script.setAction(action);
        script.setInputData(input);
        script.setObjectKey(key);
        script.setDescription(description);
        return script;
    }

}