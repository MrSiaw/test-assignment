package com.assignment.models.reqs;

import com.assignment.config.Config;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
@Builder
@Data
@AllArgsConstructor
public class LoginRequest {
    Boolean isPrePaid;
    Integer registrationType;
    String userName;
    String phoneCountryCode;
    String password;

    public static LoginRequest initDefault() {
        return new LoginRequest(false, 2, "965234324", Config.DEFAULT_PHONE_CODE, "password");
    }
}
