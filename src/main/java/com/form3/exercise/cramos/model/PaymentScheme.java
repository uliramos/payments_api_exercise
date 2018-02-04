package com.form3.exercise.cramos.model;

public enum PaymentScheme {
    FPS("FPS");

    private String name;

    /**
     * Constructor setting enum name;
     * @param name
     */
    PaymentScheme(String name) {
        this.name = name;
    }
}
