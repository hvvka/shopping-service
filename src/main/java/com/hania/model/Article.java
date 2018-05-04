package com.hania.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class Article implements Serializable {

    private long id;

    private String item;

    private int amount;

    public Article() {
    }

    public Article(long id, String item, int amount) {
        this.id = id;
        this.item = item;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (id != article.id) return false;
        if (amount != article.amount) return false;
        return item != null ? item.equals(article.item) : article.item == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + amount;
        return result;
    }
}
