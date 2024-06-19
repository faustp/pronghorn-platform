package com.faust.pronghorn.common.message.serializer;

import com.faust.pronghorn.common.exception.MessageSerializerException;
import com.faust.pronghorn.message.Message;
import com.faust.pronghorn.message.helper.MessageFactory;
import com.faust.pronghorn.model.exception.ProfileException;
import com.faust.pronghorn.model.exception.ServiceException;
import com.faust.pronghorn.model.exception.TestCaseException;
import com.faust.pronghorn.model.profile.Service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

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

        Set<Service> expectedServices = expectedMessage.getProfile().getServices();
        Set<Service> actualServices = actualMessage.getProfile().getServices();

        Assert.assertEquals(expectedServices.size(), actualServices.size());

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

        Set<Service> expectedServices = expectedMessage.getProfile().getServices();
        Set<Service> actualServices = actualMessage.getProfile().getServices();

        Assert.assertEquals(expectedServices.size(), actualServices.size());
    }
}