package com.form3.exercise.cramos.model;

import java.util.Currency;
import java.util.Objects;

public class FX {

    private final String contractReference;
    private final Double exchangeRate;
    private final Double original_amount;
    private final Currency currency;

    /**
     * Create an FX instance.
     * @param contractReference
     * @param exchangeRate
     * @param original_amount
     * @param currency
     */
    public FX(String contractReference, Double exchangeRate, Double original_amount, Currency currency) {
        this.contractReference = contractReference;
        this.exchangeRate = exchangeRate;
        this.original_amount = original_amount;
        this.currency = currency;
    }

    // getter //

    public String getContractReference() {
        return contractReference;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public Double getOriginal_amount() {
        return original_amount;
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
                && Objects.equals(original_amount, fx.original_amount) && Objects.equals(currency, fx.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractReference, exchangeRate, original_amount, currency);
    }

    @Override
    public String toString() {
        return "FX{" + "contractReference='" + contractReference + '\'' + ", exchangeRate=" + exchangeRate
                + ", original_amount=" + original_amount + ", currency=" + currency + '}';
    }
}
