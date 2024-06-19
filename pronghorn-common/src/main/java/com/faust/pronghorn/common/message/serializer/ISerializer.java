package com.faust.pronghorn.common.message.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.faust.pronghorn.common.exception.MessageSerializerException;

import java.io.IOException;

/**
 * Created by FaustineP on 11/05/2017.
 */
public interface ISerializer<T> {

    String serialize(T message) throws JsonProcessingException, MessageSerializerException;

    T deserialize(String jsonString, Class<T> clazz) throws IOException, MessageSerializerException;

    T deserialize(byte[] bytes, Class<T> clazz) throws IOException, MessageSerializerException;
}
