package com.example.stock_fifo_lifo.models;

import com.example.stock_fifo_lifo.connecting.Connecting;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Timestamp;

public class Item implements Serializable {
    private String id;
    private String name;
    private String type;
    private String id_type;
    private double unit_price;
    private double quantity;
    private String quantity_temp;
    private Timestamp date;
    private String date_temp;
    private String store;
    private String id_store;

    public Item() {
    }

    public Item(String id, String name, String type, double unit_price, double quantity, Timestamp date, String store) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.unit_price = unit_price;
        this.quantity = quantity;
        this.date = date;
        this.store = store;
    }

    public Item(String id, String name, String type, String id_type, double unit_price, double quantity, String quantity_temp, Timestamp date, String date_temp, String store, String id_store) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.id_type = id_type;
        this.unit_price = unit_price;
        this.quantity = quantity;
        this.quantity_temp = quantity_temp;
        this.date = date;
        this.date_temp = date_temp;
        this.store = store;
        this.id_store = id_store;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getId_store() {
        return id_store;
    }

    public void setId_store(String id_store) {
        this.id_store = id_store;
    }

    public String getQuantity_temp() {
        return quantity_temp;
    }

    public void setQuantity_temp(String quantity_temp) {
        this.quantity_temp = quantity_temp;
    }

    public String getDate_temp() {
        return date_temp;
    }

    public void setDate_temp(String date_temp) {
        this.date_temp = date_temp;
    }

    public Boolean itemExist(Connection connection, String id_item) throws Exception{
        boolean exist = false;
        id_item = id_item+"%";
        Boolean close_connection = false;
        if(connection == null) {
            connection = Connecting.getConnection("postgres");
        }
        try{
            String query = "select * from item WHERE id like ? ";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id_item);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                exist = true;
            }
        }catch (Exception e) {
            throw e;
        }
        return exist;
    }
}
