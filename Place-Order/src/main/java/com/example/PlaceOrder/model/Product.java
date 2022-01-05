package com.example.PlaceOrder.model;

public class Product  {

    private Integer itemId;

    private String itemName;

    private int itemCount;

    private double itemCost;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

  /*  @Override
    public String toString() {
        return "Product [itemId=" + itemId + ", itemName=" + itemName + ", itemCount=" + itemCount + ", itemCost="
                + itemCost + "]";
    }*/


}

