package com.form3.exercise.cramos.model.party;

import java.util.Objects;

public class Party {
    private final String accountName;
    private final String accountNumber;
    private final AccountNumberCode accountNumberCode;
    private final Integer accountType;
    private final String address; // for production use this would obviously be a separate object.
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

    // getter //

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
        return Objects.equals(accountName, party.accountName) && Objects.equals(accountNumber, party.accountNumber)
                && Objects.equals(accountNumberCode, party.accountNumberCode) && Objects
                .equals(accountType, party.accountType) && Objects.equals(address, party.address) && Objects
                .equals(bankId, party.bankId) && Objects.equals(bankIdCode, party.bankIdCode) && Objects
                .equals(name, party.name);
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(accountName, accountNumber, accountNumberCode, accountType, address, bankId, bankIdCode, name);
    }

    @Override
    public String toString() {
        return "Party{" + "accountName='" + accountName + '\'' + ", accountNumber='" + accountNumber + '\''
                + ", accountNumberCode=" + accountNumberCode + ", accountType=" + accountType + ", address='" + address
                + '\'' + ", bankId='" + bankId + '\'' + ", bankIdCode=" + bankIdCode + ", name='" + name + '\'' + '}';
    }
}
