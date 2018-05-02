package com.hania.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
@Component
public class OrderRepository {

    private List<Order> orders;

    public OrderRepository() {
        orders = new ArrayList<>();
        orders.add(new Order(0, "Client",
                Arrays.asList(new Article(0, "lampa", 10),
                        new Article(1, "marchewka", 5))));
    }

    public void save(Order order) {
        orders.add(order);
    }

    public void delete(long id) {
        orders.removeIf(o -> o.getId() == id);
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
