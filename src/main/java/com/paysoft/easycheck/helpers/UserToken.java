package com.paysoft.easycheck.helpers;

import com.paysoft.easycheck.dtos.CustomerDTO;

public class UserToken {

    private String token;
    private CustomerDTO user;

    public String getToken() {
        return token;
    }

    public UserToken setToken(String token) {
        this.token = token;

        return this;
    }

    public CustomerDTO getUser() {
        return user;
    }

    public UserToken setUser(CustomerDTO user) {
        this.user = user;

        return this;
    }
}
