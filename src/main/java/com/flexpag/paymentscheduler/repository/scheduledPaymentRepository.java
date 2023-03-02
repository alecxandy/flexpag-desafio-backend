package com.flexpag.paymentscheduler.repository;

import com.flexpag.paymentscheduler.model.scheduledPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface scheduledPaymentRepository extends JpaRepository<scheduledPayment, Long> {
}
