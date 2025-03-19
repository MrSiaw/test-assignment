package com.assignment.utils;

import com.assignment.models.JsonNullable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.testng.Assert.assertTrue;

public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private final static ObjectMapper mapper = new ObjectMapper();
    private final static JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
    private final static String PROPERTIES_JSON_SCHEME = "properties";
    private final static String TYPE_JSON_SCHEME = "type";

    /**
     * Convert json to class model
     *
     * @param jsonString - json
     * @param clazz      -   class
     * @param <T>        -   class type
     * @return -   class instance
     */
    public static <T> T jsonToObject(String jsonString, Class<T> clazz) {
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (Exception ex) {
            throw printStackTraceAndGetAssert(ex, "Can't convert json to class: ");
        }
    }

    public static <T> String objectToJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception ex) {
            throw printStackTraceAndGetAssert(ex, "Can't convert class to json: ");
        }
    }

    /**
     * Validate that given json validates given json scheme
     *
     * @param schemaJson -   expected json scheme
     * @param actualJson -   actual json
     */
    @Step("Validate given json scheme")
    public static void validateJsonScheme(String schemaJson, String actualJson) {
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonValidator validator = factory.getValidator();
        ProcessingReport report;
        try {
            logger.info("validating json scheme for response: {}", actualJson);
            logger.info("scheme: {}", schemaJson);
            report = validator.validate(JsonLoader.fromString(schemaJson), JsonLoader.fromString(actualJson));
        } catch (ProcessingException | IOException ex) {
            throw printStackTraceAndGetAssert(ex, "Can't load json: ");
        }
        assertTrue(report.isSuccess(), "JSON Schema validation failed: " + report);
        Allure.addAttachment("json schema", schemaJson);
    }

    /**
     * Generate json scheme for given class
     *
     * @param clazz -   class type
     * @return -   json scheme string
     */
    @Step("Get json schema for class {clazz}")
    public static String getJsonSchema(Class<?> clazz) {
        try {
            JsonSchema schema = schemaGen.generateSchema(clazz);
            JsonNode schemaJsonNode = mapper.readTree(mapper.writeValueAsString(schema));
            addNullTypes(schemaJsonNode, clazz);
            return mapper.writeValueAsString(schemaJsonNode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't generate json scheme for class " + e.getMessage());
        }
    }

    /**
     * If the field marked as JsonNullable - adds to its type "null"
     * Works also with nested classes
     * @param schemaJsonNode    -   json schema as JsonNode
     * @param clazz -   class type
     */
    private static void addNullTypes(JsonNode schemaJsonNode, Class<?> clazz) {
        if (schemaJsonNode == null || !schemaJsonNode.isObject()) {
            return;
        }
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonNullable.class)) {
                ObjectNode property;
                if (schemaJsonNode.has(PROPERTIES_JSON_SCHEME)) {
                    property = ((ObjectNode) schemaJsonNode.get(PROPERTIES_JSON_SCHEME).get(field.getName()));
                } else {
                    property = (ObjectNode) schemaJsonNode.get(field.getName());
                }
                JsonNode defaultType = property.get(TYPE_JSON_SCHEME);
                property.remove(TYPE_JSON_SCHEME);
                property
                        .putArray(TYPE_JSON_SCHEME)
                        .add(defaultType)
                        .add("null");
            }
            if (!field.getType().isPrimitive() && !field.getType().equals(String.class)) {
                JsonNode nestedNode = schemaJsonNode.get(PROPERTIES_JSON_SCHEME).get(field.getName());
                if (nestedNode != null) {
                    addNullTypes(nestedNode.get(PROPERTIES_JSON_SCHEME), field.getType());
                }
            }
        }
    }

    private static AssertionError printStackTraceAndGetAssert(Exception ex, String assertMessage) {
        logger.error(ex.getMessage(), ex);
        return new AssertionError(assertMessage + ex.getMessage());
    }

}
