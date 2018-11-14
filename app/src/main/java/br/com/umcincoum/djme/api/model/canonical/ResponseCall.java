package br.com.umcincoum.djme.api.model.canonical;

public class ResponseCall {
    private String status;
    private String description;

    public ResponseCall(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public ResponseCall() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}