package com.example.ReactData.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReactData.model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    Payment findByOrderId(String orderId);
}
