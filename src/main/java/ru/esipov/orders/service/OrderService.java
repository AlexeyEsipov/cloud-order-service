package ru.esipov.orders.service;

import ru.esipov.orders.dto.CreateOrderRequest;
import ru.esipov.orders.dto.CreateOrderResponse;

import java.util.UUID;

public interface OrderService {
    CreateOrderResponse placeOrder(CreateOrderRequest request);
    void approveOrder(UUID orderId);
    void rejectOrder(UUID orderId);
}
