package me.lesovoy.kebably.controller;

import me.lesovoy.kebably.model.Order;
import me.lesovoy.kebably.model.enumeration.Status;
import me.lesovoy.kebably.persistence.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    private final OrderRepository repository;

    OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/orders")
    List<Order> allOrders() {
        return repository.findAll();
    }

    @PostMapping("/orders")
    Order newOrder(@RequestBody Order newOrder) {
        return repository.save(newOrder);
    }

    @DeleteMapping("/orders/{orderId}")
    void deleteOrder(@PathVariable Long orderId) {
        repository.deleteById(orderId);
    }

    @PutMapping("/orders/{orderId}")
    Order replaceOrder(@RequestBody Order updatedOrder, @PathVariable Long orderId) {
        return repository.findById(orderId)
                .map(order -> {
                    if (updatedOrder.getItems() != null) order.setItems(updatedOrder.getItems());
                    if (updatedOrder.getStatus() != null) order.setStatus(updatedOrder.getStatus());
                    if (updatedOrder.getUuid() != null) order.setUuid(updatedOrder.getUuid());
                    return repository.save(order);
                })
                .orElseGet(() -> {
                    updatedOrder.setOrderId(orderId);
                    return repository.save(updatedOrder);
                });
    }

    List<Order> getOrdersByStatus(Status status) {
        return repository.findAll()
                .stream()
                .filter(order -> order.getStatus().equals(status))
                .collect(Collectors.toList());
    }
}
