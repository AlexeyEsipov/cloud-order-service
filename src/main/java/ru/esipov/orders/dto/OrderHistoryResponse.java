package ru.esipov.orders.dto;

import ru.job4j.core.sagadto.OrderStatus;

import java.sql.Timestamp;
import java.util.UUID;

public record OrderHistoryResponse(UUID id, UUID orderId, OrderStatus status, Timestamp createdAt) {
}
