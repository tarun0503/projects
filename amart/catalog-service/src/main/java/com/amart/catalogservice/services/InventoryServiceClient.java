package com.amart.catalogservice.services;

import com.amart.catalogservice.domain.InventoryItem;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.http.RestTemplateTransportClientFactories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.MessageFormat;
import java.util.Optional;

@Service
@Slf4j
public class InventoryServiceClient {

    public static final String INVENTORY_SERVICE_API_INVENTORY_PRODUCT_CODE = "http://inventory-service/api/inventory/{productCode}";

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getDefaultProductInventoryByCode",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            }
    )
    public Optional<InventoryItem> getProductInventoryByCode(String productCode) {
//        try {
        ResponseEntity<InventoryItem> itemResponseEntity =
                restTemplate.getForEntity(INVENTORY_SERVICE_API_INVENTORY_PRODUCT_CODE,
                        InventoryItem.class,
                        productCode);
        if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            return Optional.ofNullable(itemResponseEntity.getBody());
        } else {
            log.error("Unable to get inventory level for product_code: " + productCode + ", StatusCode: " + itemResponseEntity.getStatusCode());
            return Optional.empty();
        }
//        } catch (HttpClientErrorException e) {
//            log.error("Unable to get inventory level for product_code: " + productCode, e);
//        }
//        return Optional.empty();
    }

    public Optional<InventoryItem> getDefaultProductInventoryByCode(String productCode) {
        log.info("Returning default InventoryItem for productCode: " + productCode);
        InventoryItem response = new InventoryItem();
        response.setProductCode(productCode);
        response.setAvailableQuantity(0);
        return Optional.ofNullable(response);
    }
}
