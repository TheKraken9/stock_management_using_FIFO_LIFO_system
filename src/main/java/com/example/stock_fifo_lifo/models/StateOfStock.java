package com.example.stock_fifo_lifo.models;

import com.example.stock_fifo_lifo.connecting.Connecting;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StateOfStock implements Serializable {
    private Timestamp date1;
    private Timestamp date2;
    private String id_item;
    private String item;
    private String store;
    private String unit;
    private String id_Store;

    public StateOfStock() {
    }

    public StateOfStock(Timestamp date1, Timestamp date2, String id_item, String id_Store) {
        this.date1 = date1;
        this.date2 = date2;
        this.id_item = id_item;
        this.id_Store = id_Store;
    }

    public Timestamp getDate1() {
        return date1;
    }

    public void setDate1(Timestamp date1) {
        this.date1 = date1;
    }

    public void setDate1(String date1) throws Exception{
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(date1);
            this.date1 = Timestamp.valueOf(date1);
            if(parsedDate != null)
                this.setDate1(new Timestamp(parsedDate.getTime()));
            else
                this.setDate1(new Timestamp(System.currentTimeMillis()));
        }catch (Exception e) {
            System.out.println("Invalid date format");
        }

    }

    public Timestamp getDate2() {
        return date2;
    }

    public void setDate2(Timestamp date2) {
        this.date2 = date2;
    }

    public void setDate2(String date2) throws Exception{
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(date2);
            this.date2 = Timestamp.valueOf(date2);
            if(parsedDate != null)
                this.setDate2(new Timestamp(parsedDate.getTime()));
            else
                this.setDate2(new Timestamp(System.currentTimeMillis()));
        }catch (Exception e) {
            System.out.println("Invalid date format");
        }

    }

    public String getId_item() {
        return id_item;
    }

    public void setId_item(String id_item) throws Exception{
        this.verifyIfItemExist(id_item);
        this.id_item = id_item;
    }

    public String getId_Store() {
        return id_Store;
    }

    public void setId_Store(String id_Store) {
        this.id_Store = id_Store;
    }

    public void verifyIfItemExist(String id_item) throws Exception{
        Item item = new Item();
        Connection connection = Connecting.getConnection("postgres");
        Boolean exist = false;
        try{
            exist = item.itemExist(connection, id_item);
            if (!exist){
                throw new Exception("Item does not exist");
            }
        }catch (Exception e){
            throw e;
        }finally {
            connection.close();
        }
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public static void main(String[] args) throws Exception{
        StateOfStock stateOfStock = new StateOfStock();
        stateOfStock.setDate1("2021-01-01 00:00:00");
        stateOfStock.setDate2("2021-01-01 00:00:00");
        stateOfStock.setId_Store("ST00001");
        stateOfStock.setId_item("IR00001");
        System.out.println(stateOfStock.getDate1());
        System.out.println(stateOfStock.getDate2());
        System.out.println(stateOfStock.getId_item());
        System.out.println(stateOfStock.getId_Store());
    }
}
