package dev.ms.order_service.service;


import dev.ms.order_service.dto.OrderRequest;
import dev.ms.order_service.dto.OrderResponse;
import dev.ms.order_service.model.Order;
import dev.ms.order_service.model.OrderDetails;
import dev.ms.order_service.repository.OrderRepository;
import dev.ms.order_service.util.OrderIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderIdGenerator orderIdGenerator;

    public OrderResponse placeOrder(String username, OrderRequest request) {
        String orderId = orderIdGenerator.generateOrderId();

        OrderDetails details = new OrderDetails(
                request.getItemsCount(),
                request.getAmount(),
                request.getItems()
        );

        Order order = new Order(orderId, username, details);

        Order savedOrder = orderRepository.save(order);

        return new OrderResponse(
                savedOrder.getOrderId(),
                savedOrder.getUsername(),
                savedOrder.getOrderDetails().getItems(),
                savedOrder.getOrderDetails().getAmount(),
                savedOrder.getOrderDetails().getItemsCount()
        );
    }


    public List<OrderResponse> getOrdersByUsername(String username) {
        List<Order> orders = orderRepository.findByUsername(username);

        return orders.stream().map(order -> {
            OrderDetails details = order.getOrderDetails();
            return new OrderResponse(
                    order.getOrderId(),
                    order.getUsername(),
                    details.getItems(),
                    details.getAmount(),
                    details.getItemsCount()
            );
        }).toList();
    }

    public OrderResponse getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + orderId));

        OrderDetails details = order.getOrderDetails();

        return new OrderResponse(
                order.getOrderId(),
                order.getUsername(),
                details.getItems(),
                details.getAmount(),
                details.getItemsCount()
        );
    }
}

