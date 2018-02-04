package com.form3.exercise.cramos.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Implementation of @{@link Transaction} specific for a Payment.
 */
public class Payment implements Transaction {
    private final UUID id;
    private final TransactionType type = TransactionType.PAYMENT;
    private final Integer version;
    private final UUID organisationId;
    private final PaymentAttributes attributes;

    /**
     * Create a payment instance.
     * @param version
     * @param organisationId
     * @param attributes
     */
    public Payment(Integer version, UUID organisationId, PaymentAttributes attributes) {
        this.id = UUID.randomUUID(); // non-editable
        this.version = version;
        this.organisationId = organisationId;
        this.attributes = attributes;
    }

    @Override
    public TransactionType getType() {
        return type;
    }

    // getter and setters ///

    public Integer getVersion() {
        return version;
    }

    public UUID getOrganisationId() {
        return organisationId;
    }
    
    public PaymentAttributes getAttributes() {
        return attributes;
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
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(type, payment.type) && Objects
                .equals(version, payment.version) && Objects.equals(organisationId, payment.organisationId) && Objects
                .equals(attributes, payment.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, version, organisationId, attributes);
    }

    @Override public String toString() {
        return "Payment{" + "id=" + id + ", type='" + type + '\'' + ", version=" + version + ", organisationId="
                + organisationId + ", attributes=" + attributes + '}';
    }
}
