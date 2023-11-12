package com.example.stock_fifo_lifo.models;

import java.io.Serializable;

public class Stock implements Serializable {
    private String item;
    private String id_item;
    private double initial_quantity;
    private double rest_quantity;
    private double average_unit_price;
    private String unit;
    private String store;
    private String id_store;
    private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Stock() {
    }

    public Stock(String item, String id_item, double initial_quantity, double rest_quantity, double average_unit_price, String unit, String store, String id_store) {
        this.item = item;
        this.id_item = id_item;
        this.initial_quantity = initial_quantity;
        this.rest_quantity = rest_quantity;
        this.average_unit_price = average_unit_price;
        this.unit = unit;
        this.store = store;
        this.id_store = id_store;
    }

    public Stock(double total, double initial_quantity, double average_unit_price){
        this.total = total;
        this.initial_quantity = initial_quantity;
        this.average_unit_price = average_unit_price;
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

    public double getInitial_quantity() {
        return initial_quantity;
    }

    public void setInitial_quantity(double initial_quantity) {
        this.initial_quantity = initial_quantity;
    }

    public double getRest_quantity() {
        return rest_quantity;
    }

    public void setRest_quantity(double rest_quantity) {
        this.rest_quantity = rest_quantity;
    }

    public double getAverage_unit_price() {
        return average_unit_price;
    }

    public void setAverage_unit_price(double average_unit_price) {
        this.average_unit_price = average_unit_price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
}
