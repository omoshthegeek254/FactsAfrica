
package com.example.factsafrica.ui.models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Supplier implements Serializable
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
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    private final static long serialVersionUID = -6220767745054622841L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Supplier() {
    }

    /**
     * 
     * @param name
     * @param description
     * @param location
     * @param id
     * @param categoryId
     */
    public Supplier(Integer id, String name, String description, String location, Integer categoryId) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.categoryId = categoryId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}
