package com.form3.exercise.cramos.model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.form3.exercise.cramos.model.chargesinfo.ChargesInformation;
import com.form3.exercise.cramos.model.party.Party;
import com.form3.exercise.cramos.model.party.SponsorParty;

public class PaymentAttributes {

    @Id
    @JsonIgnore
    private UUID id;

    @JoinColumn(name = "id")
    @OneToOne
    @MapsId
    private Payment payment;

    private final String paymentId;
    private final Double amount;
    private final Party beneficiaryParty;
    private final ChargesInformation chargesInformation;
    private final Double currency;
    private final Party debtorParty;
    private final String endToEndReference;
    private final FX fx;
    private final String numericReference;
    private final String paymentPurpose;
    private final PaymentScheme paymentScheme;
    private final PaymentType paymentType;
    private final Date processingDate;
    private final String reference;
    private final SchemePaymentSubType schemePaymentSubType;
    private final SchemePaymentType schemePaymentType;
    private final SponsorParty sponsorParty;

    /**
     * Create Payment instance.
     * @param paymentId
     * @param amount
     * @param beneficiaryParty
     * @param chargesInformation
     * @param currency
     * @param debtorParty
     * @param endToEndReference
     * @param fx
     * @param numericReference
     * @param paymentPurpose
     * @param paymentScheme
     * @param paymentType
     * @param processingDate
     * @param reference
     * @param schemePaymentSubType
     * @param schemePaymentType
     * @param sponsorParty
     */
    public PaymentAttributes(String paymentId, Double amount, Party beneficiaryParty,
            ChargesInformation chargesInformation, Double currency, Party debtorParty, String endToEndReference, FX fx,
            String numericReference, String paymentPurpose, PaymentScheme paymentScheme, PaymentType paymentType,
            Date processingDate, String reference, SchemePaymentSubType schemePaymentSubType,
            SchemePaymentType schemePaymentType, SponsorParty sponsorParty) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.beneficiaryParty = beneficiaryParty;
        this.chargesInformation = chargesInformation;
        this.currency = currency;
        this.debtorParty = debtorParty;
        this.endToEndReference = endToEndReference;
        this.fx = fx;
        this.numericReference = numericReference;
        this.paymentPurpose = paymentPurpose;
        this.paymentScheme = paymentScheme;
        this.paymentType = paymentType;
        this.processingDate = processingDate;
        this.reference = reference;
        this.schemePaymentSubType = schemePaymentSubType;
        this.schemePaymentType = schemePaymentType;
        this.sponsorParty = sponsorParty;
    }

    // getters and setters//

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public UUID getId() {
        return id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public Party getBeneficiaryParty() {
        return beneficiaryParty;
    }

    public ChargesInformation getChargesInformation() {
        return chargesInformation;
    }

    public Double getCurrency() {
        return currency;
    }

    public Party getDebtorParty() {
        return debtorParty;
    }

    public String getEndToEndReference() {
        return endToEndReference;
    }

    public FX getFx() {
        return fx;
    }

    public String getNumericReference() {
        return numericReference;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public PaymentScheme getPaymentScheme() {
        return paymentScheme;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public Date getProcessingDate() {
        return processingDate;
    }

    public String getReference() {
        return reference;
    }

    public SchemePaymentSubType getSchemePaymentSubType() {
        return schemePaymentSubType;
    }

    public SchemePaymentType getSchemePaymentType() {
        return schemePaymentType;
    }

    public SponsorParty getSponsorParty() {
        return sponsorParty;
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
        PaymentAttributes that = (PaymentAttributes) o;
        return Objects.equals(id, that.id) && Objects.equals(paymentId, that.paymentId) && Objects
                .equals(amount, that.amount) && Objects.equals(beneficiaryParty, that.beneficiaryParty) && Objects
                .equals(chargesInformation, that.chargesInformation) && Objects.equals(currency, that.currency)
                && Objects.equals(debtorParty, that.debtorParty) && Objects
                .equals(endToEndReference, that.endToEndReference) && Objects.equals(fx, that.fx) && Objects
                .equals(numericReference, that.numericReference) && Objects.equals(paymentPurpose, that.paymentPurpose)
                && paymentScheme == that.paymentScheme && paymentType == that.paymentType && Objects
                .equals(processingDate, that.processingDate) && Objects.equals(reference, that.reference)
                && schemePaymentSubType == that.schemePaymentSubType && schemePaymentType == that.schemePaymentType
                && Objects.equals(sponsorParty, that.sponsorParty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentId, amount, beneficiaryParty, chargesInformation, currency, debtorParty,
                endToEndReference, fx, numericReference, paymentPurpose, paymentScheme, paymentType, processingDate,
                reference, schemePaymentSubType, schemePaymentType, sponsorParty);
    }

    @Override public String toString() {
        return "PaymentAttributes{" + "id=" + id + ", paymentId='" + paymentId + '\'' + ", amount=" + amount
                + ", beneficiaryParty=" + beneficiaryParty + ", chargesInformation=" + chargesInformation
                + ", currency=" + currency + ", debtorParty=" + debtorParty + ", endToEndReference='"
                + endToEndReference + '\'' + ", fx=" + fx + ", numericReference='" + numericReference + '\''
                + ", paymentPurpose='" + paymentPurpose + '\'' + ", paymentScheme=" + paymentScheme + ", paymentType="
                + paymentType + ", processingDate=" + processingDate + ", reference='" + reference + '\''
                + ", schemePaymentSubType=" + schemePaymentSubType + ", schemePaymentType=" + schemePaymentType
                + ", sponsorParty=" + sponsorParty + '}';
    }
}
