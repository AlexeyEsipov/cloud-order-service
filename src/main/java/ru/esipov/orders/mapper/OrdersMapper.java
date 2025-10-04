package ru.esipov.orders.mapper;

import ru.esipov.orders.dao.entity.OrderEntity;
import ru.esipov.orders.dao.entity.OrderHistoryEntity;
import ru.esipov.orders.dto.CreateOrderRequest;
import ru.esipov.orders.dto.CreateOrderResponse;
import ru.esipov.orders.dto.OrderHistoryResponse;
import org.mapstruct.Mapper;
import ru.job4j.core.events.OrderCreatedEvent;
import ru.job4j.core.sagadto.Order;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {
    Order requestToOrder(CreateOrderRequest request);
    OrderEntity orderToEntity(Order order);
    OrderCreatedEvent entityToEvent(OrderEntity entity);
    CreateOrderResponse entityToResponse(OrderEntity entity);
    List<OrderHistoryResponse> entityListToHistoryListResponse(List<OrderHistoryEntity> entities);
}
