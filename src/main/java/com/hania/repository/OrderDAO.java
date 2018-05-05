package com.hania.repository;

import com.hania.model.Order;

import java.util.List;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public interface OrderDAO {

    List<Order> read();

    void save(Order order);

    boolean delete(long id);

    void update(Order updatedOrder);
}
