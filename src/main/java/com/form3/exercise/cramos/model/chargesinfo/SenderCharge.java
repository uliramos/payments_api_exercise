package com.form3.exercise.cramos.model.chargesinfo;

import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SenderCharge {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @JsonIgnore
    private UUID id;

    private final Double amount;
    private final Currency currency;

    @ManyToOne
    private ChargesInformation chargesInformation;


        /**
     * Create a new SenderCharge instance.
     * @param amount
     * @param currency
     */
    public SenderCharge(Double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    // getters and setters //

    public void setChargesInformation(ChargesInformation chargesInformation) {
        this.chargesInformation = chargesInformation;
    }

    public Double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    // object methods //

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SenderCharge that = (SenderCharge) o;
        return Objects.equals(amount, that.amount) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return "SenderCharge{" + "amount=" + amount + ", currency=" + currency + '}';
    }
}
