package com.paysoft.easycheck.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "transactions")
@NamedQueries({
    @NamedQuery(name = Transaction.FIND_BY_CUSTOMER, query = "SELECT t FROM Transaction  t WHERE t.customer.ID = :customerID"),
    @NamedQuery(name = Transaction.FIND_BY_MERCHANT, query = "SELECT t FROM Transaction t WHERE t.merchant.ID = :merchantID")
})
public class Transaction {

    public static final String FIND_BY_CUSTOMER = "Transaction.findByCustomer";
    public static final String FIND_BY_MERCHANT = "Transaction.findByMerchant";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "transaction_amount")
    private Double amount;


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(getID(), that.getID()) &&
            Objects.equals(getCustomer(), that.getCustomer()) &&
            Objects.equals(getMerchant(), that.getMerchant()) &&
            Objects.equals(getCreatedAt(), that.getCreatedAt()) &&
            Objects.equals(getUpdatedAt(), that.getUpdatedAt()) &&
            Objects.equals(getAmount(), that.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getCustomer(), getMerchant(), getCreatedAt(), getUpdatedAt(), getAmount());
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "ID=" + ID +
            ", customer=" + customer +
            ", merchant=" + merchant +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", amount=" + amount +
            '}';
    }
}
