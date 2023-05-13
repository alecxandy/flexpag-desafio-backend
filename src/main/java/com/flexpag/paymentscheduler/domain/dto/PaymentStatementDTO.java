package com.flexpag.paymentscheduler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PaymentStatementDTO {

    @NotNull(message = "You need to fill in all fields")
    private LocalDateTime initialDate;
    @NotNull(message = "You need to fill in all fields")
    private LocalDateTime finalDate;
}
