package com.example.stock_fifo_lifo.reponseObject.details;

public class ErrorReponse implements java.io.Serializable{
    private String message;
    private String cause;

    public ErrorReponse() {
    }

    public ErrorReponse(String message, String cause) {
        this.message = message;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
