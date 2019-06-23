package com.amart.catalogservice.services;

import com.amart.catalogservice.domain.InventoryItem;
import com.amart.catalogservice.entities.Product;
import com.amart.catalogservice.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private InventoryServiceClient inventoryServiceClient;

    @Autowired
    public ProductService(ProductRepository productRepository, InventoryServiceClient inventoryServiceClient) {
        this.productRepository = productRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    public List<Product> findAllProducts() {
        List<Product> products = productRepository.findAll();
        for ( Product product: products ) {
            setProductInStock(product);

        }
        return products;
    }

    public Optional<Product> findProductByCode(String code) {
        Optional<Product> product = productRepository.findByCode(code);

        if(product.isPresent()){
            setProductInStock(product.get());
        }
        return product;
    }

    private void setProductInStock(Product product) {
        Optional<InventoryItem> item = inventoryServiceClient.getProductInventoryByCode(product.getCode());
        if(item.isPresent()){
            product.setInStock(item.get().getAvailableQuantity()>0? true: false);
        }
    }
}
