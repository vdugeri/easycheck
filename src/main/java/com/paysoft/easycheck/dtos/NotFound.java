package com.paysoft.easycheck.dtos;

public class NotFound {

    private String message;
    private String status;

    public NotFound(String message) {
        this.message = message;
        this.status = "error";
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
