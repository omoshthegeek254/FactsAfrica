
package com.example.vendor.models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchaseOrder implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("orderNumber")
    @Expose
    private String orderNumber;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("purchaseOrderDate")
    @Expose
    private String purchaseOrderDate;
    private final static long serialVersionUID = 4316274772930415811L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PurchaseOrder() {
    }

    /**
     * 
     * @param purchaseOrderDate
     * @param orderNumber
     * @param id
     * @param userId
     * @param status
     */
    public PurchaseOrder(Integer id, String orderNumber, String status, Integer userId, String purchaseOrderDate) {
        super();
        this.id = id;
        this.orderNumber = orderNumber;
        this.status = status;
        this.userId = userId;
        this.purchaseOrderDate = purchaseOrderDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getPurchaseOrderDate() {
        return purchaseOrderDate;
    }

    public void setPurchaseOrderDate(String purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
    }

}
