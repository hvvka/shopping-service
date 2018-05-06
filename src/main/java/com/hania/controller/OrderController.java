package com.hania.controller;

import com.hania.model.Article;
import com.hania.model.Order;
import com.hania.service.OrderService;
import com.hania.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
@RestController
@RequestMapping("/shopping")
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/order")
    public ResponseEntity<String> saveOrder(@RequestParam(name = "client") String client,
                                            @RequestBody List<Article> articles,
                                            UriComponentsBuilder ucBuilder) {
        LOG.info("Creating Order for: {}", client);
        Order order = orderService.saveOrder(client, articles);
        if (order == null) return ResponseEntity.noContent().build();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/shopping/order/{id}").buildAndExpand(order.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/order/{id}")
    public ResponseEntity deleteOrder(@PathVariable("id") long id) {
        LOG.info("Fetching and deleting Order with id {}", id);
        Order order = orderService.findOrder(id);
        if (order == null) {
            String message = String.format("Unable to delete - Order with id %d not found", id);
            LOG.error(message);
            return new ResponseEntity<>(new CustomErrorType(message), HttpStatus.BAD_REQUEST);
        }
        boolean isDeleted = orderService.deleteOrder(id);
        return isDeleted ? new ResponseEntity<Order>(HttpStatus.OK) : new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/order/{id}")
    public ResponseEntity findOrder(@PathVariable("id") long id) {
        LOG.info("Fetching order with id {}", id);
        Order order = orderService.findOrder(id);
        if (order == null) {
            String message = String.format("Order with id %d not found", id);
            LOG.error(message);
            return new ResponseEntity<>(new CustomErrorType(message), HttpStatus.OK);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping(value = "/{client}/orders")
    public ResponseEntity<List<Order>> listClientOrders(@PathVariable String client) {
        List<Order> orders = orderService.listClientOrders(client);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/orders")
    public ResponseEntity<List<Order>> listAllOrders() {
        List<Order> orders = orderService.listAllOrders();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping(value = "/order/{id}")
    public ResponseEntity updateOrder(@PathVariable("id") long id, @RequestBody List<Article> articles,
                                      UriComponentsBuilder ucBuilder) {
        LOG.info("Updating Order with id {}", id);
        Order order = orderService.findOrder(id);
        if (order == null) {
            String message = String.format("Unable to update – Order with id %d not found", id);
            LOG.error(message);
            return new ResponseEntity<>(new CustomErrorType(message), HttpStatus.BAD_REQUEST);
        }
        Order updatedOrder = orderService.updateOrder(id, articles);
        if (updatedOrder.getId() == 0) return ResponseEntity.noContent().build();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/shopping/order/{id}").buildAndExpand(updatedOrder.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
