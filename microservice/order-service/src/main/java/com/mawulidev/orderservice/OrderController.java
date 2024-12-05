package com.mawulidev.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public String welcome() {
        return "Welcome to the order service";
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestParam Long productId, @RequestParam Integer quantity) {
        Order order = orderService.placeOrder(productId, quantity);
        return ResponseEntity.ok(order);
    }
}

