package com.form3.exercise.cramos.model;

import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FX {

    @Id
    @JsonIgnore
    private UUID id;

    @JoinColumn(name = "id")
    @OneToOne
    @MapsId
    private Attributes attributes;

    private final String contractReference;
    private final Double exchangeRate;
    private final Double originalAmount;
    private final Currency currency;

    /**
     * Create an FX instance.
     * @param contractReference
     * @param exchangeRate
     * @param originalAmount
     * @param currency
     */
    public FX(String contractReference, Double exchangeRate, Double originalAmount, Currency currency) {
        this.contractReference = contractReference;
        this.exchangeRate = exchangeRate;
        this.originalAmount = originalAmount;
        this.currency = currency;
    }

    // getters and setters//

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public String getContractReference() {
        return contractReference;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public Double getOriginalAmount() {
        return originalAmount;
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
        FX fx = (FX) o;
        return Objects.equals(contractReference, fx.contractReference) && Objects.equals(exchangeRate, fx.exchangeRate)
                && Objects.equals(originalAmount, fx.originalAmount) && Objects.equals(currency, fx.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractReference, exchangeRate, originalAmount, currency);
    }

    @Override
    public String toString() {
        return "FX{" + "contractReference='" + contractReference + '\'' + ", exchangeRate=" + exchangeRate
                + ", originalAmount=" + originalAmount + ", currency=" + currency + '}';
    }
}
