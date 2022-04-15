package com.swshop.sweetshopspring.controller;

import com.swshop.sweetshopspring.exception.ResourceNotFoundException;
import com.swshop.sweetshopspring.model.Order;
import com.swshop.sweetshopspring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class OrderController {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // get all orders
    @GetMapping("orders")
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    // get a particular order using id
    @GetMapping("/orders/")
    public ResponseEntity<Order> getOrderById(
            @RequestParam(name = "id") Long id
    ) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for id: " + id));
        return ResponseEntity.ok().body(order);
    }

    // post a new order
    @PostMapping("orders")
    public Order createOrder(@Validated @RequestBody Order order) {
        return this.orderRepository.save(order);
    }

    // update order information
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/orders/")
    public ResponseEntity<Order> updateOrder(
            @RequestParam(value = "id") Long id,
            @Validated @RequestBody Order orderInfo
    ) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setName(orderInfo.getName());
        order.setEmail(orderInfo.getEmail());
        order.setPhoneNumber(orderInfo.getPhoneNumber());
        order.setLocation(orderInfo.getLocation());
        order.setCompleted(orderInfo.getCompleted());
        order.setChecCheckoutToken(orderInfo.getChecCheckoutToken());

        return ResponseEntity.ok(this.orderRepository.save(order));
    }

    // delete an order
    @DeleteMapping("orders")
    public Map<String, Boolean> deleteOrder(
            @RequestParam(value = "id") Long id
    ) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        this.orderRepository.delete(order);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
