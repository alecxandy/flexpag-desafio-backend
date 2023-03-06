package com.flexpag.paymentscheduler.controller;


import com.flexpag.paymentscheduler.entity.dto.PaymentStatementDTO;
import com.flexpag.paymentscheduler.enums.Status;
import com.flexpag.paymentscheduler.entity.Payment;
import com.flexpag.paymentscheduler.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody Payment payment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.save(payment));
    }

    @GetMapping
    public ResponseEntity<Page<Payment>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findAll(pageable));
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Status> status(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.status(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        paymentService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @Valid @RequestBody Payment payment) {
        return paymentService.update(id, payment);
    }

    //Extrato de pagamentos
    @GetMapping("/paymentStatement")
    public ResponseEntity<List<Payment>> paymentStatement(@Valid @RequestBody PaymentStatementDTO paymentStatementDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.paymentStatement(paymentStatementDTO));
    }

}
