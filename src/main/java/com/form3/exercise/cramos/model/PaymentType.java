package com.form3.exercise.cramos.model;

public enum PaymentType {
    CREDIT("Credit");

    private String name;

    /**
     * Constructor setting enum name;
     * @param name
     */
    PaymentType(String name) {
        this.name = name;
    }
}
