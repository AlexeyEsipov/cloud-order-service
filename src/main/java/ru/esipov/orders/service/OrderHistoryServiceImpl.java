package ru.esipov.orders.service;

import ru.esipov.orders.dao.entity.OrderHistoryEntity;
import ru.esipov.orders.dao.repository.OrderHistoryRepository;
import ru.esipov.orders.dto.OrderHistoryResponse;
import ru.esipov.orders.mapper.OrdersMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.core.sagadto.OrderStatus;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderHistoryServiceImpl implements OrderHistoryService {
    private final OrderHistoryRepository orderHistoryRepository;
    private final OrdersMapper ordersMapper;


    @Override
    public void add(UUID orderId, OrderStatus orderStatus) {
        OrderHistoryEntity entity = new OrderHistoryEntity(orderId, orderStatus, new Timestamp(new Date().getTime()));
        orderHistoryRepository.save(entity);
    }

    @Override
    public List<OrderHistoryResponse> findByOrderId(UUID orderId) {
        List<OrderHistoryEntity> entities = orderHistoryRepository.findByOrderId(orderId);
        return ordersMapper.entityListToHistoryListResponse(entities);
    }
}
