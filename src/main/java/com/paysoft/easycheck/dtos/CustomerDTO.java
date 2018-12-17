package com.paysoft.easycheck.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CustomerDTO implements Serializable {

    @JsonProperty("id")
    private Long ID;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;
    private String password;
    private List<CardDTO> cards;

    public CustomerDTO() {
    }

    public CustomerDTO(Long ID, String firstName, String lastName, String email, String password) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CardDTO> getCards() {
        return cards;
    }

    public void setCards(List<CardDTO> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO)) return false;
        CustomerDTO customerDTO = (CustomerDTO) o;
        return Objects.equals(getFirstName(), customerDTO.getFirstName()) &&
                Objects.equals(getLastName(), customerDTO.getLastName()) &&
                Objects.equals(getEmail(), customerDTO.getEmail()) &&
                Objects.equals(getPassword(), customerDTO.getPassword()) &&
                Objects.equals(getID(), customerDTO.getID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "ID= '" + ID + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
