package com.form3.exercise.cramos.model.party;

public enum AccountNumberCode {
    BBAN("BBAN");

    private String name;

    /**
     * Constructor setting enum name;
     * @param name
     */
    AccountNumberCode(String name) {
        this.name = name;
    }
}
