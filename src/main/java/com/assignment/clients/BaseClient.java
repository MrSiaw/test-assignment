package com.assignment.clients;

public abstract class BaseClient {
    private final String baseUrl;

    public BaseClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    protected String getBaseUrl() {
        return baseUrl;
    }


}
