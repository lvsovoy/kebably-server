package me.lesovoy.kebably.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import me.lesovoy.kebably.ServerApplication;
import me.lesovoy.kebably.TestBase;
import me.lesovoy.kebably.model.Order;
import me.lesovoy.kebably.model.enumeration.ItemType;
import me.lesovoy.kebably.model.enumeration.Status;
import me.lesovoy.kebably.persistence.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderControllerTest extends TestBase {

    @Mock
    private OrderController orderController;

    @Mock
    private OrderRepository repository;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void before() throws Exception {
        orderController = new OrderController(repository);

        MVC = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        List<Order> allOrderList = OBJECT_MAPPER.readValue(new File(TEST_RESOURCES + "responses/findAll.json"), new TypeReference<List<Order>>() {});
        when(repository.findAll()).thenReturn(allOrderList);

        Order addOrder = OBJECT_MAPPER.readValue(new File(TEST_RESOURCES + "responses/addOrder.json"), Order.class);
        when(repository.save(any())).thenReturn(addOrder);

        Order getOrderById = OBJECT_MAPPER.readValue(new File(TEST_RESOURCES + "responses/getOrderById.json"), Order.class);
        when(repository.getOne(any())).thenReturn(getOrderById);
    }

    @Test
    public void testGetByStatus() {
        List<Order> doneList = orderController.getOrdersByStatus(Status.DONE);
        assertFalse(doneList.contains(Status.PENDING));
        List<Order> pendingList = orderController.getOrdersByStatus(Status.PENDING);
        assertFalse(pendingList.contains(Status.DONE));
    }

    @Test
    public void testGetAllOrders() {
        assertEquals(3, orderController.allOrders().size());
    }

    @Test
    public void testAddOrder() {
        Order addedOrder = orderController.newOrder(new Order());
        assertEquals(4, addedOrder.getOrderId());
        assertEquals(Status.PENDING, addedOrder.getStatus());
        assertEquals("111f5251-578e-4155-8447-4531c6a2565a", addedOrder.getUuid().toString());
        assertEquals(2, addedOrder.getItems().size());
        assertEquals(ItemType.KEBAB, addedOrder.getItems().get(0).getType());
        assertEquals(ItemType.BURGER, addedOrder.getItems().get(1).getType());
    }

    @Test
    public void testDeleteOrder() throws Exception {
        int beforeSize = orderController.allOrders().size();
        orderController.deleteOrder(1L);

        when(repository.findAll())
                .thenReturn(OBJECT_MAPPER.readValue(new File(TEST_RESOURCES + "responses/afterDelete.json"), new TypeReference<List<Order>>() {
                }));

        assertEquals(beforeSize - 1, orderController.allOrders().size());
    }

    @Test
    public void testReplaceOrder() throws Exception {
        int beforeSize = orderController.allOrders().size();

        Order initialOrder = orderController.getOrderById(1L);
        assertEquals(1, initialOrder.getOrderId());
        assertEquals(Status.PENDING, initialOrder.getStatus());
        assertEquals("bd1041ce-b252-4269-88a0-c8a8fdc2926c", initialOrder.getUuid().toString());
        assertEquals(2, initialOrder.getItems().size());
        assertEquals(ItemType.PITA, initialOrder.getItems().get(0).getType());
        assertEquals(ItemType.DONNER_PLATE, initialOrder.getItems().get(1).getType());

        orderController.replaceOrder(new Order(), 1L);

        when(repository.getOne(any()))
                .thenReturn(OBJECT_MAPPER.readValue(new File(TEST_RESOURCES + "responses/replaceOrder.json"), Order.class));

        Order replacedOrder = orderController.getOrderById(1L);

        assertEquals(1, replacedOrder.getOrderId());
        assertEquals(Status.DONE, replacedOrder.getStatus());
        assertEquals("bd1041ce-b252-4269-88a0-c8a8fdc2926c", replacedOrder.getUuid().toString());
        assertEquals(2, replacedOrder.getItems().size());
        assertEquals(ItemType.PITA, replacedOrder.getItems().get(0).getType());
        assertEquals(ItemType.DONNER_PLATE, replacedOrder.getItems().get(1).getType());

        assertEquals(beforeSize, orderController.allOrders().size());
    }

    //Negative cases

    @Test
    public void testWithInvalidOrder() throws Exception {
        String uri = "/orders";
        MvcResult mvcResult = MVC.perform(MockMvcRequestBuilders.post(uri, "{\"foo\": \"bar\"}")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
        assertTrue(mvcResult.getResolvedException().getLocalizedMessage().contains("Required request body is missing"));
    }

    @Test
    public void testWithOrderNotFound() {
        String uri = "/orders/999";
        try {
            MVC.perform(MockMvcRequestBuilders.delete(uri)
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("No class me.lesovoy.kebably.model.Order entity with id 999 exists!"));
        }
    }
}
