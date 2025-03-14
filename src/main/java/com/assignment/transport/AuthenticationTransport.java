package com.assignment.transport;

import com.assignment.clients.AuthenticationClient;
import com.assignment.models.reqs.LoginRequest;
import com.assignment.models.resps.LoginResponse;

public class AuthenticationTransport extends BaseTransport {

    private static final AuthenticationClient client = new AuthenticationClient(getServiceUrl("Authentication"));

    public static LoginResponse login(LoginRequest request) {
        return jsonToObject(client.login(request), LoginResponse.class);
    }
}
