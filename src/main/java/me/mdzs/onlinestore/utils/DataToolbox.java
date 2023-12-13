package me.mdzs.onlinestore.utils;

import me.mdzs.onlinestore.domain.Item;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;

public class DataToolbox {

    private static final String DATABASE_PATH = "src/main/resources/databases/store.db";
    private static final String TABLE_NAME = "items";

    public static void initializeDatabase() throws IOException {
        DataProcessor dataProcessor = new DataProcessor("src/main/resources/databases/store.db");
        try {
            dataProcessor.createTable(TABLE_NAME, new String[]{"id integer primary key autoincrement", "name text",
                    "price integer", "quantity integer"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertItem(String name, int price, int quantity) throws SQLException {
        Item item = new Item(name, price, quantity);
        DataProcessor dataProcessor = new DataProcessor(DATABASE_PATH);
        dataProcessor.insertItem(TABLE_NAME, item);
    }

    @NotNull
    public static String readTable() throws SQLException {
        DataProcessor dataProcessor = new DataProcessor(DATABASE_PATH);
        StringBuilder result = new StringBuilder();
        dataProcessor.readAll(TABLE_NAME).forEach(item -> {
            result.append(item.getItemName()).append(" ").append(item.getPrice()).append(" ").append(item.getQuantity()).append("\n");
        });
        return result.toString();
    }
}
