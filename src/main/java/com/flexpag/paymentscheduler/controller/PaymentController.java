package com.flexpag.paymentscheduler.controller;


import com.flexpag.paymentscheduler.enums.Status;
import com.flexpag.paymentscheduler.exception.IdentifierNotFoundException;
import com.flexpag.paymentscheduler.model.Payment;
import com.flexpag.paymentscheduler.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody Payment payment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.save(payment));
    }

    @GetMapping
    public ResponseEntity<List<Payment>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findAll());
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Status> status(@PathVariable Long id) {
        Payment payment = paymentService.findById(id)
                .orElseThrow(() -> new IdentifierNotFoundException("Identifier does not exist"));
        return ResponseEntity.status(HttpStatus.OK).body(payment.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> findById(@PathVariable Long id) {
        Payment payment = paymentService.findById(id)
                .orElseThrow(() -> new IdentifierNotFoundException("Identifier does not exist"));
        return ResponseEntity.status(HttpStatus.OK).body(payment);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        Payment payment = paymentService.findById(id)
                .orElseThrow(() -> new IdentifierNotFoundException("Identifier does not exist"));
        paymentService.deleteById(id);
    }


}
