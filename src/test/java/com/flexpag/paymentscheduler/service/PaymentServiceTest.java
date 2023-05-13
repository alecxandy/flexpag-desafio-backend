package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.domain.Payment;
import com.flexpag.paymentscheduler.domain.dto.PaymentStatementDTO;
import com.flexpag.paymentscheduler.enums.Status;
import com.flexpag.paymentscheduler.exception.FinishedPaymentException;
import com.flexpag.paymentscheduler.exception.IdentifierNotFoundException;
import com.flexpag.paymentscheduler.exception.InvalidDateTimeException;
import com.flexpag.paymentscheduler.repository.PaymentRepository;
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

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PaymentServiceTest {
    Page<Payment> PAGE = Page.empty();
    Pageable PAGEABLE = PageRequest.of(2, 10);
    Payment PAYMENT = new Payment(1L, 10.00, LocalDateTime.of(2024, Month.APRIL, 23, 14, 0, 0), Status.PENDING);
    Payment PAYMENT_PAID = new Payment(1L, 10.00, LocalDateTime.of(2022, Month.APRIL, 23, 14, 0, 0), Status.PAID);
    PaymentStatementDTO PAYMENTSTATEMANTDTO = new PaymentStatementDTO(LocalDateTime.of(2022, 12, 1, 0, 0), LocalDateTime.of(2023, 12, 1, 0, 0));
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
        when(paymentRepository.findAll(PAGEABLE)).thenReturn(PAGE);
        Page<Payment> page = paymentService.findAll(PAGEABLE);
        assertNotNull(page);
        assertEquals(page.getTotalPages(), 1);
        assertEquals(page.getTotalElements(), 0L);
    }

    @Test
    void status() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(PAYMENT));
        Status status = paymentService.status(1L);
        assertNotNull(status);
        assertEquals(status, Status.PENDING);
    }

    @Test
    void notStatus() {
        when(paymentRepository.findById(Mockito.anyLong()))
                .thenThrow(new IdentifierNotFoundException("Identifier does not exist"));
        try {
            paymentService.status(Mockito.anyLong());
        } catch (Exception ex) {
            assertEquals(IdentifierNotFoundException.class, ex.getClass());
            assertEquals("Identifier does not exist", ex.getMessage());
        }
    }

    @Test
    void findById() {
        when(paymentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(PAYMENT));
        Payment payment = paymentService.findById(Mockito.anyLong());
        assertNotNull(payment);
        assertEquals(payment.getClass(), Payment.class);
        assertEquals(payment.getId(), PAYMENT.getId());
        assertEquals(payment.getDataTime(), PAYMENT.getDataTime());
        assertEquals(payment.getPaymentValue(), PAYMENT.getPaymentValue());
    }

    @Test
    void notFindById() {
        when(paymentRepository.findById(Mockito.anyLong()))
                .thenThrow(new IdentifierNotFoundException("Identifier does not exist"));
        try {
            paymentService.findById(Mockito.anyLong());
        } catch (Exception ex) {
            assertEquals(ex.getClass(), IdentifierNotFoundException.class);
            assertEquals(ex.getMessage(), "Identifier does not exist");
        }
    }

    @Test
    void deleteById() {
        when(paymentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(PAYMENT));
        doNothing().when(paymentRepository).deleteById(Mockito.anyLong());
        paymentService.deleteById(Mockito.anyLong());
        Mockito.verify(paymentRepository, times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void whenPaidReturnsExceptionDeleteById() {
        PAYMENT.setStatus(Status.PAID);
        when(paymentRepository.findById(Mockito.anyLong()))
                .thenThrow(new FinishedPaymentException("This payment has already been finalized and cannot be deleted"));
        try {
            paymentService.deleteById(Mockito.anyLong());
        } catch (Exception ex) {
            assertEquals(ex.getClass(), FinishedPaymentException.class);
            assertEquals(ex.getMessage(), "This payment has already been finalized and cannot be deleted");
        }
    }

    @Test
    void whenTheIdentifierIsInvalid() {
        when(paymentRepository.findById(Mockito.anyLong()))
                .thenThrow(new IdentifierNotFoundException("Identifier does not exist"));
        try {
            paymentService.deleteById(Mockito.anyLong());
        } catch (Exception ex) {
            assertEquals(IdentifierNotFoundException.class, ex.getClass());
            assertEquals("Identifier does not exist", ex.getMessage());
        }
    }

    @Test
    void update() {
        when(paymentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(PAYMENT));
        when(paymentRepository.save(PAYMENT)).thenReturn(PAYMENT);
        Long id = paymentService.update(Mockito.anyLong(), PAYMENT);
        assertNotNull(id);
        assertEquals(id, PAYMENT.getId());
    }


    @Test
    void paymentStatement() {
        when(paymentRepository.
                findByDataTimeBetween(PAYMENTSTATEMANTDTO.getInitialDate(), PAYMENTSTATEMANTDTO.getFinalDate()))
                .thenReturn(List.of(PAYMENT));
        List<Payment> payments = paymentService.paymentStatement(PAYMENTSTATEMANTDTO);
        assertNotNull(payments);
        assertEquals(payments.size(),1);
        assertEquals(payments.get(0).getClass(),PAYMENT.getClass());
    }
}