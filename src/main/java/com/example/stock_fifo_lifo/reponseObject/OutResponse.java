package com.example.stock_fifo_lifo.reponseObject;

public class OutResponse {
    private String message;
    private String status_code;

    public OutResponse() {
    }

    public OutResponse(String message, String status_code) {
        this.message = message;
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }
}
