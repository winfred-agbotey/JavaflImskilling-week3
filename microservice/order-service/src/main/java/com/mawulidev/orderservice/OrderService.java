package com.mawulidev.orderservice;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductFeignClient productFeignClient;

    @Transactional
    public Order placeOrder(Long productId, Integer quantity) {
        // Check if the product exists by fetching it from the Product Service
        Product product = productFeignClient.getProductById(productId).getBody();

        if (product != null) {
            // If the product exists, update its quantity
            product.setQuantity(product.getQuantity() - quantity);
            if (product.getQuantity() < 0) {
                throw new InsufficientStockException("Not enough stock available for product: " + productId);
            }
            // Call the Product Service to update the product
            productFeignClient.createOrUpdateProduct(product);
        } else {
            // If the product does not exist, create a new product
            Product newProduct = Product.builder()
                    .id(productId)
                    .name("New Product created from Order " + productId)
                    .price(100)
                    .quantity(quantity)
                    .build();

            // Call the Product Service to create this new product
            productFeignClient.createOrUpdateProduct(newProduct);
        }

        // Create the order
        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setOrderDate(new Date());

        // Save the order in the Order Repository
        return orderRepository.save(order);
    }
}
