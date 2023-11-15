package fr.iocean.bestioles.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class JsonUtils {
    private static final ObjectMapper mapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        return new ObjectMapper();
    }

    public static byte[] convertToJsonBytes(Object object) throws JsonProcessingException {
        return mapper.writeValueAsBytes(object);
    }
}
