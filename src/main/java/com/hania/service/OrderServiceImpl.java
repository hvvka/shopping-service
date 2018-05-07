package com.hania.service;

import com.hania.model.Article;
import com.hania.model.Order;
import com.hania.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
@Service("shoppingService")
public class OrderServiceImpl implements OrderService {

    private final AtomicLong counter;

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        counter = new AtomicLong(orderRepository.getOrderIndex());
    }

    @Override
    public Order saveOrder(String client, List<Article> articles) {
        Order order = new Order(counter.incrementAndGet(), client, articles);
        orderRepository.save(order);
        return order;
    }

    @Override
    public boolean deleteOrder(long id) {
        return orderRepository.delete(id);
    }

    @Override
    public Order findOrder(long id) {
        return orderRepository.findOrder(id);
    }

    @Override
    public List<Order> listClientOrders(String client) {
        return orderRepository.findClientOrders(client);
    }

    @Override
    public List<Order> listAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public boolean doesOrderExists(Order order) {
        return false;
    }

    @Override
    public Order updateOrder(long id, List<Article> articles) {
        return orderRepository.updateOrder(id, articles);
    }
}
