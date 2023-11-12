package com.example.stock_fifo_lifo.reponseObject;

import com.example.stock_fifo_lifo.models.Stock;
import com.example.stock_fifo_lifo.reponseObject.details.ErrorReponse;
import com.example.stock_fifo_lifo.reponseObject.details.InfoReponse;
import com.example.stock_fifo_lifo.reponseObject.details.MessageResponse;

import java.io.Serializable;

public class StockResponse implements Serializable {
    private InfoReponse info;
    private MessageResponse message;
    private ErrorReponse error;
    private Stock stock;

    public StockResponse() {
    }

    public StockResponse(InfoReponse info, MessageResponse message, ErrorReponse error, Stock stock) {
        this.info = info;
        this.message = message;
        this.error = error;
        this.stock = stock;
    }

    public InfoReponse getInfo() {
        return info;
    }

    public void setInfo(InfoReponse info) {
        this.info = info;
    }

    public MessageResponse getMessage() {
        return message;
    }

    public void setMessage(MessageResponse message) {
        this.message = message;
    }

    public ErrorReponse getError() {
        return error;
    }

    public void setError(ErrorReponse error) {
        this.error = error;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
