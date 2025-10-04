package ru.esipov.orders.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.job4j.core.sagadto.OrderStatus;

import java.sql.Timestamp;
import java.util.UUID;

@Table(name = "orders_history")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "order_id")
    private UUID orderId;
    @Column(name = "status")
    private OrderStatus status;
    @Column(name = "created_at")
    private Timestamp createdAt;

    public OrderHistoryEntity(UUID orderId, OrderStatus status, Timestamp createdAt) {
        this.orderId = orderId;
        this.status = status;
        this.createdAt = createdAt;
    }
}
