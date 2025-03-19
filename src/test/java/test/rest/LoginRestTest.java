package test.rest;

import com.assignment.models.reqs.LoginRequest;
import com.assignment.models.resps.LoginResponse;
import com.assignment.transport.AuthenticationTransport;
import com.assignment.utils.JsonUtil;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.assignment.utils.JsonUtil.objectToJson;

public class LoginRestTest {

    //should be in base class
    @BeforeSuite
    public void setLogging() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    //should be in base class
    //we can create manager class with all clients
    //with manager it should be like this: Manager.removeAllClients()
    @AfterMethod
    public void removeAllClients() {
        AuthenticationTransport.removeClient();
    }

    @Test(testName = "Validate login json scheme")
    public void validateJsonScheme() {
        LoginResponse loginResponse = AuthenticationTransport
                .login(LoginRequest.initDefault());
        JsonUtil.validateJsonScheme(JsonUtil.getJsonSchema(LoginResponse.class), objectToJson(loginResponse));
    }

}
