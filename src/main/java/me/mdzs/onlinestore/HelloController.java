package me.mdzs.onlinestore;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import me.mdzs.onlinestore.utils.DataToolbox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class HelloController {

    public TextField itemNameTextField;
    public TextField itemPriceTextField;
    public TextField itemQuantityTextField;
    public Button addItemButton;
    public Button showItemButton;

    public ScrollPane scrollPane;



    private Stage stage;


    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private void addButtonClicked() {
        try {
            if (Files.notExists(Paths.get("src/main/resources/databases/store.db"))) {
                DataToolbox.initializeDatabase();
            }
            DataToolbox.insertItem(itemNameTextField.getText(), Integer.parseInt(itemPriceTextField.getText()),
                    Integer.parseInt(itemQuantityTextField.getText()));
            itemNameTextField.clear();
            itemPriceTextField.clear();
            itemQuantityTextField.clear();
        } catch (SQLException | IOException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showButtonClicked() throws SQLException {
        Label label = new Label(DataToolbox.readTable());
        scrollPane.setContent(label);
    }

    }
