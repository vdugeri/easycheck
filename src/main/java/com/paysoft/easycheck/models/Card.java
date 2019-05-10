package com.paysoft.easycheck.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cards")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;

    @Column(name = "last_four")
    private String lastFour;

    @Column(name = "token")
    private String token;

    @Column(name = "blocked")
    private boolean blocked;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return isBlocked() == card.isBlocked() &&
            Objects.equals(getID(), card.getID()) &&
            Objects.equals(getLastFour(), card.getLastFour()) &&
            Objects.equals(getToken(), card.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getLastFour(), getToken(), isBlocked());
    }

    @Override
    public String toString() {
        return "Card{" +
            "ID=" + ID +
            ", lastFour='" + lastFour + '\'' +
            ", token='" + token + '\'' +
            ", customer=" + customer + '\'' +
            ", blocked=" + blocked +
            '}';
    }
}
