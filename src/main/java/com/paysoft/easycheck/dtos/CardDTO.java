package com.paysoft.easycheck.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardDTO {

    private Long ID;
    @JsonProperty("last_four")
    private String lastFour;
    private String token;
    @JsonProperty("customer_id")
    private Long customerID;
    private boolean blocked;

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

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "CardDTO{" +
            "ID=" + ID +
            ", lastFour='" + lastFour + '\'' +
            ", token='" + token + '\'' +
            ", customerID=" + customerID + '\'' +
            ", blocked=" + blocked +
            '}';
    }
}
