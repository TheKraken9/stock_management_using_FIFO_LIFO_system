package com.example.stock_fifo_lifo;

import com.example.stock_fifo_lifo.entity.FlowEntity;
import com.example.stock_fifo_lifo.models.Flow;
import com.example.stock_fifo_lifo.reponseObject.OutResponse;
import jakarta.ws.rs.*;

@Path("/out")
public class OutResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @POST
    @Consumes("application/json")
    public OutResponse out(Flow flow) throws Exception{
        Flow result = new Flow();
        boolean result_state = false;
        OutResponse outResponse = new OutResponse();
        try{
            FlowEntity flowEntity = new FlowEntity();
            result_state = flowEntity.out(null, flow.getId_item(), flow.getId_store(), flow.getDate(), flow.getQuantity());
        }catch(Exception e){
            outResponse.setMessage(e.getMessage());
            outResponse.setStatus_code("500");
        }
        if(result_state){
            outResponse.setMessage("Out success");
            outResponse.setStatus_code("201");
        }
        return outResponse;
    }
}