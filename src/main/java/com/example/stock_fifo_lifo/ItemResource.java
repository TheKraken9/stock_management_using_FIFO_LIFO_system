package com.example.stock_fifo_lifo;

import com.example.stock_fifo_lifo.entity.ItemEntity;
import com.example.stock_fifo_lifo.reponseObject.details.ErrorReponse;
import com.example.stock_fifo_lifo.reponseObject.details.InfoReponse;
import com.example.stock_fifo_lifo.models.Item;
import com.example.stock_fifo_lifo.reponseObject.details.MessageResponse;
import com.example.stock_fifo_lifo.reponseObject.ItemResponse;
import jakarta.ws.rs.*;

import java.util.ArrayList;

@Path("/items")
public class ItemResource {

    @GET
    @Path("/std")
    @Produces("application/json")
    public ItemResponse getItems() throws Exception {
        ArrayList<Item> items = new ArrayList<Item>();
        ItemEntity itemEntity = new ItemEntity();
        ItemResponse itemResponse = new ItemResponse();
        try{
            items = itemEntity.getItems(null);
        }catch(Exception e){
            itemResponse.setError(new ErrorReponse(e.getMessage(), e.getCause().toString()));
        }
        itemResponse.setInfo(new InfoReponse(items.size(), "http://localhost:8080/stock_fifo_lifo-1.0-SNAPSHOT/api/items/std"));
        itemResponse.setMessage(new MessageResponse("/stock_fifo_lifo-1.0-SNAPSHOT/api/items/std", "Get all items", "none", false, "http://locahost:3000", "200", "GET"));
        itemResponse.setItems(items);
        return itemResponse;
    }

    @GET
    @Path("/gnl")
    @Produces("application/json")
    public ItemResponse getItemsGeneral() throws Exception {
        ArrayList<Item> items = new ArrayList<Item>();
        ItemEntity itemEntity = new ItemEntity();
        ItemResponse itemResponse = new ItemResponse();
        try{
            items = itemEntity.getOverItem(null);
        }catch(Exception e){
            itemResponse.setError(new ErrorReponse(e.getMessage(), e.getCause().toString()));
        }
        itemResponse.setInfo(new InfoReponse(items.size(), "http://localhost:8080/stock_fifo_lifo-1.0-SNAPSHOT/api/items/gnl"));
        itemResponse.setMessage(new MessageResponse("/stock_fifo_lifo-1.0-SNAPSHOT/api/items/gnl", "Get all general items", "none", false, "http://locahost:3000", "200", "GET"));
        itemResponse.setItems(items);
        return itemResponse;
    }

}