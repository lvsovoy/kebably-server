package me.lesovoy.kebably.construction;

import me.lesovoy.kebably.model.Order;
import me.lesovoy.kebably.model.construction.ItemBuilder;
import me.lesovoy.kebably.model.construction.OrderBuilder;
import me.lesovoy.kebably.model.enumeration.ItemType;
import me.lesovoy.kebably.model.enumeration.Status;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class OrderConstructionTest {
    @Test
    public void testConstruction() {
        UUID uuid = UUID.randomUUID();
        Order order = new OrderBuilder()
                .withStatus(Status.PENDING)
                .withUuid(uuid)
                .withItem(new ItemBuilder(ItemType.KEBAB).build())
                .withItem(new ItemBuilder(ItemType.BURGER).build())
                .build();

        assertEquals(Status.PENDING, order.getStatus());
        assertEquals(uuid, order.getUuid());
        assertEquals(2, order.getItems().size());
        assertEquals(ItemType.KEBAB, order.getItems().get(0).getType());
        assertEquals(ItemType.BURGER, order.getItems().get(1).getType());
    }
}
