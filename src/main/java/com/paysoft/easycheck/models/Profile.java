package com.paysoft.easycheck.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "profiles")
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Customer customer;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;
        Profile profile = (Profile) o;
        return isActive() == profile.isActive() &&
            Objects.equals(getID(), profile.getID()) &&
            Objects.equals(getCustomer(), profile.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), isActive(), getCustomer());
    }

    @Override
    public String toString() {
        return "Profile{" +
            "ID=" + ID +
            ", isActive=" + isActive +
            ", customer=" + customer +
            '}';
    }
}
