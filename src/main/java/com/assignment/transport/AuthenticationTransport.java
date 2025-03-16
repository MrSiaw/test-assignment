package com.assignment.transport;

import com.assignment.clients.AuthenticationClient;
import com.assignment.models.reqs.LoginRequest;
import com.assignment.models.resps.LoginResponse;
import io.qameta.allure.Step;

public class AuthenticationTransport extends BaseTransport {

    private static final ThreadLocal<AuthenticationClient> client = new ThreadLocal<>();

    public static AuthenticationClient getClient() {
        if (client.get() == null) {
            client.set(new AuthenticationClient(getServiceUrl("Authentication")));
        }
        return client.get();
    }

    @Step("POST /LoginUser")
    public static LoginResponse login(LoginRequest request) {
        return jsonToObject(getClient().login(request), LoginResponse.class);
    }

    public static void removeClient() {
        client.remove();
    }
}
