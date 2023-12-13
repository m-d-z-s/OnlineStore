package me.mdzs.onlinestore.utils;

import me.mdzs.onlinestore.domain.Item;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

class DataProcessorTest {

    @Test
    void tableCreation() throws SQLException {
        DataProcessor dataProcessor = new DataProcessor("src/test/resources/databases/test.db");
        dataProcessor.createTable("test_table", new String[]{"row INT primary key", "col INT", "value DOUBLE"});
    }

    @Test
    void itemInsertion() throws SQLException {
        DataProcessor dataProcessor = new DataProcessor("src/test/resources/databases/test.db");
        dataProcessor.createTable("items", new String[]{"id integer primary key autoincrement", "name text",
                "price integer", "quantity integer"});
        Item item1 = new Item(1, "test_item", 100, 1);
        Item item2 = new Item(2, "test_item2", 999, 9);

        dataProcessor.insertItem("items", item1);
        dataProcessor.insertItem("items", item2);
    }

    @Test
    void readDatabase() throws SQLException {
        DataProcessor dataProcessor = new DataProcessor("src/test/resources/databases/test.db");
        List<Item> items = dataProcessor.readAll("items");

        for (Item item : items) {
            System.out.print(item.getItemName() + " " + item.getPrice() + " " + item.getQuantity() + "\n");
        }

    }


}