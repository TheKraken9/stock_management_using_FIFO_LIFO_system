package com.example.stock_fifo_lifo.models;

public class Store {
    private String id;
    private String name;
    private String id_region;

    public Store() {
    }

    public Store(String id, String name, String id_region) {
        this.id = id;
        this.name = name;
        this.id_region = id_region;
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

    public String getId_region() {
        return id_region;
    }

    public void setId_region(String id_region) {
        this.id_region = id_region;
    }
}
