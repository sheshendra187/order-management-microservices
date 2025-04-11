package dev.ms.order_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrderDetails {

    private int itemsCount;
    private double amount;

    @ElementCollection
    private List<String> items;
}

