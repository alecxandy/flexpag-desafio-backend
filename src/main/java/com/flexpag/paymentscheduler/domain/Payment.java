package com.flexpag.paymentscheduler.domain;

import com.flexpag.paymentscheduler.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valuepayment")
    @NotNull(message = "You need to fill in all fields")
    private Double paymentValue;

    @Column(name = "datatime")
    @NotNull(message = "You need to fill in all fields")
    private LocalDateTime dataTime;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;

}
