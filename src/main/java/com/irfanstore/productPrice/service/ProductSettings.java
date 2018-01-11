package com.irfanstore.productPrice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
public class ProductSettings {

    @Value("${productprice.promocode}")
    private String promoCode;

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
}

// Hit following URL with GET to see the environment variables:
//    http://<<product price service base URL>>.cfapps.io/env

//If you don't have spring cloud bus setup, hit the following URL with POST several times to refresh all the service instances:
//    http://product-price-service.cfapps.io/refresh

//If you setup the spring cloud bus, then hit the following URL with POST once only and it will refresh all the service instances:
//     http://product-price-service.cfapps.io/bus/refresh
// Note the "/bus" part in the URL which gets enabled after enabling the spring cloud bus.

// Refresh endpoint refreshes any beans which are annotated with @RefreshScope



