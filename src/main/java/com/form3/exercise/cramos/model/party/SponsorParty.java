package com.form3.exercise.cramos.model.party;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.form3.exercise.cramos.model.Attributes;

@Entity
public class SponsorParty {

    @Id
    @JsonIgnore
    private UUID id;

    @JoinColumn(name = "id")
    @OneToOne
    @MapsId
    private Attributes attributes;

    private final String accountNumber;
    private final String bankId;
    private final BankIdCode bankIdCode;

    /**
     * Create SponsorParty instance
     * @param accountNumber
     * @param bankId
     * @param bankIdCode
     */
    public SponsorParty(String accountNumber, String bankId, BankIdCode bankIdCode) {
        this.accountNumber = accountNumber;
        this.bankId = bankId;
        this.bankIdCode = bankIdCode;
    }

    // getters and setters//

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankId() {
        return bankId;
    }

    public BankIdCode getBankIdCode() {
        return bankIdCode;
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
        SponsorParty that = (SponsorParty) o;
        return Objects.equals(accountNumber, that.accountNumber) && Objects.equals(bankId, that.bankId)
                && bankIdCode == that.bankIdCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, bankId, bankIdCode);
    }

    @Override
    public String toString() {
        return "SponsorParty{" + "accountNumber='" + accountNumber + '\'' + ", bankId='" + bankId + '\''
                + ", bankIdCode=" + bankIdCode + '}';
    }
}
