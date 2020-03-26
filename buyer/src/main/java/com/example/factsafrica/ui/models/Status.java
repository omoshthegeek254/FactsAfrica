package com.example.factsafrica.ui.models;

public class Status {

    private String invoice_status;

    public Status(String invoice_status) {
        this.invoice_status = invoice_status;
    }

    public String getStatus() {
        return invoice_status;
    }

    public void setStatus(String invoice_status) {
        this.invoice_status = invoice_status;
    }
}
