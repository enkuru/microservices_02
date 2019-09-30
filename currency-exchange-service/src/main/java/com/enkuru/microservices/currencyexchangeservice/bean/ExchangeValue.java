package com.enkuru.microservices.currencyexchangeservice.bean;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class ExchangeValue {

    @Id
    @NonNull
    private Long id;

    @NonNull
    @Column(name = "currency_from")
    private String from;

    @NonNull
    @Column(name = "currency_to")
    private String to;

    @NonNull
    private BigDecimal conversionMultiple;

    @Transient
    @Setter
    private int port;
}
