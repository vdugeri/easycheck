package com.paysoft.easycheck.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class AdminDTO implements Serializable {

    @JsonProperty("id")
    private Long ID;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email_address")
    private String emailAddress;
    private String password;
    @JsonProperty("merchant_id")
    private Long merchantID;
    @JsonProperty("role_ids")
    private List<Long> roleIDs;


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(Long merchantID) {
        this.merchantID = merchantID;
    }

    public List<Long> getRoleIDs() {
        return roleIDs;
    }

    public void setRoleIDs(List<Long> roleIDs) {
        this.roleIDs = roleIDs;
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", emailAddress='" + emailAddress + '\'' +
            ", password='" + password + '\'' +
            ", merchantID=" + merchantID +
            ", roleIDs=" + roleIDs +
            '}';
    }
}
