package me.lesovoy.kebably.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/")
    public String order(Model model) {
        return "order";
    }


}
