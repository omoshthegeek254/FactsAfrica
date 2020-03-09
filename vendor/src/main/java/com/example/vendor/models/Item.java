
package com.example.vendor.models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cost")
    @Expose
    private Double cost;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("supplierId")
    @Expose
    private Integer supplierId;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    private final static long serialVersionUID = 1480586421577242354L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item() {
    }

    /**
     * 
     * @param cost
     * @param supplierId
     * @param name
     * @param description
     * @param id
     * @param userId
     * @param categoryId
     */
    public Item(Integer id, String name, String description, Double cost, Integer categoryId, Integer supplierId, Integer userId) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.userId = userId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
