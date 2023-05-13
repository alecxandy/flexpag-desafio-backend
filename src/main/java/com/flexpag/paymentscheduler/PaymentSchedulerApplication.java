package com.flexpag.paymentscheduler;

import com.flexpag.paymentscheduler.enums.Status;
import com.flexpag.paymentscheduler.domain.Payment;
import com.flexpag.paymentscheduler.repository.PaymentRepository;
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
        paymentRepository.save(new Payment(1L, 10.00, LocalDateTime.parse("2022-01-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(2L, 20.00, LocalDateTime.parse("2022-02-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(3L, 30.00, LocalDateTime.parse("2022-03-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(4L, 40.00, LocalDateTime.parse("2022-04-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(5L, 50.00, LocalDateTime.parse("2022-05-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(6L, 60.00, LocalDateTime.parse("2022-06-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(7L, 70.00, LocalDateTime.parse("2022-07-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(8L, 80.00, LocalDateTime.parse("2022-08-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(9L, 90.00, LocalDateTime.parse("2022-09-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(10L, 100.00, LocalDateTime.parse("2022-10-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(11L, 200.00, LocalDateTime.parse("2022-11-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(12L, 300.00, LocalDateTime.parse("2022-12-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(13L, 400.00, LocalDateTime.parse("2023-01-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(14L, 500.00, LocalDateTime.parse("2023-02-01T01:30:26"), Status.PAID));
        paymentRepository.save(new Payment(15L, 600.00, LocalDateTime.parse("2023-03-01T01:30:26"), Status.PENDING));
        paymentRepository.save(new Payment(15L, 700.00, LocalDateTime.parse("2023-04-01T01:30:26"), Status.PENDING));
        paymentRepository.save(new Payment(16L, 800.00, LocalDateTime.parse("2023-05-01T01:30:26"), Status.PENDING));
        paymentRepository.save(new Payment(17L, 900.00, LocalDateTime.parse("2023-06-01T01:30:26"), Status.PENDING));
        paymentRepository.save(new Payment(18L, 1000.00, LocalDateTime.parse("2023-07-01T01:30:26"), Status.PENDING));
        paymentRepository.save(new Payment(19L, 1100.00, LocalDateTime.parse("2023-08-01T01:30:26"), Status.PENDING));
        paymentRepository.save(new Payment(21L, 1200.00, LocalDateTime.parse("2023-09-01T01:30:26"), Status.PENDING));
        paymentRepository.save(new Payment(21L, 1300.00, LocalDateTime.parse("2023-10-01T01:30:26"), Status.PENDING));
        paymentRepository.save(new Payment(22L, 1400.00, LocalDateTime.parse("2023-11-01T01:30:26"), Status.PENDING));
        paymentRepository.save(new Payment(23L, 1500.00, LocalDateTime.parse("2023-12-01T01:30:26"), Status.PENDING));
        paymentRepository.save(new Payment(24L, 1600.00, LocalDateTime.parse("2024-01-01T01:30:26"), Status.PENDING));
        paymentRepository.save(new Payment(25L, 1700.00, LocalDateTime.parse("2024-02-01T01:30:26"), Status.PENDING));
        paymentRepository.save(new Payment(26L, 1800.00, LocalDateTime.parse("2024-03-01T01:30:26"), Status.PENDING));
        paymentRepository.save(new Payment(27L, 2000.00, LocalDateTime.parse("2024-04-01T01:30:26"), Status.PENDING));

    }
}
