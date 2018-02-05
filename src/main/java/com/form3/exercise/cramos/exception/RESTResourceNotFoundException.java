package com.form3.exercise.cramos.exception;

/**
 * Custom @{@link RuntimeException} to be flagged in the event of not finding the specified REST resource
 * whilst processing a API request.
 */
public class RESTResourceNotFoundException extends RuntimeException {

    /**
     * {@inheritDoc}
     */
    public RESTResourceNotFoundException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public RESTResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public RESTResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
