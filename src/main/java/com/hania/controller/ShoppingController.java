package com.hania.controller;

import com.hania.model.Article;
import com.hania.model.Order;
import com.hania.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
@RestController
public class ShoppingController {

    private final ShoppingService shoppingService;

    @Autowired
    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping("/shopping")
    @ResponseBody
    public void saveOrder(@RequestParam(name = "client") String client,
                          @RequestParam(name = "articles", required = false, defaultValue = "") List<Article> articles) {
        shoppingService.saveOrder(client, articles);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable("id") long id) {
        shoppingService.deleteOrder(id);
    } //delete mapping - przekazywanie zmiennej w ścieżce - path variable; nie przysyłam JSONa, tylko samą ścieżkę

    @GetMapping("/shopping/{client}/orders")
    public List<Order> listOrders(@PathVariable String client) {
        return shoppingService.listOrders(client);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> listAllOrders() {
        return shoppingService.listAllOrders();
    }
}
