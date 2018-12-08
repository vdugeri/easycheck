package com.paysoft.easycheck.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customers")
@NamedQueries({
    @NamedQuery(name = Customer.FIND_BY_EMAIL, query = "SELECT c FROM Customer  c WHERE c.email = :email")
})
public class Customer {

    public static final String FIND_BY_EMAIL = "Customer.findByEmail";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_address")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "customer")
    private List<Card> cards;


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

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getID(), customer.getID()) &&
                Objects.equals(getFirstName(), customer.getFirstName()) &&
                Objects.equals(getLastName(), customer.getLastName()) &&
                Objects.equals(getEmail(), customer.getEmail()) &&
                Objects.equals(getPassword(), customer.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getFirstName(), getLastName(), getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
