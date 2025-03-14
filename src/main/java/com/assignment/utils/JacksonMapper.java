package com.assignment.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonMapper {
    private final static ObjectMapper mapper = new ObjectMapper();

    /**
     * Convert json to class model
     * @param jsonString - json
     * @param clazz -   class
     * @return  -   class instance
     * @param <T>   -   class type
     */
    public static <T> T jsonToObject(String jsonString, Class<T> clazz) {
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            throw new AssertionError("Invalid json given:\n" + jsonString);
        }
    }
}
