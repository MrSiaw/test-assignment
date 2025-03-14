package test.rest;

import com.assignment.models.reqs.LoginRequest;
import com.assignment.transport.AuthenticationTransport;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
                {new LoginRequest(false, 2, "965234324", null, "password")},
                {new LoginRequest(null, 2, "965234324", "+260", "password")},
                {new LoginRequest(false, null, "965234324", "+260", "password")}
        };
    }

    @Test(dataProvider = "mandatoryFieldsData")
    public void mandatoryFields(LoginRequest request) {
        Assert.assertEquals(AuthenticationTransport.login(request).getResult().getErrorDescription(), "Bad Request", "Message");
    }

    @Test(dataProvider = "nonMandatoryFieldsData")
    public void nonMandatoryFields(LoginRequest request) {
        Assert.assertEquals(AuthenticationTransport.login(request).getResult().getErrorDescription(), "Wrong Credetials", "Message");
    }
}
