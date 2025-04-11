package dev.ms.user_admin_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private String orderid;
    private String username;
    private List<String> items;
    private double amount;
    private int itemsCount;
}
