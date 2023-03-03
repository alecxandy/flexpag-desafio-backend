package com.flexpag.paymentscheduler.handler;


import com.flexpag.paymentscheduler.exception.FinishedPaymentException;
import com.flexpag.paymentscheduler.exception.IdentifierNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FinishedPaymentException.class)
    public Map<String, String> finishedPaymentException(FinishedPaymentException ex) {
        Map<String, String> erros = new HashMap<>();
        erros.put("DateTime", LocalDateTime.now().toString());
        erros.put("Message", ex.getMessage());
        erros.put("Status", HttpStatus.BAD_REQUEST.toString());
        return erros;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IdentifierNotFoundException.class)
    public Map<String, String> idNotFoundException(IdentifierNotFoundException ex) {
        Map<String, String> erros = new HashMap<>();
        erros.put("DateTime", LocalDateTime.now().toString());
        erros.put("Message", ex.getMessage());
        erros.put("Status", HttpStatus.NOT_FOUND.toString());
        return erros;
    }

}
