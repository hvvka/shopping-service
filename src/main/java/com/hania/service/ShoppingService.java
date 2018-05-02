package com.hania.service;

import com.hania.model.Article;
import com.hania.model.Order;

import java.util.List;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public interface ShoppingService {

    void saveOrder(String client, List<Article> articles);

    void deleteOrder(long id);

    List<Order> listOrders(String client);

    List<Order> listAllOrders();
}
