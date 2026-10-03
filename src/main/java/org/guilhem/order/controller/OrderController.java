package org.guilhem.order.controller;

import java.util.UUID;

import org.guilhem.order.domain.Order;
import org.guilhem.order.service.OrderManagement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/order")
public class OrderController {
    private OrderManagement orderManagement;

    public OrderController(OrderManagement orderManagement) {
        this.orderManagement = orderManagement;
    }

    @GetMapping("/{order-id}")
    @ResponseBody 
    Order getOrder(@PathVariable("order-id") UUID orderId) {
        return orderManagement.getOrder(orderId);
    }
}
