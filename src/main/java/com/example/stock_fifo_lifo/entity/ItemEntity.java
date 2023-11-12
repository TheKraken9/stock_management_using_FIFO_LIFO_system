package com.example.stock_fifo_lifo.entity;

import com.example.stock_fifo_lifo.connecting.Connecting;
import com.example.stock_fifo_lifo.models.Item;

import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;

public class ItemEntity {
    public ArrayList<Item> getItems(Connection connection) throws Exception {
        boolean close_connection = false;
        if(connection == null){
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        ArrayList<Item> items = new ArrayList<Item>();
        try{
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("select * from item");
            while (resultSet.next()){
                Item item = new Item();
                item.setId(resultSet.getString("id"));
                item.setName(resultSet.getString("name"));
                item.setId_type(resultSet.getString("id_type"));
                items.add(item);
            }
        }catch (Exception e){
            throw e;
        }
        if(close_connection){
            connection.close();
        }
        return items;
    }

    public ArrayList<Item> getOverItem(Connection connection) throws Exception {
        boolean close_connection = false;
        if(connection == null){
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        ArrayList<Item> items = new ArrayList<Item>();
        try{
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("select * from item");
            while (resultSet.next()){
                Item item = new Item();
                String id = resultSet.getString("id").substring(0,2);
                String name = resultSet.getString("name").split(" ")[0];
                item.setId(id);
                item.setName(name);
                items.add(item);
            }
            for (int i = 0; i < items.size(); i++) {
                for (int j = i+1; j < items.size(); j++) {
                    if(items.get(i).getId().equals(items.get(j).getId())){
                        items.remove(j);
                        j--;
                    }
                }
            }
        }catch (Exception e){
            throw e;
        }
        if(close_connection){
            connection.close();
        }
        return items;
    }

    public boolean isStockEnough(Connection connection, String id_item, String id_store, double quantity) throws Exception{
        boolean enough = false;
        double sum_stock = 0;
        boolean close_connection = false;
        if(connection == null){
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        try{
            String query = "select sum(rest) from entry where id_item = ? and id_store = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id_item);
            preparedStatement.setString(2, id_store);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                sum_stock = resultSet.getDouble("sum");
            }
            if(sum_stock >= quantity){
                enough = true;
            }
        }catch (Exception e){
            throw e;
        }
        if(close_connection){
            connection.close();
        }
        return enough;
    }

    public boolean isStoreHasItem(Connection connection, String id_item, String id_store) throws Exception{
        boolean has = false;
        boolean close_connection = false;
        if(connection == null){
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        try{
            String query = "select * from entry where id_item = ? and id_store = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id_item);
            preparedStatement.setString(2, id_store);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                has = true;
            }
        }catch (Exception e){
            throw e;
        }
        if(close_connection){
            connection.close();
        }
        return has;
    }

    public static void main(String[] args) throws Exception {
        ItemEntity itemEntity = new ItemEntity();
        ArrayList<Item> items = itemEntity.getOverItem(null);
        System.out.println(items.size());
        System.out.println(itemEntity.isStockEnough(null, "IR00001", "ST00001", 100));
    }
}
