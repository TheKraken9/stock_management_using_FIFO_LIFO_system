package com.example.stock_fifo_lifo;

import com.example.stock_fifo_lifo.entity.StoreEntity;
import com.example.stock_fifo_lifo.models.Store;
import com.example.stock_fifo_lifo.reponseObject.ItemResponse;
import com.example.stock_fifo_lifo.reponseObject.StoreResponse;
import com.example.stock_fifo_lifo.reponseObject.details.ErrorReponse;
import com.example.stock_fifo_lifo.reponseObject.details.InfoReponse;
import com.example.stock_fifo_lifo.reponseObject.details.MessageResponse;
import jakarta.ws.rs.*;

import java.util.ArrayList;

@Path("/stores")
public class StoreResource {
    @GET
    @Produces("application/json")
    public StoreResponse getStores() {
        ArrayList<Store> stores = new ArrayList<Store>();
        StoreEntity storeEntity = new StoreEntity();
        StoreResponse storeResponse = new StoreResponse();
        try{
            stores = storeEntity.getStores(null);
        }catch(Exception e){
            storeResponse.setError(new ErrorReponse(e.getMessage(), e.getCause().toString()));
        }
        storeResponse.setInfo(new InfoReponse(stores.size(), "http://localhost:8080/stock_fifo_lifo-1.0-SNAPSHOT/api/stores"));
        storeResponse.setMessage(new MessageResponse("/stock_fifo_lifo-1.0-SNAPSHOT/api/stores", "Get all stores", "none", false, "http://locahost:3000", "200", "GET"));
        storeResponse.setStores(stores);
        return storeResponse;
    }
}