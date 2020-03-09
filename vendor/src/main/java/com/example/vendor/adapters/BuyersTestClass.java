package com.example.vendor.adapters;

public class BuyersTestClass {

    private String name;
    private int id;
    private String branch;
    private int orderId;

    public BuyersTestClass(String name, int id, String branch, int orderId) {
        this.name = name;
        this.id = id;
        this.branch = branch;
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
