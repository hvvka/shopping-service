package com.hania.model;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
@Component
public class OrderRepository {

    public void save(Order order) {

    }

    public void delete(long id) {

    }

    public List<Order> findClientOrders(String client) {
        return Collections.emptyList();
    }

    public List<Order> findAll() {
        return Collections.emptyList();
    }
}
