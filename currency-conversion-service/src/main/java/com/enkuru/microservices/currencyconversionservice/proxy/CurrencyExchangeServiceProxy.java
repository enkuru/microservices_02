package com.enkuru.microservices.currencyconversionservice.proxy;

import com.enkuru.microservices.currencyconversionservice.bean.CurrencyConversion;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
//@FeignClient(name = "currency-exchange-service")
@FeignClient(name = "netflix-zuul-api-gateway-server")// we changed feign client target to zuul api gateway
@RibbonClient(name = "currency-exchange-service")// we added instance urls into the application.properties
// note: when we want to add a new instance, we need to add the new url of the instance into the application.properties
public interface CurrencyExchangeServiceProxy {

//    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")// we changed feign client target to zuul api gateway, so we need to add the application name into uri
    public CurrencyConversion retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
