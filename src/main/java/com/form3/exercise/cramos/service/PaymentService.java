package com.form3.exercise.cramos.service;

import java.util.List;
import java.util.UUID;

import com.form3.exercise.cramos.model.Payment;

public interface PaymentService {

    /**
     * Get all @{@link Payment}s.
     * @return
     */
    List<Payment> getAll();

    /**
     * Get @{@link Payment} by Id.
     * @param id
     * @return
     */
    Payment getOne(UUID id);

    /**
     * Create @{@link Payment} record.
     * @param payment
     * @return
     */
    Payment create(Payment payment);

    /**
     * Remove @{@link Payment} by Id.
     * @param id
     */
    void remove(UUID id);
}
