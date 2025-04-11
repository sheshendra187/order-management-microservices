package dev.ms.order_service.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderIdGenerator {
    public String generateOrderId() {
        return UUID.randomUUID().toString();
    }
}

