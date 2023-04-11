package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.entity.Payment;
import com.flexpag.paymentscheduler.enums.Status;
import com.flexpag.paymentscheduler.exception.InvalidDateTimeException;
import com.flexpag.paymentscheduler.repository.PaymentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PaymentServiceTest {

    Payment PAYMENT = new Payment(1L, 10.00, LocalDateTime.of(2024, Month.APRIL, 23, 14, 0, 0), Status.PENDING);
    Payment PAYMENT_PAID = new Payment(1L, 10.00, LocalDateTime.of(2022, Month.APRIL, 23, 14, 0, 0), Status.PAID);

    @InjectMocks
    private PaymentService paymentService;
    @Mock
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void save() {
        when(paymentRepository.save(PAYMENT)).thenReturn(PAYMENT);
        Long id = paymentService.save(PAYMENT);
        assertEquals(id, PAYMENT.getId());
    }

    @Test
    void notSave() {
        when(paymentRepository.save(PAYMENT_PAID)).thenReturn(PAYMENT);
        try {
            paymentService.save(PAYMENT_PAID);
        } catch (Exception e) {
            assertEquals(InvalidDateTimeException.class, e.getClass());
            assertEquals("Date or time cannot be less than the current date", e.getMessage());
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void status() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }

    @Test
    void paymentStatement() {
    }
}