package com.example.stock_fifo_lifo.reponseObject;

import com.example.stock_fifo_lifo.models.Store;
import com.example.stock_fifo_lifo.reponseObject.details.ErrorReponse;
import com.example.stock_fifo_lifo.reponseObject.details.InfoReponse;
import com.example.stock_fifo_lifo.reponseObject.details.MessageResponse;

import java.util.ArrayList;

public class StoreResponse implements java.io.Serializable{
    private InfoReponse info;
    private MessageResponse message;
    private ErrorReponse error;
    private ArrayList<Store> stores;

    public StoreResponse() {
    }

    public StoreResponse(InfoReponse info, MessageResponse message, ErrorReponse error, ArrayList<Store> stores) {
        this.info = info;
        this.message = message;
        this.error = error;
        this.stores = stores;
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

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void setStores(ArrayList<Store> stores) {
        this.stores = stores;
    }
}
