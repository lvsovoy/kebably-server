package me.lesovoy.kebably.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.lesovoy.kebably.model.enumeration.Status;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Order {
    @JsonProperty("status")
    private Status status;

    @Embedded
    @ElementCollection
    @JsonProperty("items")
    private List<Item> items;

    @JsonProperty("uuid")
    private UUID uuid;

    private @Id
    @GeneratedValue
    long orderId;

    public Order() {
    }

    public Order(Status status, List<Item> items, UUID uuid, Long orderId) {
        this.status = status;
        this.items = items;
        this.uuid = uuid;
        this.orderId = orderId;
    }
}