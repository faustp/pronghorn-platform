package com.pccw.ad.pronghorn.ws.client;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.pccw.ad.pronghorn.common.exception.MessageSerializerException;
import com.pccw.ad.pronghorn.common.message.serializer.MessageSerializer;
import com.pccw.ad.pronghorn.model.profile.Profile;
import com.pccw.ad.pronghorn.model.tc.Script;
import com.pccw.ad.pronghorn.model.tc.TestCase;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by FaustineP on 22/05/2017.
 */
public class WSClient {
    final static Logger logger = Logger.getLogger(WSClient.class);

    public static String BASE_PATH_URL = "/pronghorn/ws/rest/v1/";
    public static final String TESTCASE_PATH_URL = "tcs";
    public static final String PROFILE_PATH_URL = "profiles";
    public static final String SCRIPT_PATH_URL = "scripts";

    public static RestTemplate restTemplate = new RestTemplate();
    public String host = null;
    public String port = null;

    public WSClient(String host, String port){
        this.host = host;
        this.port = port;
    }

    public Script getTestScript(String profileName, String testCaseId, String scriptId) throws IOException {
        String resourceURL = host + ":" + port + BASE_PATH_URL + PROFILE_PATH_URL + File.separator  + profileName
                + File.separator + TESTCASE_PATH_URL + File.separator + testCaseId + File.separator + SCRIPT_PATH_URL
                + File.separator + scriptId;
        logger.info("resourceURL " + resourceURL);
        ResponseEntity<String> response = restTemplate.getForEntity(resourceURL, String.class);

        ObjectMapper om = new ObjectMapper();
        Script script = om.readValue(response.getBody(), new TypeReference<Script>(){
        });
        logger.info("response " + response.getBody());

        return script;
    }

    public List<Script> getAllTestScripts(String profileName, String testCaseId) throws IOException {
        String resourceURL = host + ":" + port + BASE_PATH_URL + PROFILE_PATH_URL + File.separator  + profileName
                + File.separator + TESTCASE_PATH_URL + File.separator + testCaseId + File.separator + SCRIPT_PATH_URL;
        logger.info("resourceURL " + resourceURL);
        ResponseEntity<String> response = restTemplate.getForEntity(resourceURL, String.class);

        ObjectMapper om = new ObjectMapper();
        List<Script> scripts = om.readValue(response.getBody(), new TypeReference<List<Script>>(){
        });
        logger.info("response " + response.getBody());

        return scripts;
    }

    public TestCase getTestCase(String profileName, String testCaseId) throws IOException {
        String resourceURL = host + ":" + port + BASE_PATH_URL + PROFILE_PATH_URL + File.separator  + profileName
                + File.separator + TESTCASE_PATH_URL + File.separator + testCaseId;
        logger.info("resourceURL " + resourceURL);
        ResponseEntity<String> response = restTemplate.getForEntity(resourceURL, String.class);

        ObjectMapper om = new ObjectMapper();
        TestCase testCase = om.readValue(response.getBody(), new TypeReference<TestCase>(){
        });
        logger.info("response " + response.getBody());

        return testCase;
    }

    public List<TestCase> getAllTestCases(String profileName) throws IOException {
        String resourceURL = host + ":" + port + BASE_PATH_URL + PROFILE_PATH_URL + File.separator  + profileName
                + File.separator + TESTCASE_PATH_URL;
        logger.info("resourceURL " + resourceURL);
        ResponseEntity<String> response = restTemplate.getForEntity(resourceURL, String.class);

        ObjectMapper om = new ObjectMapper();
        List<TestCase> testCases = om.readValue(response.getBody(), new TypeReference<List<TestCase>>(){
        });
        logger.info("response " + response.getBody());

        return testCases;
    }

    public Profile getProfile(String profileName) throws IOException, MessageSerializerException {
        String resourceURL = host + ":" + port + BASE_PATH_URL + PROFILE_PATH_URL + File.separator  + profileName;
        logger.info("resourceURL " + resourceURL);
        ResponseEntity<String> response = restTemplate.getForEntity(resourceURL, String.class);

        ObjectMapper om = new ObjectMapper();
        Profile profile = om.readValue(response.getBody(), new TypeReference<Profile>(){
        });
        logger.info("response " + response.getBody());

        return profile;
    }

    public List<Profile> getAllProfiles() throws IOException {
        String resourceURL = host + ":" + port + BASE_PATH_URL + PROFILE_PATH_URL + File.separator;
        logger.info("resourceURL " + resourceURL);
        ResponseEntity<String> response = restTemplate.getForEntity(resourceURL, String.class);

        ObjectMapper om = new ObjectMapper();
        List<Profile> profiles = om.readValue(response.getBody(), new TypeReference<List<Profile>>(){
        });
        logger.info("response " + response.getBody());

        return profiles;
    }
}
