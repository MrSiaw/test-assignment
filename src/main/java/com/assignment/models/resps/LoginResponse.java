package com.assignment.models.resps;

import com.assignment.models.JsonNullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    //fpr some field don't know their type, guess String mostly
    //for generating pojo used online pojo generator
    @JsonNullable
    String token;
    Result result;
    @JsonNullable
    String data;
    @JsonNullable
    String dataStructure;
    @JsonNullable
    String additionalData;
    @JsonNullable
    String userInfo;
    boolean isSuccessfull;

    //deafult name for setter method "setSuccessfull" which cause exception
    public void setIsSuccessfull(boolean successfull) {
        isSuccessfull = successfull;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result{
        String errorDescription;
        @JsonNullable
        String additionalInfo;
        @JsonNullable
        String eventData;
        @JsonNullable
        String closedOdds;
        int errorCode;
        int resultCode;
        @JsonNullable
        String errorCodeDescription;
    }

}
