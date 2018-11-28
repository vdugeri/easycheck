package com.paysoft.easycheck.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardDTO {

    private Long ID;
    @JsonProperty("last_four")
    private String lastFour;
    private String token;
    @JsonProperty("user_id")
    private Long userId;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getLastFour() {
        return lastFour;
    }

    public void setLastFour(String lastFour) {
        this.lastFour = lastFour;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CardDTO{" +
            "ID=" + ID +
            ", lastFour='" + lastFour + '\'' +
            ", token='" + token + '\'' +
            ", userId=" + userId +
            '}';
    }
}
