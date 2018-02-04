package com.form3.exercise.cramos.model;

public enum SchemePaymentType {
    IMMEDIATE_PAYMENT("ImmediatePayment");

    private String name;

    /**
     * Constructor setting enum name;
     * @param name
     */
    SchemePaymentType(String name) {
        this.name = name;
    }
}
