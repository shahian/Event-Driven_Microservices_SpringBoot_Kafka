package com.shahian.orderservice.controller;

import com.shahian.basedomains.dto.OrderDTO;
import com.shahian.basedomains.dto.OrderEvent;
import com.shahian.orderservice.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/v1/orders")
    public String placeOrder(@RequestBody OrderDTO orderDTO){
        orderDTO.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent=new OrderEvent();
        orderEvent.setOrder(orderDTO);
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending...");
        orderProducer.sendMessage(orderEvent);
        return "Order Placed Successfuly...";
    }
}
