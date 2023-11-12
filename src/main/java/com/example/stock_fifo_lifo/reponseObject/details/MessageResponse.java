package com.example.stock_fifo_lifo.reponseObject.details;

public class MessageResponse implements java.io.Serializable{
    private String endpoint;
    private String description;
    private String auth;
    private boolean https;
    private String cors;
    private String status_code;
    private String method;

    public MessageResponse() {
    }

    public MessageResponse(String endpoint, String description, String auth, boolean https, String cors, String status_code, String method) {
        this.endpoint = endpoint;
        this.description = description;
        this.auth = auth;
        this.https = https;
        this.cors = cors;
        this.status_code = status_code;
        this.method = method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public boolean isHttps() {
        return https;
    }

    public void setHttps(boolean https) {
        this.https = https;
    }

    public String getCors() {
        return cors;
    }

    public void setCors(String cors) {
        this.cors = cors;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
