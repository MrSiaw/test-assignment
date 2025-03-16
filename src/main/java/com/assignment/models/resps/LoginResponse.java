package com.assignment.models.resps;

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
    Object token;
    Result result;
    Object data;
    Object dataStructure;
    Object additionalData;
    Object userInfo;
    boolean isSuccessfull;

    //deafult name for setter method "setSuccessfull" which cause exception
    public void setIsSuccessfull(boolean successfull) {
        isSuccessfull = successfull;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    //@Builder
    public class Result{
        String errorDescription;
        Object additionalInfo;
        Object eventData;
        Object closedOdds;
        int errorCode;
        int resultCode;
        Object errorCodeDescription;
    }

}
