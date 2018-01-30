package com.bobteam.bobpool.order;

import java.io.Serializable;

/**
 * Created by Osy on 2018-01-30.
 */

public class BasketProvider implements Serializable{
    private String foodName;
    private int amount;
    private int totalPrice;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
