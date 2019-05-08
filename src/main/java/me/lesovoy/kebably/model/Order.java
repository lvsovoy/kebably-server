package me.lesovoy.kebably.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Order {
    @JsonProperty("order")
    private List<Item> order;

    public void addItem(Item item) {
       this.order.add(item);
    }

    public List<Item> getOrder() {
        return order;
    }

    public void setOrder(List<Item> order) {
        this.order = order;
    }
}
