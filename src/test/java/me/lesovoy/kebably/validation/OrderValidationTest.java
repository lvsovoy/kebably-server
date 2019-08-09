package me.lesovoy.kebably.validation;

import me.lesovoy.kebably.TestBase;
import me.lesovoy.kebably.model.Order;
import me.lesovoy.kebably.model.construction.ItemBuilder;
import me.lesovoy.kebably.model.construction.OrderBuilder;
import me.lesovoy.kebably.model.enumeration.ItemType;
import me.lesovoy.kebably.model.enumeration.Status;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.File;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class OrderValidationTest extends TestBase {

    @Test
    public void testSerialization() throws Exception {
        String orderJson = FileUtils.readFileToString(new File(TEST_RESOURCES + "schema/order.json"), "UTF8");
        UUID uuid = UUID.fromString("4bd4480c-3607-4b49-a8da-6de0a029230d");
        Order order = new OrderBuilder()
                .withStatus(Status.PENDING)
                .withUuid(uuid)
                .withItem(new ItemBuilder(ItemType.KEBAB).build())
                .withItem(new ItemBuilder(ItemType.BURGER).build())
                .build();
        String json = OBJECT_MAPPER.writeValueAsString(order);
        JSONAssert.assertEquals(orderJson, json, JSONCompareMode.LENIENT);
    }

    @Test
    public void testDeserialization() throws Exception {
        Order order = OBJECT_MAPPER.readValue(new File(TEST_RESOURCES + "schema/order.json"), Order.class);
        assertEquals(0, order.getOrderId());
        assertEquals(Status.PENDING, order.getStatus());
        assertEquals("4bd4480c-3607-4b49-a8da-6de0a029230d", order.getUuid().toString());
        assertEquals(2, order.getItems().size());
        assertEquals(ItemType.KEBAB, order.getItems().get(0).getType());
        assertEquals(ItemType.BURGER, order.getItems().get(1).getType());
    }
}
