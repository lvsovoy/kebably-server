package me.lesovoy.kebably.model.construction;

import me.lesovoy.kebably.model.Item;
import me.lesovoy.kebably.model.Order;
import me.lesovoy.kebably.model.enumeration.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderBuilder {
    long orderId;
    Status status;
    UUID uuid;
    List<Item> itemList = new ArrayList<>();

    public OrderBuilder(long orderId) {
        this.orderId = orderId;
    }

    public OrderBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    public OrderBuilder withUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public OrderBuilder withItem(Item item) {
        this.itemList.add(item);
        return this;
    }

    public Order build() {
        Order order = new Order();
        order.setOrderId(this.orderId);
        order.setStatus(this.status);
        order.setUuid(this.uuid);
        order.setItems(this.itemList);
        return order;
    }

}
