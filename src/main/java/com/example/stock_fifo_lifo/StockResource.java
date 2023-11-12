package com.example.stock_fifo_lifo;

import com.example.stock_fifo_lifo.entity.StockEntity;
import com.example.stock_fifo_lifo.models.StateOfStock;
import com.example.stock_fifo_lifo.models.Stock;
import com.example.stock_fifo_lifo.reponseObject.StockResponse;
import com.example.stock_fifo_lifo.reponseObject.details.ErrorReponse;
import com.example.stock_fifo_lifo.reponseObject.details.InfoReponse;
import com.example.stock_fifo_lifo.reponseObject.details.MessageResponse;
import jakarta.ws.rs.*;

@Path("/stocks")
public class StockResource {
    @GET
    @Path("/state")
    @Produces("application/json")
    public StockResponse stockState(@QueryParam("date1") String date1, @QueryParam("date2") String date2, @QueryParam("id_item") String id_item, @QueryParam("id_Store") String id_Store) throws Exception {
        StockEntity stockEntity = new StockEntity();
        StateOfStock stateOfStock = new StateOfStock();
        stateOfStock.setDate1(date1);
        stateOfStock.setDate2(date2);
        stateOfStock.setId_item(id_item);
        stateOfStock.setId_Store(id_Store);
        System.out.println(stateOfStock.getDate1());
        StockResponse stockResponse = new StockResponse();
        String parameters = "?date1="+date1+"&date2="+date2+"&id_item="+id_item+"&id_Store="+id_Store;
        Stock result = new Stock();
        try {
            result = stockEntity.stockAvailable(null, stateOfStock.getDate1(), stateOfStock.getDate2(), stateOfStock.getId_item(), stateOfStock.getId_Store());
        }catch (Exception e){
            stockResponse.setError(new ErrorReponse(e.getMessage(), e.getCause().toString()));
        }
        stockResponse.setInfo(new InfoReponse(1, "http://localhost:8080/stock_fifo_lifo-1.0-SNAPSHOT/api/stocks/state"+parameters));
        stockResponse.setMessage(new MessageResponse("/stock_fifo_lifo-1.0-SNAPSHOT/api/stocks/state", "Get stock state", "none", false, "http://locahost:3000", "200", "GET"));
        stockResponse.setStock(result);
        return stockResponse;
    }
}