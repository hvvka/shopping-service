package com.hania.service;

import com.hania.model.Article;
import com.hania.model.Order;

import java.util.List;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public interface OrderService {

    Order saveOrder(String client, List<Article> articles);

    boolean deleteOrder(long id);

    Order findOrder(long id);

    List<Order> listClientOrders(String client);

    List<Order> listAllOrders();

    boolean doesOrderExists(Order order);

    Order updateOrder(long id, List<Article> articles);
}
