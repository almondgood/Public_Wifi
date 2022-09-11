package com.api.exception;

import java.io.IOException;

public class ApiException extends Exception{

    public ApiException() {
        super();
    }

    public ApiException(String errorCode) {
        super.printStackTrace();
        error(errorCode);
    }

    void error(String errorCode) {

    }
}
