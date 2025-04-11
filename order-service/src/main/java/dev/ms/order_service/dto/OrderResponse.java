package dev.ms.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {
    private String orderId;
    private String username;
    private List<String> items;
    private double amount;
    private int itemsCount;
}

