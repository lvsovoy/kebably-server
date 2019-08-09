package me.lesovoy.kebably.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.lesovoy.kebably.model.enumeration.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class Order {
    private @Id
    @GeneratedValue
    @JsonProperty("orderId")
    long orderId;

    @NotNull
    @JsonProperty("status")
    private Status status;

    @JsonProperty("uuid")
    private UUID uuid;

    @Embedded
    @ElementCollection
    @CollectionTable(name = "user_items", joinColumns = @JoinColumn(name = "uuid"))
    @JsonProperty("items")
    private List<Item> items;

    public Order() {
    }
}