package com.pccw.ad.pronghorn.mq.client;


import com.pccw.ad.pronghorn.common.exception.MessageSerializerException;
import com.pccw.ad.pronghorn.message.Message;
import com.pccw.ad.pronghorn.message.helper.MessageFactory;
import com.pccw.ad.pronghorn.model.exception.ProfileException;
import com.pccw.ad.pronghorn.model.exception.ServiceException;
import com.pccw.ad.pronghorn.model.exception.TestCaseException;
import com.pccw.ad.pronghorn.mq.client.exception.MQClientException;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created by FaustineP on 10/05/2017.
 */
public class MQClientTest {
    MQClient<Message> mqClient;

    @Test
    public void sendMessage() throws MQClientException, IOException, TimeoutException, TestCaseException, ServiceException, ProfileException, ExecutionException, InterruptedException {
        String configFile = new File(System.getProperty("user.dir")).getParent().concat(File.separator).
                concat("config").concat(File.separator);
        mqClient = new MQClient<>(configFile.concat("consumer.properties"));
        ExecutorService service = Executors.newFixedThreadPool(1000);
        for (int ctr = 0; ctr < 100000; ctr++) {
            MQSender mqSender = new MQSender();
            Future<String> future = service.submit(mqSender);
            System.out.println(future.get());
        }
        service.shutdown();
        mqClient.closeConnection();
    }


    @Test
    public void consumeMessage() throws IOException, MQClientException, TimeoutException, MessageSerializerException {
        String configFile = new File(System.getProperty("user.dir")).getParent().concat(File.separator).
                concat("config").concat(File.separator);
        mqClient = new MQClient<>(configFile.concat("consumer.properties"));
        Message message;
        do {
            message = mqClient.receive("com.pccw.ad.pronghorn.in", Message.class);
            System.out.println(message);
        } while (message != null);
        mqClient.closeConnection();
    }

    public class MQSender implements Callable<String> {

        @Override
        public String call() throws Exception {
            mqClient.send(MessageFactory.generateMessage(), "com.pccw.ad.pronghorn.in");
            return null;
        }
    }
}