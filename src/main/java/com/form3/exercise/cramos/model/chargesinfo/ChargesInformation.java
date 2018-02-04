package com.form3.exercise.cramos.model.chargesinfo;

import java.util.Currency;
import java.util.List;

public class ChargesInformation {

    private final BearerCode bearerCode;
    private final List<SenderCharge> senderCharge;
    private final Double receiverChargesAmount;
    private final Currency receiverChargesCurrency;

    /**
     * Constructor for ChargerInformation new instances.
     * @param bearerCode
     * @param senderCharge
     * @param receiverChargesAmount
     * @param receiverChargesCurrency
     */
    public ChargesInformation(BearerCode bearerCode, List<SenderCharge> senderCharge, Double receiverChargesAmount,
            Currency receiverChargesCurrency) {
        this.bearerCode = bearerCode;
        this.senderCharge = senderCharge;
        this.receiverChargesAmount = receiverChargesAmount;
        this.receiverChargesCurrency = receiverChargesCurrency;
    }
}
