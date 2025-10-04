package ru.esipov.orders.service;

import ru.esipov.orders.dto.OrderHistoryResponse;
import ru.job4j.core.sagadto.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryService {
    void add(UUID orderId, OrderStatus orderStatus);

    List<OrderHistoryResponse> findByOrderId(UUID orderId);
}
