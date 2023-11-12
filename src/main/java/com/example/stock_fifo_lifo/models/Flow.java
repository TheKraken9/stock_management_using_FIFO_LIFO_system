package com.example.stock_fifo_lifo.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class Flow implements Serializable {
    private String id;
    private String date_temp;
    private Timestamp date;
    private Item item_object;
    private String item;
    private String id_item;
    private double quantity;
    private String quantity_temp;
    private double unit_price;
    private String store;
    private String id_store;
    private String id_region;
    private String region;
    private String id_type;
    private String type;

    public Flow() {
    }

    public Flow(String id, String date_temp, Timestamp date, Item item_object, String item, String id_item, double quantity, double unit_price, String store, String id_store) {
        this.id = id;
        this.date_temp = date_temp;
        this.date = date;
        this.item_object = item_object;
        this.item = item;
        this.id_item = id_item;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.store = store;
        this.id_store = id_store;
    }

    public Flow(String id, String date_temp, Timestamp date, Item item_object, String item, String id_item, double quantity, String quantity_temp, double unit_price, String store, String id_store, String id_region, String region, String id_type, String type) {
        this.id = id;
        this.date_temp = date_temp;
        this.date = date;
        this.item_object = item_object;
        this.item = item;
        this.id_item = id_item;
        this.quantity = quantity;
        this.quantity_temp = quantity_temp;
        this.unit_price = unit_price;
        this.store = store;
        this.id_store = id_store;
        this.id_region = id_region;
        this.region = region;
        this.id_type = id_type;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate_temp() {
        return date_temp;
    }

    public void setDate_temp(String date_temp) {
        this.date_temp = date_temp;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Item getItem_object() {
        return item_object;
    }

    public void setItem_object(Item item_object) {
        this.item_object = item_object;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getId_item() {
        return id_item;
    }

    public void setId_item(String id_item) {
        this.id_item = id_item;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
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

    public String getId_region() {
        return id_region;
    }

    public void setId_region(String id_region) {
        this.id_region = id_region;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
