package com.pccw.ad.pronghorn.ws.client;


import com.pccw.ad.pronghorn.common.exception.MessageSerializerException;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by 81013567 on 23/05/2017.
 */
public class WSClientTest {


    @Test
    public void testClient() throws Exception {

        WSClient wsClient = new WSClient("10.37.210.32","8181");
       // System.out.println(wsClient.getProfile("BPSS"));
//         System.out.println(wsClient.getAllProfiles());
//        System.out.println(wsClient.getAllTestCases("BPSS"));
//        System.out.println(wsClient.getTestCase("BPSS", "Sub-001"));
       // System.out.println(wsClient.getAllTestScripts("BPSS", "Sub-001"));
        System.out.println(wsClient.getTestScript("BPSS", "Sub-001", "Sub-001"));
    }

}