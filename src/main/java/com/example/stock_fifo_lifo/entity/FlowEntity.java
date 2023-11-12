package com.example.stock_fifo_lifo.entity;

import com.example.stock_fifo_lifo.connecting.Connecting;
import com.example.stock_fifo_lifo.models.Flow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import static com.example.stock_fifo_lifo.connecting.Connecting.generateSequence;

public class FlowEntity {

    public void newOut(Connection connection, Timestamp outed, String id_item, double quantity, double unit_price, String id_store) throws Exception {
        String sql = null;
        Boolean close_connection = false;
        if(connection == null){
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        String sequence = "SELECT nextval('seq_out')";
        sql = (outed == null) ?
                "INSERT INTO out(id, outed, id_item, quantity,unit_price, id_store) VALUES (?, now(), ?, ?, ?, ?)" :
                "INSERT INTO out(id, outed, id_item, quantity,unit_price, id_store) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sequence);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String id = generateSequence("OUT", Integer.valueOf(resultSet.getString(1)),5);
            System.out.println(id);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            if(outed != null){
                preparedStatement.setTimestamp(2, outed);
                preparedStatement.setString(3, id_item);
                preparedStatement.setDouble(4, quantity);
                preparedStatement.setDouble(5, unit_price);
                preparedStatement.setString(6, id_store);
            }else{
                preparedStatement.setString(2, id_item);
                preparedStatement.setDouble(3, quantity);
                preparedStatement.setDouble(4, unit_price);
                preparedStatement.setString(5, id_store);
            }
            preparedStatement.executeUpdate();
            if(close_connection) {
                preparedStatement.close();
                connection.close();
            }
        }catch (Exception e){
            throw e;
        }
    }

    public Flow getPrice(Connection connection, String id_item, String id_store, Timestamp date, String type_name) throws Exception {
        String sql = null;
        Boolean close_connection = false;
        if(connection == null) {
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        if(type_name.equalsIgnoreCase("FIFO"))
            sql = "SELECT id, rest, unit_price FROM entry WHERE id_item = ? AND id_store = ? AND entered <= ? AND STATE = 0 ORDER BY entered LIMIT 1";
        else
            sql = "SELECT id, rest, unit_price FROM entry WHERE id_item = ? AND id_store = ? AND entered <= ? AND STATE = 0 ORDER BY entered DESC LIMIT 1";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id_item);
            preparedStatement.setString(2, id_store);
            preparedStatement.setTimestamp(3, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String id = resultSet.getString(1);
            double quantity = resultSet.getDouble(2);
            double unit_price = resultSet.getDouble(3);

            if(close_connection) {
                preparedStatement.close();
                connection.close();
            }
            return new Flow(id, null, null, null, null, null, quantity, unit_price, null, null);
        }catch (Exception e){
            throw e;
        }
    }

    public void make_out_of_stock(Connection connection, String id_item, String id_store, String id) throws Exception {
        String sql = null;
        Boolean close_connection = false;
        if(connection == null) {
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        sql = "UPDATE entry SET state = 1, rest = 0 WHERE id_item = ? AND id_store = ? AND state = 0 AND id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id_item);
            preparedStatement.setString(2, id_store);
            preparedStatement.setString(3, id);
            preparedStatement.executeUpdate();
            if(close_connection) {
                preparedStatement.close();
                connection.close();
            }
        }catch (Exception e){
            throw e;
        }
    }

    public void reduce_out(Connection connection, String id_item, String id_store, String id, double quantity) throws Exception {
        String sql = null;
        Boolean close_connection = false;
        if(connection == null) {
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        sql = "UPDATE entry SET rest = rest - ? WHERE id_item = ? AND id_store = ? AND state = 0 AND id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, quantity);
            preparedStatement.setString(2, id_item);
            preparedStatement.setString(3, id_store);
            preparedStatement.setString(4, id);
            preparedStatement.executeUpdate();
            if(close_connection) {
                preparedStatement.close();
                connection.close();
            }
        }catch (Exception e){
            throw e;
        }
    }

    public String is_fifo_or_lifo(Connection connection, String id_item, String id_store, Timestamp date) throws Exception {
        String sql = null;
        String type_name = null;
        Boolean close_connection = false;
        if(connection == null) {
            close_connection = true;
            connection = Connecting.getConnection("postgres");
        }
        sql = "SELECT item.id as id, item.name as name, id_type, type.name as name_type from item join type on item.id_type=type.id WHERE item.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id_item);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String name = resultSet.getString("name_type");
            type_name = name;
            if(close_connection) {
                preparedStatement.close();
                connection.close();
            }
        }catch (Exception e){
            throw e;
        }
        return type_name;
    }

    private void test_param(Connection connection, String id_item, String id_store, double quantity) throws Exception {
        ItemEntity itemEntity = new ItemEntity();
        boolean isStoreHasItem = itemEntity.isStoreHasItem(connection, id_item, id_store);
        boolean isStockEnough = itemEntity.isStockEnough(connection, id_item, id_store, quantity);
        if(!isStoreHasItem)
            throw new Exception("This store does not have this item");
        if(!isStockEnough)
            throw new Exception("Stock is not enough");
    }

    public boolean out(Connection connection, String id_item, String id_store, Timestamp date, double quantity) throws Exception {
        FlowEntity flowEntity = new FlowEntity();
        this.test_param(connection, id_item, id_store, quantity);
        Boolean close_connection = false;
        boolean result_state = false;
        if(connection == null) {
            connection = Connecting.getConnection("postgres");
        }
        try{
            connection.setAutoCommit(false);
            Boolean break_loop = true;
            Flow result = new Flow();
            double quant = 0.0;
            double quantity_wanted = quantity;
            quant = quantity_wanted;
            String type_name = flowEntity.is_fifo_or_lifo(connection, id_item, id_store, date);
            System.out.println(type_name);
            while(break_loop) {
                result = flowEntity.getPrice(connection, id_item, id_store, date, type_name);
                if (result != null) {
                    double quantity_temp = result.getQuantity();
                    System.out.println(quantity_temp + " " + quant);
                    if (quantity_temp <= quant) {
                        flowEntity.make_out_of_stock(connection, id_item, id_store, result.getId());
                        quant = quantity_wanted - quantity_temp;
                        flowEntity.newOut(connection, null, id_item, quantity_temp, result.getUnit_price(), id_store);
                    } else {
                        flowEntity.reduce_out(connection, id_item, id_store, result.getId(), quant);
                        flowEntity.newOut(connection, null, id_item, quant, result.getUnit_price(), id_store);
                        break_loop = false;
                    }
                    System.out.println(result.getId() + " " + result.getUnit_price() + " " + result.getQuantity());
                } else {
                    break_loop = false;
                }
            }
            connection.commit();
            result_state = true;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return result_state;
    }

    public static void main(String[] args) throws Exception{
        FlowEntity flowEntity = new FlowEntity();
        boolean result = false;
        result = flowEntity.out(null, "IR00001", "ST00001", null, 100);
    }
}
