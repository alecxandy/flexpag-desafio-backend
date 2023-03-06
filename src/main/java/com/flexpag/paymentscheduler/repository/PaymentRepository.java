package com.flexpag.paymentscheduler.repository;

import com.flexpag.paymentscheduler.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByDataTimeBetween(LocalDateTime initialDate, LocalDateTime finalDate);
}
