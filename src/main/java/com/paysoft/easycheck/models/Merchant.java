package com.paysoft.easycheck.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "merchants")
public class Merchant  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "verified")
    private boolean verified;

    @OneToMany(mappedBy = "merchant")
    private List<Admin> admins;

    @OneToMany(mappedBy = "merchant", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Transaction> transactions;

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

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Merchant)) return false;
        Merchant merchant = (Merchant) o;
        return isVerified() == merchant.isVerified() &&
            Objects.equals(getID(), merchant.getID()) &&
            Objects.equals(getName(), merchant.getName()) &&
            Objects.equals(getAddress(), merchant.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getName(), getAddress(), isVerified());
    }

    @Override
    public String toString() {
        return "Merchant{" +
            "ID=" + ID +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", verified=" + verified +
            '}';
    }
}
