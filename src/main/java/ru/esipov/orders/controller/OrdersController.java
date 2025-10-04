package ru.esipov.orders.controller;

import ru.esipov.orders.dto.CreateOrderRequest;
import ru.esipov.orders.dto.CreateOrderResponse;
import ru.esipov.orders.dto.OrderHistoryResponse;
import ru.esipov.orders.service.OrderHistoryService;
import ru.esipov.orders.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;
    private final OrderHistoryService orderHistoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreateOrderResponse placeOrder(@RequestBody @Valid CreateOrderRequest request) {
        return orderService.placeOrder(request);
    }

    @GetMapping("/{orderId}/history")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderHistoryResponse> getOrderHistory(@PathVariable UUID orderId) {
        return orderHistoryService.findByOrderId(orderId);
    }
}
