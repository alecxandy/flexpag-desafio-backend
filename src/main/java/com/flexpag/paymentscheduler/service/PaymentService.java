package com.flexpag.paymentscheduler.service;


import com.flexpag.paymentscheduler.enums.Status;
import com.flexpag.paymentscheduler.exception.FinishedPaymentException;
import com.flexpag.paymentscheduler.model.Payment;
import com.flexpag.paymentscheduler.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Long save(Payment payment) {
        payment.setStatus(Status.PAID);
        return paymentRepository.save(payment).getId();
    }

    public Status status(Long id) {
        return paymentRepository.findById(id).get().getStatus();
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    //Excluir apenas se o status for pendente
    public void deleteById(Long id) {
        paymentRepository.findById(id).map(payment -> {
            if (payment.getStatus().equals(Status.PENDING)) {
                paymentRepository.deleteById(id);
            } else {
                throw new FinishedPaymentException("This payment has already been finalized and cannot be deleted");
            }
            return payment;
        });
    }


}
