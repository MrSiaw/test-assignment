package com.assignment.models.reqs;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequest {
    private Boolean isPrePaid;
    private Integer registrationType;
    private String userName;
    private String phoneCountryCode;
    private String password;

    public LoginRequest(Boolean isPrePaid, Integer registrationType, String userName, String phoneCountryCode, String password) {
        this.isPrePaid = isPrePaid;
        this.registrationType = registrationType;
        this.userName = userName;
        this.phoneCountryCode = phoneCountryCode;
        this.password = password;
    }

    public Boolean isPrePaid() {
        return isPrePaid;
    }

    public Integer getRegistrationType() {
        return registrationType;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    public String getPassword() {
        return password;
    }

}
