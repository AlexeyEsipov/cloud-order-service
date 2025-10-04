package ru.esipov.orders.dto;

import ru.job4j.core.sagadto.OrderStatus;

import java.util.UUID;

public record CreateOrderResponse(UUID orderId, UUID customerId, UUID productId, Integer productQuantity, OrderStatus status) {
}
