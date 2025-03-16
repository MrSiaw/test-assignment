package com.assignment.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import com.github.victools.jsonschema.generator.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private final static ObjectMapper mapper = new ObjectMapper();

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
        } catch (Exception e) {
            logger.error("given json: {}", jsonString);
            throw new AssertionError("Invalid json given:\n" + jsonString);
        }
    }

    public static <T> String objectToJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new AssertionError("Can't convert class to json");
        }
    }

    @Step("Validate given json scheme")
    public static <T> void validateJsonScheme(String schemaJson, String actualJson) {
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonValidator validator = factory.getValidator();
        ProcessingReport report;
        try {
            report = validator.validate(JsonLoader.fromString(schemaJson), JsonLoader.fromString(actualJson));
        } catch (ProcessingException | IOException e) {
            logger.error("Can't load on of jsons");
            logger.error("given json 1: {}", schemaJson);
            logger.error("given json 2: {}", actualJson);
            throw new AssertionError("Can't load json");
        }
        assertTrue(report.isSuccess(), "JSON Schema validation failed: " + report);
        Allure.addAttachment("json schema", schemaJson);
    }

    @Step("Get json schema for class {clazz}")
    public static String getJsonSchema(Class<?> clazz) {
        mapper.constructType(clazz);
        var config = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON).build();
        var generator = new SchemaGenerator(config);
        var schemaNode = generator.generateSchema(clazz);
        try {
            return mapper.writeValueAsString(schemaNode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't generate json scheme for class " + e.getMessage());
        }
    }

}
