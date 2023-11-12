package com.example.stock_fifo_lifo.entity;

import com.example.stock_fifo_lifo.connecting.Connecting;
import com.example.stock_fifo_lifo.models.Store;

import java.sql.Connection;
import java.util.ArrayList;

public class StoreEntity {
    public ArrayList<Store> getStores(Connection connection) throws Exception {
        boolean close_connection = false;
        if(connection == null){
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        ArrayList<Store> stores = new ArrayList<Store>();
        try{
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM store");
            while (resultSet.next()){
                Store store = new Store();
                store.setId(resultSet.getString("id"));
                store.setName(resultSet.getString("name"));
                store.setId_region(resultSet.getString("id_region"));
                stores.add(store);
            }
        }catch (Exception e){
            throw e;
        }
        if(close_connection){
            connection.close();
        }
        return stores;
    }
}
