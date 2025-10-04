package ru.esipov.orders.service.handler;

import ru.esipov.orders.service.OrderHistoryService;
import ru.esipov.orders.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.job4j.core.command.ApproveOrderCommand;
import ru.job4j.core.command.OrderHistoryCommand;
import ru.job4j.core.command.RejectOrderCommand;

@Component
@KafkaListener(topics = "${orders.commands.topic.name}")
@Slf4j
public class OrderCommandsHandler {
    private final OrderService orderService;
    private final OrderHistoryService orderHistoryService;

    public OrderCommandsHandler(OrderService orderService, OrderHistoryService orderHistoryService) {
        this.orderService = orderService;
        this.orderHistoryService = orderHistoryService;
    }

    @KafkaHandler
    public void handleCommand(@Payload ApproveOrderCommand approveOrderCommand) {
        log.info("ApproveOrderCommand : {}", approveOrderCommand.orderId());
        orderService.approveOrder(approveOrderCommand.orderId());
    }

    @KafkaHandler
    public void handleCommand(@Payload RejectOrderCommand rejectOrderCommand) {
        log.info("RejectOrderCommand : {}", rejectOrderCommand.orderId());
        orderService.rejectOrder(rejectOrderCommand.orderId());
    }

    @KafkaHandler
    public void handleCommand(@Payload OrderHistoryCommand historyCommand) {
        log.info("OrderHistoryCommand : {} {}", historyCommand.orderId(), historyCommand.orderStatus());
        orderHistoryService.add(historyCommand.orderId(), historyCommand.orderStatus());
    }
}
