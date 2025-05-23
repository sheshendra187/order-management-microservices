package dev.ms.order_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private List<String> items;
    private double amount;
    private int itemsCount;
}
