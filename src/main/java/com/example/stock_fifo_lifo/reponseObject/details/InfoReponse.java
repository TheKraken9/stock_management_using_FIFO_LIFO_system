package com.example.stock_fifo_lifo.reponseObject.details;

public class InfoReponse implements java.io.Serializable{
    public int count;
    public String uri;

    public InfoReponse() {
    }

    public InfoReponse(int count, String uri) {
        this.count = count;
        this.uri = uri;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
