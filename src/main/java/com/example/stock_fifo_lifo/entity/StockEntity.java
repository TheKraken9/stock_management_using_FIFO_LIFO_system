package com.example.stock_fifo_lifo.entity;

import com.example.stock_fifo_lifo.connecting.Connecting;
import com.example.stock_fifo_lifo.models.Flow;
import com.example.stock_fifo_lifo.models.Stock;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class StockEntity {

    public Stock stockAvailable(Connection connection, Timestamp initialDate, Timestamp finalDate, String id_item, String id_store) throws Exception {
        String item = id_item+"%";
        Stock stock = new Stock();
        Stock stock_info = new Stock();
        stock_info = new StockEntity().getInfo(connection, id_item, id_store);
        boolean close_connection = false;
        if(connection == null) {
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        try{
            Stock stock_initial = stockAvailableOnDate(connection, initialDate, item, id_store);
            Stock stock_final = stockAvailableOnDate(connection, finalDate, item, id_store);
            double average = (stock_final.getTotal() - stock_initial.getTotal()) / (stock_final.getInitial_quantity() - stock_initial.getInitial_quantity());
            //average = Math.round(average * 100.0) / 100.0;
            double total = stock_initial.getTotal() - stock_final.getTotal();

            stock.setInitial_quantity(stock_initial.getInitial_quantity());
            stock.setRest_quantity(stock_final.getInitial_quantity());
            stock.setAverage_unit_price(average);
            stock.setTotal(total);
            stock.setItem(stock_info.getItem());
            stock.setStore(stock_info.getStore());
            stock.setUnit(stock_info.getUnit());
        }catch (Exception e) {
            throw e;
        }
        return stock;
    }

    public Stock getInfo(Connection connection, String id_item, String id_store) throws Exception {
        Stock stock = new Stock();
        Boolean close_connection = false;
        if(connection == null) {
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        try{
            stock.setItem(getNameItem(connection, id_item));
            stock.setId_item(id_item);
            stock.setUnit(getUnit(connection, id_item));
            stock.setStore(getNameStore(connection, id_store));
            stock.setId_store(id_store);
        }catch (Exception e) {
            throw e;
        }
        return stock;
    }

    private String getNameItem(Connection connection, String id_item) throws Exception {
        String name = null;
        String item = id_item+"%";
        Boolean close_connection = false;
        if(connection == null) {
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        try{
            String sql = "SELECT name FROM item WHERE id like ? limit 1";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, item);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                name = resultSet.getString("name").split(" ")[0];
            }
        }catch (Exception e) {
            throw e;
        }
        if(close_connection){
            connection.close();
        }
        return name;
    }

    private String getNameStore(Connection connection, String id_store) throws Exception {
        String name = null;
        Boolean close_connection = false;
        if(connection == null) {
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        try{
            String sql = "SELECT name FROM store WHERE id = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id_store);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                name = resultSet.getString("name");
            }
        }catch (Exception e) {
            throw e;
        }
        if(close_connection){
            connection.close();
        }
        return name;
    }

    private String getUnit(Connection connection, String id_item) throws Exception {
        id_item = id_item+"%";
        String unit = null;
        Boolean close_connection = false;
        if(connection == null) {
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        try{
            String sql = "SELECT unit FROM item WHERE id like ? limit 1";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id_item);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                unit = resultSet.getString("unit");
            }
        }catch (Exception e) {
            throw e;
        }
        if(close_connection){
            connection.close();
        }
        return unit;
    }

    public Stock stockAvailableOnDate(Connection connection, Timestamp date, String id_item, String id_store) throws Exception {
        Boolean close_connection = false;
        double total = 0.0;
        double quantity = 0.0;
        double average = 0.0;
        if(connection == null) {
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        try{
            StockEntity stockEntity = new StockEntity();
            Stock stock = stockEntity.totalEntry(null, id_item, id_store, date);
            Stock stock1 = stockEntity.totalOut(null, id_item, id_store, date);
            total = stock.getTotal() - stock1.getTotal();
            quantity = stock.getInitial_quantity() - stock1.getInitial_quantity();
            average = total / quantity;
        }catch (Exception e) {
            throw e;
        }
        if(close_connection){
            connection.close();
        }
        return new Stock(total, quantity, average);
    }

    public ArrayList<Flow> stockEntry(Connection connection, String id_item, String id_store, Timestamp date) throws Exception {
        Boolean close_connection = false;
        ArrayList<Flow> flows = new ArrayList<>();
        if(connection == null) {
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        try{
            String sql = "SELECT entry.id as id, entered, item.name as name_item, id_item, quantity, unit_price, id_store, store.name as name_store FROM entry join item on item.id = entry.id_item join store on store.id=entry.id_store WHERE id_item like ? AND id_store = ? AND entered <= ? ORDER BY entered";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id_item);
            preparedStatement.setString(2, id_store);
            preparedStatement.setTimestamp(3, date);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Flow flow = new Flow(resultSet.getString(1), null, resultSet.getTimestamp(2), null, resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getDouble(6), resultSet.getString(8), resultSet.getString(7));
                flows.add(flow);
            }
        }catch (Exception e) {
            throw e;
        }
        if(close_connection){
            connection.close();
        }
        return flows;
    }

    public ArrayList<Flow> stockOut(Connection connection, String id_item, String id_store, Timestamp date) throws Exception {
        Boolean close_connection = false;
        ArrayList<Flow> flows = new ArrayList<>();
        if(connection == null) {
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        try{
            String sql = "select out.id as id, outed, id_item, quantity, unit_price, id_store, item.name as name_item, id_type, type.name as name_type, store.name as name_store, id_region, region.name as name_region from out join item on item.id = out.id_item join store on store.id=out.id_store join region on region.id=store.id_region join type on type.id=item.id_type WHERE id_item like ? AND id_store = ? AND outed <= ? ORDER BY outed";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id_item);
            preparedStatement.setString(2, id_store);
            preparedStatement.setTimestamp(3, date);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Flow flow = new Flow(resultSet.getString(1), null, resultSet.getTimestamp(2), null, resultSet.getString(7), resultSet.getString(3), resultSet.getDouble(4),null, resultSet.getDouble(5), resultSet.getString(10), resultSet.getString(6), resultSet.getString(11), resultSet.getString(12), resultSet.getString(8), resultSet.getString(9));
                flows.add(flow);
            }
        }catch (Exception e) {
            throw e;
        }
        if(close_connection){
            connection.close();
        }
        return flows;
    }

    public Stock totalEntry(Connection connection, String id_item, String id_store, Timestamp date) throws Exception {
        Boolean close_connection = false;
        if(connection == null) {
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        ArrayList<Flow> flows = stockEntry(connection, id_item, id_store, date);
        double total = 0.0;
        double quantity = 0.0;
        double average = 0.0;
        for (Flow flow : flows) {
            total += flow.getQuantity() * flow.getUnit_price();
            quantity += flow.getQuantity();
        }
        average = total / quantity;

        if(close_connection){
            connection.close();
        }
        return new Stock(total, quantity, average);
    }

    public Stock totalOut(Connection connection, String id_item, String id_store, Timestamp date) throws Exception {
        Boolean close_connection = false;
        if(connection == null) {
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        ArrayList<Flow> flows = stockOut(connection, id_item, id_store, date);
        double total = 0.0;
        double quantity = 0.0;
        double average = 0.0;
        for (Flow flow : flows) {
            total += flow.getQuantity() * flow.getUnit_price();
            quantity += flow.getQuantity();
        }
        average = total / quantity;

        if(close_connection){
            connection.close();
        }
        return new Stock(total, quantity, average);
    }

    public static void main(String[] args) throws Exception{
        StockEntity stockEntity = new StockEntity();
        /*Stock stock = stockEntity.totalEntry(null, "IR%", "ST00001", Timestamp.valueOf("2024-01-01 00:00:00"));
        System.out.println(stock.getTotal());
        System.out.println(stock.getInitial_quantity());
        System.out.println(stock.getAverage_unit_price());
        Stock stock1 = stockEntity.totalOut(null, "IR%", "ST00001", Timestamp.valueOf("2024-01-01 00:00:00"));
        System.out.println(stock1.getTotal());
        System.out.println(stock1.getInitial_quantity());
        System.out.println(stock1.getAverage_unit_price());*/
        Stock stock = stockEntity.stockAvailable(null, Timestamp.valueOf("2022-01-01 00:00:00"), Timestamp.valueOf("2024-01-01 00:00:00"), "IR", "ST00001");
        System.out.println("--------------------------------------------------");
        System.out.println("Initial quantity: " + stock.getInitial_quantity() + " , Rest quantity: " + stock.getRest_quantity() + " , Average unit price: " + stock.getAverage_unit_price() + " , Total: " + stock.getTotal());
        /*ArrayList<Flow> flow = stockEntity.stockEntry(null, "IR00001", "ST00001", Timestamp.valueOf("2024-01-01 00:00:00"));
        System.out.println(flow.size());
        System.out.println(flow.get(0).getQuantity());*/
        //stockEntity.stockAvailable(null, null, null, null, null);
        stockEntity.getInfo(null, "IR", "ST00001");
    }
}
