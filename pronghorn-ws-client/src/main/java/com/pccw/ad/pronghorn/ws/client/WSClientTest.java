package com.pccw.ad.pronghorn.ws.client;

import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.mapping.TextScore;



/**
 * Created by 81013567 on 22/05/2017.
 */
public class WSClientTest {


    @Test
    public void testClient(){
        WSClient wsClient = new WSClient("host","port");
        wsClient.getAllTestCaseByProjectName("");
    }

}