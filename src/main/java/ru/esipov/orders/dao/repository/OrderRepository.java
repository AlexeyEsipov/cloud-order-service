package ru.esipov.orders.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.esipov.orders.dao.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
