package com.assignment.models.resps;

public class LoginResponse {

    private Object token;
    private Result result;
    private Object data;
    private Object dataStructure;
    private Object additionalData;
    private Object userInfo;
    private boolean isSuccessfull;

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getDataStructure() {
        return dataStructure;
    }

    public void setDataStructure(Object dataStructure) {
        this.dataStructure = dataStructure;
    }

    public Object getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(Object additionalData) {
        this.additionalData = additionalData;
    }

    public Object getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Object userInfo) {
        this.userInfo = userInfo;
    }

    public boolean isSuccessfull() {
        return isSuccessfull;
    }

    public void setSuccessfull(boolean successfull) {
        isSuccessfull = successfull;
    }

    public class Result{
        private String errorDescription;
        private Object additionalInfo;
        private Object eventData;
        private Object closedOdds;
        private int errorCode;
        private int resultCode;
        private Object errorCodeDescription;

        public String getErrorDescription() {
            return errorDescription;
        }

        public void setErrorDescription(String errorDescription) {
            this.errorDescription = errorDescription;
        }

        public Object getAdditionalInfo() {
            return additionalInfo;
        }

        public void setAdditionalInfo(Object additionalInfo) {
            this.additionalInfo = additionalInfo;
        }

        public Object getEventData() {
            return eventData;
        }

        public void setEventData(Object eventData) {
            this.eventData = eventData;
        }

        public Object getClosedOdds() {
            return closedOdds;
        }

        public void setClosedOdds(Object closedOdds) {
            this.closedOdds = closedOdds;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public Object getErrorCodeDescription() {
            return errorCodeDescription;
        }

        public void setErrorCodeDescription(Object errorCodeDescription) {
            this.errorCodeDescription = errorCodeDescription;
        }
    }

}
