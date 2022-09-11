package com.api.exception;


public class WifiApiException extends ApiException{

    public WifiApiException() {
        super();
    }

    public WifiApiException(String errorCode) {
        super(errorCode);
    }

    @Override
    void error(String errorCode) {
        super.error(errorCode);
    }
}
