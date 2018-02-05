package com.form3.exercise.cramos.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.form3.exercise.cramos.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

}
