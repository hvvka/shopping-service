package com.hania.repository;

import com.hania.model.Order;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
@Component
class OrderDAOImpl implements OrderDAO {

    private static final Logger LOG = LoggerFactory.getLogger(OrderDAOImpl.class);

    private static final String DB_PATH = "src/main/resources/db/";

    private static final String FILE_EXTENSION = ".xml";

    private XStream xStream;

    OrderDAOImpl() {
        xStream = new OrderXStream();
    }

    @Override
    public List<Order> read() {
        List<Order> orders = new ArrayList<>();
        Arrays.stream(readAllFiles())
                .map(this::readFile)
                .forEach(orders::add);
        return orders;
    }

    @Override
    public void save(Order order) {
        try {
            xStream.toXML(order, new FileOutputStream(new File(DB_PATH +
                    order.getId() + "_" + order.getClient() + FILE_EXTENSION)));
        } catch (FileNotFoundException e) {
            LOG.error("", e);
        }
    }

    @Override
    public boolean delete(long id) {
        Optional<File> file = Arrays.stream(readAllFiles())
                .filter(f -> f.getName().startsWith(id + "_"))
                .findFirst();
        return file.map(File::delete).orElse(false);
    }

    @Override
    public void update(Order updatedOrder) {
        long id = updatedOrder.getId();
        boolean isDeleted = delete(id);
        if (isDeleted) save(updatedOrder);
    }

    private File[] readAllFiles() {
        File directory = new File(DB_PATH);
        File[] files = new File[0];
        if (directory.exists() && directory.isDirectory()) {
            files = directory.listFiles((d, name) -> name.endsWith(FILE_EXTENSION));
        }
        return files;
    }

    private Order readFile(File file) {
        try {
            return (Order) xStream.fromXML(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            LOG.error("", e);
        }
        return new Order();
    }
}
