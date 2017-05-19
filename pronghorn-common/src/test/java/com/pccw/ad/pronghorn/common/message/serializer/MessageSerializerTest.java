package com.pccw.ad.pronghorn.common.message.serializer;

import com.pccw.ad.pronghorn.common.exception.MessageSerializerException;
import com.pccw.ad.pronghorn.message.Message;
import com.pccw.ad.pronghorn.message.helper.MessageFactory;
import com.pccw.ad.pronghorn.model.exception.ProfileException;
import com.pccw.ad.pronghorn.model.exception.ServiceException;
import com.pccw.ad.pronghorn.model.exception.TestCaseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by FaustineP on 12/05/2017.
 */
public class MessageSerializerTest {

    MessageSerializer<Message> messageMessageSerializer;

    @Before
    public void setUp() {
        messageMessageSerializer = new MessageSerializer<>();
    }

    @Test
    public void serializeMessage() throws TestCaseException, ServiceException, ProfileException, IOException,
            MessageSerializerException {

        Message actualMessage = MessageFactory.generateMessage();
        String actualJsonStrMessage = messageMessageSerializer.serialize(actualMessage);

        Message expectedMessage = messageMessageSerializer.deserialize(actualJsonStrMessage, Message.class);

        Assert.assertEquals(expectedMessage.getUuid(), actualMessage.getUuid());
        Assert.assertEquals(expectedMessage.getProfile().getName(), actualMessage.getProfile().getName());
        Assert.assertEquals(expectedMessage.getProfile().getServices().toString(),
                actualMessage.getProfile().getServices().toString());
        Assert.assertEquals(expectedMessage.getRoute(), actualMessage.getRoute());
    }


    @Test
    public void deserializedMessage() throws TestCaseException, ServiceException, ProfileException, IOException,
            MessageSerializerException {

        Message actualMessage = MessageFactory.generateMessage();
        String actualJsonStrMessage = messageMessageSerializer.serialize(actualMessage);

        Message expectedMessage = messageMessageSerializer.deserialize(actualJsonStrMessage, Message.class);

        Assert.assertEquals(expectedMessage.getUuid(), actualMessage.getUuid());
        Assert.assertEquals(expectedMessage.getProfile().getName(), actualMessage.getProfile().getName());
        Assert.assertEquals(expectedMessage.getProfile().getServices().toString(),
                actualMessage.getProfile().getServices().toString());
        Assert.assertEquals(expectedMessage.getRoute(), actualMessage.getRoute());
    }
}