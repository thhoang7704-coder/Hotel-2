package com.example.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.Model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
