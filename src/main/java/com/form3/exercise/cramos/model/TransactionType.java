package com.form3.exercise.cramos.model;

public enum TransactionType {
    PAYMENT("Payment");

    private String name;

    /**
     * Constructor settinng enum name;
     * @param name
     */
    TransactionType(String name) {
        this.name = name;
    }
}
