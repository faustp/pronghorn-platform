package com.pccw.ad.pronghorn.common.message.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.ad.pronghorn.common.exception.MessageSerializerException;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by FaustineP on 11/05/2017.
 */
public class MessageSerializer<T> implements ISerializer<T> {

    private static ObjectMapper objectMapper = new ObjectMapper();


    public MessageSerializer() {
    }


    @Override
    public String serialize(T message) throws JsonProcessingException, MessageSerializerException {
        if (message == null) throw new MessageSerializerException("Object to be serialized must not be null");
        return objectMapper.writeValueAsString(message);
    }


    @Override
    public T deserialize(String jsonString, Class<T> clazz) throws IOException, MessageSerializerException {
        if (jsonString == null || jsonString.isEmpty())
            throw new MessageSerializerException("String to be deserialized must not be null");
        String jsonMessage = new String(jsonString.getBytes(), Charset.forName("UTF-8"));
        return objectMapper.readValue(jsonMessage, clazz);
    }

    @Override
    public T deserialize(byte[] bytes, Class<T> clazz) throws IOException, MessageSerializerException {
        if (bytes == null) throw new MessageSerializerException("bytes to be deserialized must not be null");
        String jsonMessage = new String(bytes, Charset.forName("UTF-8"));
        return objectMapper.readValue(jsonMessage, clazz);
    }
}
