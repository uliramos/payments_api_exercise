package com.form3.exercise.cramos.model;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * Implementation of @{@link Transaction} specific for a Payment.
 */
@Entity
public class Payment implements Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
                      strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private TransactionType type = TransactionType.PAYMENT;
    private Integer version;
    private UUID organisationId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "payment")
    private Attributes attributes;

    /**
     * Create a payment instance.
     * @param version
     * @param organisationId
     * @param attributes
     */
    public Payment(Integer version, UUID organisationId, Attributes attributes) {
        this.version = version;
        this.organisationId = organisationId;
        this.attributes = attributes;
    }

    @Override
    public TransactionType getType() {
        return type;
    }

    // getter and setters ///

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public UUID getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(UUID organisationId) {
        this.organisationId = organisationId;
    }
    
    public Attributes getAttributes() {
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
        return Objects.equals(id, payment.id) && type == payment.type && Objects.equals(version, payment.version)
                && Objects.equals(organisationId, payment.organisationId) && Objects
                .equals(attributes.getId(), payment.attributes.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, version, organisationId, attributes.getId());
    }


}
