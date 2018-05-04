package com.hania.repository;

import com.hania.model.Article;
import com.hania.model.Order;
import com.thoughtworks.xstream.XStream;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
class OrderXStream extends XStream {

    OrderXStream() {
        super();
        this.alias("order", Order.class);
        this.alias("article", Article.class);
    }
}
