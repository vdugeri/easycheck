package com.paysoft.easycheck.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class RoleDTO implements Serializable {

    @JsonProperty("id")
    private Long ID;
    private String name;

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

    @Override
    public String toString() {
        return "RoleDTO{" +
            "ID=" + ID +
            ", name='" + name + '\'' +
            '}';
    }
}
