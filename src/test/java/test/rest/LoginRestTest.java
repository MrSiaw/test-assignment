package test.rest;

import com.assignment.models.reqs.LoginRequest;
import com.assignment.models.resps.LoginResponse;
import com.assignment.transport.AuthenticationTransport;
import com.assignment.utils.JsonUtil;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.assignment.utils.JsonUtil.objectToJson;
import static org.testng.Assert.assertEquals;

public class LoginRestTest {

    //could be in base class
    @BeforeSuite
    public void setLogging() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @DataProvider(name = "mandatoryFieldsData")
    public Object[][] mandatoryFieldsData() {
        return new Object[][]{
                {new LoginRequest(false, 2, null, "+260", "password")},
                {new LoginRequest(false, 2, "965234324", "+260", null)}
        };
    }

    @DataProvider(name = "nonMandatoryFieldsData")
    public Object[][] nonMandatoryFieldsData() {
        return new Object[][]{
                //why phone code is not mandatory? comes from a brand id?
                {new LoginRequest(false, 2, "965234324", null, "password")},
                {new LoginRequest(null, 2, "965234324", "+260", "password")},
                {new LoginRequest(false, null, "965234324", "+260", "password")}
        };
    }

    @Step("Check that login response contains error '{expectedMessage}'")
    private void assertLoginErrorMessage(LoginResponse response, String expectedMessage) {
        assertEquals(response.getResult().getErrorDescription(), expectedMessage, "Response doesn't have given message");
    }

    @Test(dataProvider = "mandatoryFieldsData", testName = "Mandatory field absence leads to 'Bad Request' error")
    public void mandatoryFields(LoginRequest request) {
        assertLoginErrorMessage(AuthenticationTransport.login(request), "Bad Request");
    }

    @Test(dataProvider = "nonMandatoryFieldsData", testName = "Non mandatory field doesn't affect the request")
    public void nonMandatoryFields(LoginRequest request) {
        assertLoginErrorMessage(AuthenticationTransport.login(request), "Wrong Credetials");
    }

    @Test(testName = "Validate login json scheme")
    public void validateJsonScheme() {
        //tried to used generator, but it has id and ref fields, which JsonValidator doesn't support
        //leave it as TODO
        /*
        JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(objectMapper);
        JsonSchema schema = schemaGen.generateSchema(LoginResponse.class);
        String schemaJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);*/
        String schemaJson = """
                {\
                  "type": "object",
                  "properties": {
                    "token": {
                      "type": "null"
                    },
                    "result": {
                      "type": "object",
                      "properties": {
                        "errorDescription": {
                          "type": "string"
                        },
                        "additionalInfo": {
                          "type": "null"
                        },
                        "eventData": {
                          "type": "null"
                        },
                        "closedOdds": {
                          "type": "null"
                        },
                        "errorCode": {
                          "type": "integer"
                        },
                        "resultCode": {
                          "type": "integer"
                        },
                        "errorCodeDescription": {
                          "type": "null"
                        }
                      },
                      "required": [
                        "errorDescription",
                        "additionalInfo",
                        "eventData",
                        "closedOdds",
                        "errorCode",
                        "resultCode",
                        "errorCodeDescription"
                      ]
                    },
                    "data": {
                      "type": "null"
                    },
                    "dataStructure": {
                      "type": "null"
                    },
                    "additionalData": {
                      "type": "null"
                    },
                    "userInfo": {
                      "type": "null"
                    },
                    "isSuccessfull": {
                      "type": "boolean"
                    }
                  },
                  "required": [
                    "token",
                    "result",
                    "data",
                    "dataStructure",
                    "additionalData",
                    "userInfo",
                    "isSuccessfull"
                  ]
                }""";
        LoginResponse loginResponse = AuthenticationTransport.login(new LoginRequest(false, 2, "","",""));
        JsonUtil.validateJsonScheme(schemaJson, objectToJson(loginResponse));
    }
}
