package ca.bc.gov.iamp.justindisputesapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtility {

    private final static Logger log = LoggerFactory.getLogger(JsonUtility.class);

    public static String toJson(Object object) {
        String json = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert object into JSON string: {}", e.getMessage(), e);

        }
        return json;
    }

    public static <T> T toObject(String json, Class<T> classOfT) {
        T object = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            object = mapper.readValue(json, classOfT);
        } catch (IOException e) {
            log.error("Failed to convert JSON Sting into Object: {}", e.getMessage(), e);
        }
        return object;
    }
}
