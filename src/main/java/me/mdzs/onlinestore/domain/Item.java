package me.mdzs.onlinestore.domain;

import javafx.scene.control.TextField;

import java.util.Random;

public class Item {
    private int id;
    private String itemName;
    private int price;
    private int quantity;


    public Item(String itemName, int price, int quantity){
        this.id = new Random().nextInt(1000);
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
    public Item(int id, String itemName, int price, int quantity){
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName(){
        return itemName;
    }

    public int getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getId() {
        return id;
    }
}
