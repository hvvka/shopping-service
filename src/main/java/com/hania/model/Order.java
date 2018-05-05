package com.hania.model;

import java.util.List;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class Order {

    private long id;

    private String client;

    private List<Article> articles;

    public Order() {
    }

    public Order(long id, String client, List<Article> articles) {
        this.id = id;
        this.client = client;
        this.articles = articles;
    }

    public long getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (client != null ? !client.equals(order.client) : order.client != null) return false;
        return articles != null ? articles.equals(order.articles) : order.articles == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (articles != null ? articles.hashCode() : 0);
        return result;
    }
}
