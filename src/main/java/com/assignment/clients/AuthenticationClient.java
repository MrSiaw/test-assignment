package com.assignment.clients;

import com.assignment.models.reqs.LoginRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class AuthenticationClient extends BaseClient {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationClient.class);

    public AuthenticationClient(String baseUrl) {
        super(baseUrl);
    }

    public Response login(LoginRequest requestModel) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestModel)
                //TODO should be form props file/config
                .header("brandid",30)
                .header("channelid", 1)
                .when()
                .post(getBaseUrl() + "LoginUser")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
