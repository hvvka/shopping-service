package com.hania.repository;

import com.hania.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
@Component
public class OrderRepository {

    private List<Order> orders;

    private final OrderDAO orderDAO;

    @Autowired
    public OrderRepository(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
        orders = orderDAO.read();
    }

    public void save(Order order) {
        orders.add(order);
        orderDAO.save(order);
    }

    public boolean delete(long id) {
        orders.removeIf(o -> o.getId() == id);
        return orderDAO.delete(id);
    }

    public Order findOrder(long id) {
        Optional<Order> order = orders.stream()
                .filter(o -> o.getId() == id)
                .findFirst();
        return order.orElse(null);
    }

    public List<Order> findClientOrders(String client) {
        return orders.stream()
                .filter(o -> o.getClient().equals(client))
                .collect(Collectors.toList());
    }

    public List<Order> findAll() {
        return orders;
    }
}
