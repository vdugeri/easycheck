package com.paysoft.easycheck.dtos;

import java.io.Serializable;

public class HelloDto implements Serializable {

    private String message;

    public HelloDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloDto{message=" + message + "}";
    }
}
