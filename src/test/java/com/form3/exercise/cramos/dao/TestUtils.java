package com.form3.exercise.cramos.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import com.form3.exercise.cramos.model.Attributes;
import com.form3.exercise.cramos.model.FX;
import com.form3.exercise.cramos.model.PaymentScheme;
import com.form3.exercise.cramos.model.PaymentType;
import com.form3.exercise.cramos.model.SchemePaymentSubType;
import com.form3.exercise.cramos.model.SchemePaymentType;
import com.form3.exercise.cramos.model.chargesinfo.BearerCode;
import com.form3.exercise.cramos.model.chargesinfo.ChargesInformation;
import com.form3.exercise.cramos.model.chargesinfo.SenderCharge;
import com.form3.exercise.cramos.model.party.AccountNumberCode;
import com.form3.exercise.cramos.model.party.BankIdCode;
import com.form3.exercise.cramos.model.party.Party;
import com.form3.exercise.cramos.model.party.SponsorParty;

/**
 * Utility class to support set up testing.
 */
public class TestUtils {

    static Attributes getPaymentAttributesForTest(String paymentId, String beneficiaryAccNumber, String debtorAccNumber,
            Double receiverChargesAmount, Double senderChargersAmount, String sponsorPartyAccountNumber) {

        Double amount = 100.21;
        Party beneficiaryParty = getBeneficiaryParty(beneficiaryAccNumber);
        ChargesInformation chargesInformation = getChargesInformation(receiverChargesAmount, senderChargersAmount);
        Currency currency = Currency.getInstance("GBP");
        Party debtorParty = getDebtorParty(debtorAccNumber);
        String endToEndReference = "Wil piano Jan";
        FX fx = getFX();
        String numericReference = "1002001";
        String paymentPurpose = "Paying for goods/services";
        PaymentScheme paymentScheme = PaymentScheme.FPS;
        PaymentType paymentType = PaymentType.CREDIT;
        Date processingDate = Date.from(LocalDate.parse("2017-01-18",
                DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay(ZoneId.systemDefault()).toInstant());
        String reference = "Payment for Em's piano lessons";
        SchemePaymentSubType schemePaymentSubType = SchemePaymentSubType.INTERNET_BANKING;
        SchemePaymentType schemePaymentType = SchemePaymentType.IMMEDIATE_PAYMENT;
        SponsorParty sponsorParty = getSponsorParty(sponsorPartyAccountNumber);

        Attributes attributes = new Attributes(
                paymentId, amount, beneficiaryParty, chargesInformation, currency, debtorParty, endToEndReference,
                fx, numericReference, paymentPurpose, paymentScheme, paymentType, processingDate, reference, schemePaymentSubType,
                schemePaymentType, sponsorParty);

        // jpa db relational wiring
        chargesInformation.setAttributes(attributes);
        fx.setAttributes(attributes);
        beneficiaryParty.setAttributes(attributes);
        debtorParty.setAttributes(attributes);
        sponsorParty.setAttributes(attributes);

        return attributes;
    }

    static ChargesInformation getChargesInformation(Double receiverChargesAmount, Double senderChargersAmount) {
        BearerCode bearerCode = BearerCode.SHAR;
        SenderCharge senderCharge1 = new SenderCharge(senderChargersAmount, Currency.getInstance("USD"));
        SenderCharge senderCharge2 = new SenderCharge(5.00, Currency.getInstance("GBP"));
        List<SenderCharge> senderCharges = new ArrayList<>();
        senderCharges.add(senderCharge1);
        senderCharges.add(senderCharge2);
        Currency receiverChargesCurrency = Currency.getInstance("GBP");
        ChargesInformation chargesInformation = new ChargesInformation(bearerCode, senderCharges, receiverChargesAmount,
                receiverChargesCurrency);

        // jpa db relational wiring
        senderCharge1.setChargesInformation(chargesInformation);
        senderCharge2.setChargesInformation(chargesInformation);

        return chargesInformation;
    }

    static Party getBeneficiaryParty(String accountNumber) {
        String accountName = "W Owens";
        AccountNumberCode accountNumberCode = AccountNumberCode.BBAN;
        Integer accountType = 0;
        String address = "1 The Beneficiary Localtown SE2";
        String bankId = "403000";
        BankIdCode bankIdCode = BankIdCode.GBDSC;
        String name = "Wilfred Jeremiah Owens";
        return new Party(accountName, accountNumber, accountNumberCode, accountType,
                address, bankId, bankIdCode, name);
    }

    static Party getDebtorParty(String accountNumber) {
        String accountName = "EJ Brown Black";
        AccountNumberCode accountNumberCode = AccountNumberCode.IBAN;
        Integer accountType = 0;
        String address = "10 Debtor Crescent Sourcetown NE1";
        String bankId = "203301";
        BankIdCode bankIdCode = BankIdCode.GBDSC;
        String name = "Emelia Jane Brown";
        return new Party(accountName, accountNumber, accountNumberCode, accountType,
                address, bankId, bankIdCode, name);
    }

    static FX getFX() {
        String contractReference = "FX123";
        Double exchangeRate = 2.00000;
        Double originalAmount = 200.42;
        Currency currency = Currency.getInstance("USD");
        return new FX(contractReference, exchangeRate, originalAmount, currency);
    }

    static SponsorParty getSponsorParty(String accountNumber) {
        String bankId = "123123";
        BankIdCode bankIdCode = BankIdCode.GBDSC;
        return new SponsorParty(accountNumber, bankId, bankIdCode);
    }

}
