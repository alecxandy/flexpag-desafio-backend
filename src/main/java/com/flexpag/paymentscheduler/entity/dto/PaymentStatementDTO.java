package com.flexpag.paymentscheduler.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class PaymentStatementDTO {

    @NotNull(message = "You need to fill in all fields")
    private LocalDateTime initialDate;
    @NotNull(message = "You need to fill in all fields")
    private LocalDateTime finalDate;
}
