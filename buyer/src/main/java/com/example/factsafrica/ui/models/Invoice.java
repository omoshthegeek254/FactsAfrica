
package com.example.factsafrica.ui.models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Invoice implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("invoiceDate")
    @Expose
    private String invoiceDate;
    private final static long serialVersionUID = 7250269546991406573L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Invoice() {
    }

    /**
     * 
     * @param name
     * @param id
     * @param invoiceDate
     * @param userId
     * @param status
     */
    public Invoice(Integer id, String name, String status, Integer userId, String invoiceDate) {
        super();
        this.id = id;
        this.name = name;
        this.status = status;
        this.userId = userId;
        this.invoiceDate = invoiceDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

}
