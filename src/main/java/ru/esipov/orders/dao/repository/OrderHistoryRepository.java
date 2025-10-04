package ru.esipov.orders.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.esipov.orders.dao.entity.OrderHistoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, UUID> {
    List<OrderHistoryEntity> findByOrderId(UUID orderId);
}
