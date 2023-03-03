package com.flexpag.paymentscheduler.exception;

public class FinishedPaymentException extends RuntimeException {
    public FinishedPaymentException(String message) {
        super(message);
    }
}
