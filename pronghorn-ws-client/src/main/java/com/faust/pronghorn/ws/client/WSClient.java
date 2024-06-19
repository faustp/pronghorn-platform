package com.faust.pronghorn.ws.client;


import com.faust.pronghorn.common.exception.MessageSerializerException;
import com.faust.pronghorn.model.profile.Profile;
import com.faust.pronghorn.model.tc.Script;
import com.faust.pronghorn.model.tc.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;


/**
 * Created by FaustineP on 22/05/2017.
 */
public class WSClient {
    final static Logger logger = LoggerFactory.getLogger(WSClient.class);

    public static String BASE_PATH_URL = "/pronghorn/ws/rest/v1/";
    public static final String TESTCASE_PATH_URL = "tcs";
    public static final String PROFILE_PATH_URL = "profiles";
    public static final String SCRIPT_PATH_URL = "scripts";
    public static final String SEPARATOR = "/";
    public static final String HTTP = "http://";

    public static RestTemplate restTemplate = new RestTemplate();
    public String host = null;
    public String port = null;

    public WSClient(String host, String port){
        this.host = host;
        this.port = port;
    }

    public void main (String[] args) throws IOException {
        getAllProfiles();
    }

    public Script getTestScript(String profileName, String testCaseId, String scriptId) throws IOException {
        String resourceURL = HTTP + host + ":" + port + BASE_PATH_URL + PROFILE_PATH_URL + SEPARATOR  + profileName
                + SEPARATOR + TESTCASE_PATH_URL + SEPARATOR + testCaseId + SEPARATOR + SCRIPT_PATH_URL
                + SEPARATOR + scriptId;
        logger.info("resourceURL " + resourceURL);
        ResponseMessage<Script> response = restTemplate.getForObject(resourceURL,ResponseMessage.class);

        if(response == null)
            return null;

        logger.info("response " + response.getResult());
        return response.getResult();
    }

    public List<Script> getAllTestScripts(String profileName, String testCaseId) throws IOException {
        String resourceURL = HTTP + host + ":" + port + BASE_PATH_URL + PROFILE_PATH_URL + SEPARATOR  + profileName
                + SEPARATOR + TESTCASE_PATH_URL + SEPARATOR + testCaseId + SEPARATOR + SCRIPT_PATH_URL;
        logger.info("resourceURL " + resourceURL);
        ResponseMessage<List<Script>> response = restTemplate.getForObject(resourceURL,ResponseMessage.class);

        if(response == null)
            return null;

        logger.info("response " + response.getResult());
        return response.getResult();
    }
//TODO
    public TestCase getTestCase(String profileName, String testCaseId) throws IOException {
        String resourceURL = HTTP + host + ":" + port + BASE_PATH_URL + PROFILE_PATH_URL + SEPARATOR  + profileName
                + SEPARATOR + TESTCASE_PATH_URL + SEPARATOR + testCaseId;
        logger.info("resourceURL " + resourceURL);
        ResponseMessage<TestCase> response = restTemplate.getForObject(resourceURL,ResponseMessage.class);

        if(response == null)
            return null;

        logger.info("response " + response.getResult());
        return response.getResult();
    }

    public List<TestCase> getAllTestCases(String profileName) throws IOException {
        String resourceURL = HTTP + host + ":" + port + BASE_PATH_URL + PROFILE_PATH_URL + SEPARATOR  + profileName
                + SEPARATOR + TESTCASE_PATH_URL;
        logger.info("resourceURL " + resourceURL);
        ResponseMessage<List<TestCase>> response = restTemplate.getForObject(resourceURL,ResponseMessage.class);

        if(response == null)
            return null;

        logger.info("response " + response.getResult());
        return response.getResult();
    }

    public Profile getProfile(String profileName) throws IOException, MessageSerializerException {
        String resourceURL = HTTP + host + ":" + port + BASE_PATH_URL + PROFILE_PATH_URL + SEPARATOR  + profileName;
        logger.info("resourceURL " + resourceURL);
        ResponseMessage<Profile> response = restTemplate.getForObject(resourceURL,ResponseMessage.class);

        if(response == null)
            return null;

        logger.info("response " + response.getCode());
        return response.getResult();
    }

    public List<Profile> getAllProfiles() throws IOException {
        String resourceURL = HTTP + host + ":" + port + BASE_PATH_URL + PROFILE_PATH_URL;
        logger.info("resourceURL " + resourceURL);
        ResponseMessage<List<Profile>> response = restTemplate.getForObject(resourceURL,ResponseMessage.class);

        if(response == null)
            return null;

        logger.info("response " + response.getCode());
        return response.getResult();
    }

    public static class ResponseMessage<T> {

        private int code;
        private String message;
        private T result;

        public ResponseMessage() {
        }

        public ResponseMessage(int code, String message, T result) {
            this.code = code;
            this.message = message;
            this.result = result;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public T getResult() {
            return result;
        }

        public void setResult(T result) {
            this.result = result;
        }
    }

}
