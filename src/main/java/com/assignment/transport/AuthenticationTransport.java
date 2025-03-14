package com.assignment.transport;

import com.assignment.clients.AuthenticationClient;
import com.assignment.models.reqs.LoginRequest;
import com.assignment.models.resps.LoginResponse;
import io.qameta.allure.Step;

public class AuthenticationTransport extends BaseTransport {

    private static final AuthenticationClient client = new AuthenticationClient(getServiceUrl("Authentication"));

    @Step("POST /LoginUser")
    public static LoginResponse login(LoginRequest request) {
        return jsonToObject(client.login(request), LoginResponse.class);
    }
}
