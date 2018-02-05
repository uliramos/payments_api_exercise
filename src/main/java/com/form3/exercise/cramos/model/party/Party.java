package com.form3.exercise.cramos.model.party;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.form3.exercise.cramos.model.Attributes;

@Entity
public class Party {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @JsonIgnore
    private UUID id;

    @OneToOne
    private Attributes attributes;

    private final String accountName;
    private final String accountNumber;
    private final AccountNumberCode accountNumberCode;
    private final Integer accountType;
    private final String address; // for production use this would probably be a separate object.
    private final String bankId;
    private final BankIdCode bankIdCode;
    private final String name;

    /**
     * Constructor for a new Party instance.
     * @param accountName
     * @param accountNumber
     * @param accountNumberCode
     * @param accountType
     * @param address
     * @param bankId
     * @param bankIdCode
     * @param name
     */
    public Party(String accountName, String accountNumber, AccountNumberCode accountNumberCode, Integer accountType,
            String address, String bankId, BankIdCode bankIdCode, String name) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountNumberCode = accountNumberCode;
        this.accountType = accountType;
        this.address = address;
        this.bankId = bankId;
        this.bankIdCode = bankIdCode;
        this.name = name;
    }

    // getters and setters//

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountNumberCode getAccountNumberCode() {
        return accountNumberCode;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public String getAddress() {
        return address;
    }

    public String getBankId() {
        return bankId;
    }

    public BankIdCode getBankIdCode() {
        return bankIdCode;
    }

    public String getName() {
        return name;
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
        Party party = (Party) o;
        return Objects.equals(id, party.id) && Objects
                .equals(accountName, party.accountName) && Objects.equals(accountNumber, party.accountNumber)
                && accountNumberCode == party.accountNumberCode && Objects.equals(accountType, party.accountType)
                && Objects.equals(address, party.address) && Objects.equals(bankId, party.bankId)
                && bankIdCode == party.bankIdCode && Objects.equals(name, party.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountName, accountNumber, accountNumberCode, accountType, address,
                bankId, bankIdCode, name);
    }

    @Override
    public String toString() {
        return "Party{" + "id=" + id + ", accountName='" + accountName
                + '\'' + ", accountNumber='" + accountNumber + '\'' + ", accountNumberCode=" + accountNumberCode
                + ", accountType=" + accountType + ", address='" + address + '\'' + ", bankId='" + bankId + '\''
                + ", bankIdCode=" + bankIdCode + ", name='" + name + '\'' + '}';
    }
}
