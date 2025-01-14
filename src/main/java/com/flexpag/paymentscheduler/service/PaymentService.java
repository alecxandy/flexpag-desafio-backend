package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.domain.Payment;
import com.flexpag.paymentscheduler.domain.dto.PaymentStatementDTO;
import com.flexpag.paymentscheduler.enums.Status;
import com.flexpag.paymentscheduler.exception.FinishedPaymentException;
import com.flexpag.paymentscheduler.exception.IdentifierNotFoundException;
import com.flexpag.paymentscheduler.exception.InvalidDateTimeException;
import com.flexpag.paymentscheduler.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Long save(Payment payment) {
        if (LocalDateTime.now().isAfter(payment.getDataTime())) {
            throw new InvalidDateTimeException("Date or time cannot be less than the current date");
        }
        payment.setStatus(Status.PENDING);
        return paymentRepository.save(payment).getId();
    }

    public Page<Payment> findAll(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    public Status status(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IdentifierNotFoundException("Identifier does not exist"));
        return payment.getStatus();
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new IdentifierNotFoundException("Identifier does not exist"));
    }

    //Excluir apenas se o status for pendente
    public void deleteById(Long id) {
        paymentRepository.findById(id).map(p -> {
            if (p.getStatus().equals(Status.PENDING)) {
                paymentRepository.deleteById(id);
            } else {
                throw new FinishedPaymentException("This payment has already been finalized and cannot be deleted");
            }
            return p;
        }).orElseThrow(() -> new IdentifierNotFoundException("Identifier does not exist"));
    }

    //Atualizar apenas se o status for pendente
    public Long update(Long id, Payment payment) {
        if (LocalDateTime.now().isAfter(payment.getDataTime())) {
            throw new InvalidDateTimeException("Date or time cannot be less than the current date");
        }
        return paymentRepository.findById(id).map(p -> {
            if (p.getStatus().equals(Status.PENDING)) {
                p.setId(id);
                p.setPaymentValue(payment.getPaymentValue());
                p.setStatus(Status.PENDING);
                p.setDataTime(payment.getDataTime());
                paymentRepository.save(p);
            } else {
                throw new FinishedPaymentException("This payment has already been finalized and cannot be updated");
            }
            return p.getId();
        }).orElseThrow(() -> new IdentifierNotFoundException("Identifier does not exist"));
    }

   //Extratos
    public List<Payment> paymentStatement(@RequestBody PaymentStatementDTO paymentStatementDTO) {
        return paymentRepository.findByDataTimeBetween(
                paymentStatementDTO.getInitialDate(),
                paymentStatementDTO.getFinalDate());
    }

}
