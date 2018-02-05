package com.form3.exercise.cramos.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form3.exercise.cramos.dao.PaymentRepository;
import com.form3.exercise.cramos.exception.RESTResourceNotFoundException;
import com.form3.exercise.cramos.model.Attributes;
import com.form3.exercise.cramos.model.Payment;
import com.form3.exercise.cramos.model.chargesinfo.ChargesInformation;
import com.form3.exercise.cramos.model.chargesinfo.SenderCharge;

@Service
public class DefaultPaymentService implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public DefaultPaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAll() {
        return paymentRepository.findAll().stream().map(DefaultPaymentService::unwiredJPAEntities).collect(Collectors.toList());
    }

    public Payment getOne(UUID id) {
        Payment payment = paymentRepository.findOne(id);
        if (payment == null) {
            throw new RESTResourceNotFoundException("Payment not found. id: " + payment.getId());
        }
        return unwiredJPAEntities(payment);
    }

    public Payment create(Payment payment) {
        payment.getAttributes().setPayment(payment); // add jpa relational db wiring
        return unwiredJPAEntities(paymentRepository.save(payment));
    }

    public void remove(UUID id) {
        paymentRepository.delete(id);
    }

    // helper methods //

    /**
     * This will avoid circular dependencies for when returning objects to the web-layer
     * where the get serialized as part of the http response.
     */
    public static Payment unwiredJPAEntities(Payment payment) {
        Attributes attributes = payment.getAttributes();
        attributes.setPayment(null);
        ChargesInformation chargesInformation = attributes.getChargesInformation();
        chargesInformation.setAttributes(null);
        for (SenderCharge sc : chargesInformation.getSenderCharges()) {
            sc.setChargesInformation(null);
        }
        attributes.getBeneficiaryParty().setAttributes(null);
        attributes.getDebtorParty().setAttributes(null);
        attributes.getSponsorParty().setAttributes(null);
        attributes.getFx().setAttributes(null);

        return payment;
    }
}
