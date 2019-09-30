package com.enkuru.microservices.currencyconversionservice.controller;

import com.enkuru.microservices.currencyconversionservice.bean.CurrencyConversion;
import com.enkuru.microservices.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {
    private final CurrencyExchangeServiceProxy exchangeServiceProxy;
    private Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) throws Exception {

        // Feign - Problem 1, invoking another microservice
        /*Map<String, String> uriValues = new HashMap<>();
        uriValues.put("from", from);
        uriValues.put("to", to);
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriValues);

        CurrencyConversion response = responseEntity.getBody();*/

        CurrencyConversion response = exchangeServiceProxy.retrieveExchangeValue(from, to);

        if (response == null) {
            throw new Exception("not-found");
        }

        response.setQuantity(quantity);
        response.setTotalCalculatedAmount(response.getConversionMultiple().multiply(quantity));

        logger.info("{}", response) ;

        return response;
    }
}
