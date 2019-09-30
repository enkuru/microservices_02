package com.enkuru.microservices.currencyexchangeservice.controller;

import com.enkuru.microservices.currencyexchangeservice.bean.ExchangeValue;
import com.enkuru.microservices.currencyexchangeservice.repository.ExchangeValueRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {
    private final Environment environment;
    private final ExchangeValueRepository exchangeValueRepository;
    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) throws Exception {
        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);

        if (exchangeValue == null) {
            throw new Exception("not-found");
        }

        exchangeValue.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port"))));

        logger.info("{}", exchangeValue);

        return exchangeValue;
    }
}
