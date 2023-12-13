package me.mdzs.onlinestore.utils;

import me.mdzs.onlinestore.domain.Item;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {
    String url;


    public DataProcessor(@NotNull String path) {
        this.url = "jdbc:sqlite:" + path;
    }

    public void createTable(@NotNull String tableName, @NotNull String[] columns) throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        StringBuilder queryBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");
        for (int i = 0; i < columns.length; i++) {
            queryBuilder.append(columns[i]);
            if (i != columns.length - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(")");
        String query = queryBuilder.toString();
        try (PreparedStatement createTableStatement = connection.prepareStatement(query)) {
            createTableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertItem(@NotNull String tableName, @NotNull Item item) throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        String query = "INSERT INTO " + tableName + " (name, price, quantity) VALUES ('" + item.getItemName() +
                "', " + item.getPrice() + ", "+ item.getQuantity() + ")";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> readAll(@NotNull String tableName) throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        String query = "select * from " + tableName;
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int price = result.getInt("price");
                int quantity = result.getInt("quantity");
                items.add(new Item(id, name, price, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }
}
