package com.amart.catalogservice.services;

import com.amart.catalogservice.domain.InventoryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.http.RestTemplateTransportClientFactories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@Slf4j
public class InventoryServiceClient {

    public static final String INVENTORY_SERVICE_API_INVENTORY_PRODUCT_CODE = "http://inventory-service/api/inventory/{productCode}";

    @Autowired
    private RestTemplate restTemplate;

    public Optional<InventoryItem> getProductInventoryByCode(String productCode) {
        try {
            ResponseEntity<InventoryItem> itemResponseEntity =
                    restTemplate.getForEntity(INVENTORY_SERVICE_API_INVENTORY_PRODUCT_CODE,
                            InventoryItem.class,
                            productCode);
            if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
                return Optional.ofNullable(itemResponseEntity.getBody());
            } else {
                log.error("Unable to get inventory level for product_code: " + productCode + ", StatusCode: " + itemResponseEntity.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            log.error("Unable to get inventory level for product_code: " + productCode, e);
        }
        return Optional.empty();
    }
}
