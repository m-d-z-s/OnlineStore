package me.mdzs.onlinestore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import me.mdzs.onlinestore.utils.DataToolbox;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelloApplication extends Application {
    // 1. есть ли база данных, если нет то вызвать метод initializeDatabase() из DataToolbox
    // 2. добавить в базу данных новый товар с помощью метода insertItem() из DataToolbox
    // 3. вывести в консоль все товары из базы данных с помощью метода readTable() из DataToolbox

    private TextField itemNameField;
    private TextField priceField;
    private TextField quantityField;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Clothing Store App");

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));

        Label itemNameLabel = new Label("Item Name:");
        itemNameField = new TextField();
        placeInAnchorPane(itemNameLabel, 10, 10);
        placeInAnchorPane(itemNameField, 100, 10);

        Label priceLabel = new Label("Price:");
        priceField = new TextField();
        placeInAnchorPane(priceLabel, 10, 40);
        placeInAnchorPane(priceField, 100, 40);

        Label quantityLabel = new Label("Quantity:");
        quantityField = new TextField();
        placeInAnchorPane(quantityLabel, 10, 70);
        placeInAnchorPane(quantityField, 100, 70);

        Button addButton = new Button("Add Item");
        placeInAnchorPane(addButton, 100, 100);

        Button showButton = new Button("Show Items");
        placeInAnchorPane(showButton, 100, 130);

        anchorPane.getChildren().addAll(itemNameLabel, itemNameField, priceLabel, priceField, quantityLabel, quantityField, addButton, showButton);

        addButton.setOnAction(actionEvent -> {
            try {
                if (Files.notExists(Paths.get("src/main/resources/databases/store.db"))) {
                    DataToolbox.initializeDatabase();
                }
                DataToolbox.insertItem(itemNameField.getText(), Integer.parseInt(priceField.getText()), Integer.parseInt(quantityField.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        showButton.setOnAction(actionEvent -> { // вывод в отдельное окно
            try {
                Stage stage = new Stage();
                stage.setTitle("Items");
                AnchorPane anchorPane1 = new AnchorPane();
                anchorPane1.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));
                Label label = new Label(DataToolbox.readTable());
                placeInAnchorPane(label, 10, 10);
                anchorPane1.getChildren().add(label);
                stage.setScene(new Scene(anchorPane1, 300, 300));
                stage.show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        Scene scene = new Scene(anchorPane, 300, 200);
        primaryStage.setTitle("Your Application Title");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void placeInAnchorPane(javafx.scene.Node node, double x, double y) {
        AnchorPane.setLeftAnchor(node, x);
        AnchorPane.setTopAnchor(node, y);
    }


    public static void main(String[] args) {
        launch();
    }
}