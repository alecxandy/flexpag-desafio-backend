package com.flexpag.paymentscheduler.controller;

import com.flexpag.paymentscheduler.entity.Payment;
import com.flexpag.paymentscheduler.entity.dto.PaymentStatementDTO;
import com.flexpag.paymentscheduler.enums.Status;
import com.flexpag.paymentscheduler.repository.PaymentRepository;
import com.flexpag.paymentscheduler.service.PaymentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class PaymentControllerTest {

    Page<Payment> PAGE = Page.empty();
    Pageable PAGEABLE = PageRequest.of(2, 10);
    Payment PAYMENT = new Payment(1L, 10.00, LocalDateTime.of(2024, Month.APRIL, 23, 14, 0, 0), Status.PENDING);
    Payment PAYMENT_PAID = new Payment(1L, 10.00, LocalDateTime.of(2022, Month.APRIL, 23, 14, 0, 0), Status.PAID);
    PaymentStatementDTO PAYMENTSTATEMANTDTO = new PaymentStatementDTO(LocalDateTime.of(2022, 12, 1, 0, 0), LocalDateTime.of(2023, 12, 1, 0, 0));

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Mockito.when(paymentService.save(PAYMENT)).thenReturn(1L);
        ResponseEntity<Long> response = paymentController.save(PAYMENT);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), 1L);
        assertEquals(response.getStatusCodeValue(), HttpStatus.CREATED.value());
    }

    @Test
    void findAll() {
        Mockito.when(paymentService.findAll(PAGEABLE)).thenReturn(PAGE);
        ResponseEntity<Page<Payment>> response = paymentController.findAll(PAGEABLE);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getTotalElements(), 0);
        assertEquals(response.getBody().getTotalPages(), 1);
        assertEquals(response.getStatusCodeValue(), HttpStatus.OK.value());
    }

    @Test
    void status() {
        Mockito.when(paymentService.status(1L)).thenReturn(Status.PENDING);
        ResponseEntity<Status> response = paymentController.status(1L);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), Status.PENDING);
        assertEquals(response.getStatusCodeValue(), HttpStatus.OK.value());
    }

    @Test
    void findById() {
        Mockito.when(paymentService.findById(1L)).thenReturn(PAYMENT);
        ResponseEntity<Payment> response = paymentController.findById(1L);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getClass(), Payment.class);
        assertEquals(response.getStatusCodeValue(), HttpStatus.OK.value());
        assertEquals(response.getBody().getId(), PAYMENT.getId());
        assertEquals(response.getBody().getDataTime(), PAYMENT.getDataTime());
        assertEquals(response.getBody().getStatus(), PAYMENT.getStatus());
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