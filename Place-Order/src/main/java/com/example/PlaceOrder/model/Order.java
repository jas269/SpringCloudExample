package com.example.PlaceOrder.model;

import java.util.List;

public class Order {
    private Integer userId;
    private List<ProductDetail> list;

    public Order(Integer userId, List<ProductDetail> list) {
        this.userId = userId;
        this.list = list;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<ProductDetail> getList() {
        return list;
    }

    public void setList(List<ProductDetail> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", list=" + list +
                '}';
    }
}
