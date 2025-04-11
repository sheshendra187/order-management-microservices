package dev.ms.order_service.controller;

import dev.ms.order_service.dto.OrderRequest;
import dev.ms.order_service.dto.OrderResponse;
import dev.ms.order_service.model.Order;
import dev.ms.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 1. Place order
    @PostMapping("/{username}/orders")
    public ResponseEntity<OrderResponse> placeOrder(
            @PathVariable String username,
            @RequestBody OrderRequest request
    ) {
        return ResponseEntity.ok(orderService.placeOrder(username, request));
    }

    // 2. Get orders by username
    @GetMapping("/{username}/orders")
    public ResponseEntity<List<OrderResponse>> getOrdersByUsername(@PathVariable String username) {
        return ResponseEntity.ok(orderService.getOrdersByUsername(username));
    }

    // 3. Get order by ID
    @GetMapping("/orders/{orderid}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable String orderid) {
        return ResponseEntity.ok(orderService.getOrderById(orderid));
    }
}

