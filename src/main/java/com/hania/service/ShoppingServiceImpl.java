package com.hania.service;

import com.hania.model.Article;
import com.hania.model.Order;
import com.hania.model.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
@Service("shoppingService")
public class ShoppingServiceImpl implements ShoppingService {

    private final AtomicLong counter = new AtomicLong();

    private final OrderRepository orderRepository;

    @Autowired
    public ShoppingServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(String client, List<Article> articles) {
        Order order = new Order(counter.incrementAndGet(), client, articles);
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(long id) {
        orderRepository.delete(id);
    }

    @Override
    public List<Order> listOrders(String client) {
        return orderRepository.findClientOrders(client);
    }

    @Override
    public List<Order> listAllOrders() {
        return orderRepository.findAll();
    }
}
