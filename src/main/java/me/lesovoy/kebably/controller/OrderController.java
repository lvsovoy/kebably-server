package me.lesovoy.kebably.controller;

import me.lesovoy.kebably.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @PostMapping("/order")
    ResponseEntity<Order> order(@RequestBody Order newOrder) {
        return new ResponseEntity<Order>(newOrder, HttpStatus.OK);
    }

}
