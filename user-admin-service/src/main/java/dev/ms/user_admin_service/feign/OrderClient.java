package dev.ms.user_admin_service.feign;

import dev.ms.user_admin_service.dto.OrderRequest;
import dev.ms.user_admin_service.dto.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderClient {

    @GetMapping("/orders/{orderid}")
    OrderResponse getOrderById(@PathVariable String orderid);

    @GetMapping("/{username}/orders")
    List<OrderResponse> getOrdersByUsername(@PathVariable String username);

    @PostMapping("/{username}/orders")
    ResponseEntity<String> placeOrder(
            @PathVariable String username,
            @RequestBody OrderRequest orderRequest
    );
}

