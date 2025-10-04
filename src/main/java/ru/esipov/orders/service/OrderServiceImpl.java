package ru.esipov.orders.service;

import ru.esipov.orders.dao.entity.OrderEntity;
import ru.esipov.orders.dao.repository.OrderRepository;
import ru.esipov.orders.dto.CreateOrderRequest;
import ru.esipov.orders.dto.CreateOrderResponse;
import ru.esipov.orders.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.job4j.core.events.OrderApprovedEvent;
import ru.job4j.core.events.OrderCreatedEvent;
import ru.job4j.core.sagadto.Order;
import ru.job4j.core.sagadto.OrderStatus;

import java.util.UUID;

@Service

public class OrderServiceImpl implements OrderService {
    @Value("${orders.events.topic.name}")
    private String ordersEventsTopicName;
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OrdersMapper ordersMapper;

    public OrderServiceImpl(OrderRepository orderRepository, KafkaTemplate<String, Object> kafkaTemplate, OrdersMapper ordersMapper) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.ordersMapper = ordersMapper;
    }

    @Override
    public CreateOrderResponse placeOrder(CreateOrderRequest request) {
        Order order = ordersMapper.requestToOrder(request);
        OrderEntity entity = ordersMapper.orderToEntity(order);
        entity.setStatus(OrderStatus.CREATED);
        orderRepository.save(entity);
        OrderCreatedEvent placedOrder = ordersMapper.entityToEvent(entity);
        kafkaTemplate.send(ordersEventsTopicName, placedOrder);
        return ordersMapper.entityToResponse(entity);
    }

    @Override
    public void approveOrder(UUID orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        Assert.notNull(orderEntity, "No order is found with id " + orderId + " in the database table");
        orderEntity.setStatus(OrderStatus.APPROVED);
        orderRepository.save(orderEntity);
        OrderApprovedEvent orderApprovedEvent = new OrderApprovedEvent(orderId);
        kafkaTemplate.send(ordersEventsTopicName, orderApprovedEvent);
    }

    @Override
    public void rejectOrder(UUID orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        Assert.notNull(orderEntity, "No order is found with id " + orderId + " in the database table");
        orderEntity.setStatus(OrderStatus.REJECTED);
        orderRepository.save(orderEntity);
    }
}
