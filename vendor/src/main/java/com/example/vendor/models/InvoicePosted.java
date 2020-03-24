package com.example.vendor.models;

public class InvoicePosted {
    private Integer buyer_id;
    private String due_date;
    private String invoice_amount;

    public InvoicePosted(Integer buyer_id, String due_date, String invoice_amount) {
        this.buyer_id = buyer_id;
        this.due_date = due_date;
        this.invoice_amount = invoice_amount;
    }


    public Integer getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(Integer buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getInvoice_amount() {
        return invoice_amount;
    }

    public void setInvoice_amount(String invoice_amount) {
        this.invoice_amount = invoice_amount;
    }
}
