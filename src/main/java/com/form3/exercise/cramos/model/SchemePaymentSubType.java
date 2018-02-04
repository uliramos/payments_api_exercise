package com.form3.exercise.cramos.model;

public enum SchemePaymentSubType {
    INTERNET_BANKING("InternetBanking");

    private String name;

    /**
     * Constructor setting enum name;
     * @param name
     */
    SchemePaymentSubType(String name) {
        this.name = name;
    }
}
