package com.paysoft.easycheck.dtos;

import java.io.Serializable;

public class MerchantDTO implements Serializable {
    private Long ID;
    private String name;
    private String address;
    private boolean verified;

    public MerchantDTO(Long ID, String name, String address, boolean verified) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.verified = verified;
    }

    public MerchantDTO() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "MerchantDTO{" +
            "ID=" + ID +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", verified=" + verified +
            '}';
    }
}
