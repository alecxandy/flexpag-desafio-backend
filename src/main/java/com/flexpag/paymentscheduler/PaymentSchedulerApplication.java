package com.flexpag.paymentscheduler;

import com.flexpag.paymentscheduler.enums.Status;
import com.flexpag.paymentscheduler.model.Payment;
import com.flexpag.paymentscheduler.repository.PaymentRepository;
import com.flexpag.paymentscheduler.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class PaymentSchedulerApplication implements CommandLineRunner {

    @Autowired
    private PaymentRepository paymentRepository;

    public static void main(String[] args) {
        SpringApplication.run(PaymentSchedulerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        paymentRepository.save(new Payment(1L, 10.00, LocalDateTime.parse("2023-01-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(2L, 10.00, LocalDateTime.parse("2023-02-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(3L, 10.00, LocalDateTime.parse("2023-03-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(4L, 10.00, LocalDateTime.parse("2023-04-01T01:30:26"), Status.PENDING));
        paymentRepository.save(new Payment(5L, 10.00, LocalDateTime.parse("2023-05-01T01:30:26"), Status.PENDING));

    }
}
