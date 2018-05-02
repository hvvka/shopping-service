package com.hania.model;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class Article {

    private final long id;

    private final String item;

    private final int amount;

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
}
