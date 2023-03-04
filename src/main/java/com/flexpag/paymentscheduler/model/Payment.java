package com.flexpag.paymentscheduler.model;

import com.flexpag.paymentscheduler.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Double paymentValue;

    @Column(name = "datatime")
    private LocalDateTime dataTime;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;

}
