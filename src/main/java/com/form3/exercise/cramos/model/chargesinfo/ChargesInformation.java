package com.form3.exercise.cramos.model.chargesinfo;

import java.util.Currency;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.form3.exercise.cramos.model.Attributes;

@Entity
public class ChargesInformation {

    @Id
    @JsonIgnore
    private UUID id;

    @JoinColumn(name = "id")
    @OneToOne
    @MapsId
    private Attributes attributes;

    private final BearerCode bearerCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chargesInformation")
    private final List<SenderCharge> senderCharges;
    private final Double receiverChargesAmount;
    private final Currency receiverChargesCurrency;

    /**
     * Constructor for ChargerInformation new instances.
     * @param bearerCode
     * @param senderCharges
     * @param receiverChargesAmount
     * @param receiverChargesCurrency
     */
    public ChargesInformation(BearerCode bearerCode, List<SenderCharge> senderCharges, Double receiverChargesAmount,
            Currency receiverChargesCurrency) {
        this.bearerCode = bearerCode;
        this.senderCharges = senderCharges;
        this.receiverChargesAmount = receiverChargesAmount;
        this.receiverChargesCurrency = receiverChargesCurrency;
    }

    // getters and setters//

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public UUID getId() {
        return id;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public BearerCode getBearerCode() {
        return bearerCode;
    }

    public List<SenderCharge> getSenderCharges() {
        return senderCharges;
    }

    public Double getReceiverChargesAmount() {
        return receiverChargesAmount;
    }

    public Currency getReceiverChargesCurrency() {
        return receiverChargesCurrency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChargesInformation that = (ChargesInformation) o;
        return Objects.equals(id, that.id)
                && bearerCode == that.bearerCode && Objects
                .equals(receiverChargesAmount, that.receiverChargesAmount) && Objects
                .equals(receiverChargesCurrency, that.receiverChargesCurrency);
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(id, bearerCode, senderCharges, receiverChargesAmount, receiverChargesCurrency);
    }

    @Override
    public String toString() {
        return "ChargesInformation{" + "id=" + id + ", bearerCode="
                + bearerCode + ", senderCharges=" + Objects.toString(senderCharges) + ", receiverChargesAmount=" + receiverChargesAmount
                + ", receiverChargesCurrency=" + receiverChargesCurrency + '}';
    }
}
