package com.mawulidev.productservice;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product createProduct(Product product);
    Product createOrUpdateProduct(Product product);
    void deleteProduct(Long id);
}
