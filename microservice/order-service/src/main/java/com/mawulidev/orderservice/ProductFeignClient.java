package com.mawulidev.orderservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductFeignClient {

    @GetMapping("/api/products/{id}")
    ResponseEntity<Product> getProductById(@PathVariable Long id);

    @PostMapping("/api/products")
    void createOrUpdateProduct(@RequestBody Product product);  // Create or update product

}
