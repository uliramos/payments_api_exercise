package com.form3.exercise.cramos.dao;

import java.util.UUID;

import org.springframework.data.repository.Repository;

import com.form3.exercise.cramos.model.Payment;

public interface PaymentRepository extends Repository<Payment, UUID> {

}
