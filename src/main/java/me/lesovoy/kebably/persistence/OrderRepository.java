package me.lesovoy.kebably.persistence;

import me.lesovoy.kebably.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
