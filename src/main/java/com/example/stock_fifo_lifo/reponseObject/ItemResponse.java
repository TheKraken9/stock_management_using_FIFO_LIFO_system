package com.example.stock_fifo_lifo.reponseObject;

import com.example.stock_fifo_lifo.reponseObject.details.ErrorReponse;
import com.example.stock_fifo_lifo.reponseObject.details.InfoReponse;
import com.example.stock_fifo_lifo.models.Item;
import com.example.stock_fifo_lifo.reponseObject.details.MessageResponse;

import java.util.ArrayList;


public class ItemResponse implements java.io.Serializable{
    private InfoReponse info;
    private MessageResponse message;
    private ErrorReponse error;
    private ArrayList<Item> items;

    public ItemResponse() {
    }

    public ItemResponse(InfoReponse info, MessageResponse message, ErrorReponse error, ArrayList<Item> items) {
        this.info = info;
        this.message = message;
        this.error = error;
        this.items = items;
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

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
