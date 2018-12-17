package com.paysoft.easycheck.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.paysoft.easycheck.models.Customer;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TransactionDTO implements Serializable {

    @JsonProperty("customer_id")
    private Long customerID;
    @JsonProperty("merchant_id")
    private Long merchantID;
    @JsonProperty("id")
    private Long ID;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    private double amount;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(Long merchantID) {
        this.merchantID = merchantID;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
            "customerID=" + customerID +
            ", merchantID=" + merchantID +
            ", ID=" + ID +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", amount=" + amount +
            '}';
    }
}
