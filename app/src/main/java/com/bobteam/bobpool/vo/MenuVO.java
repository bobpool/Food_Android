package com.bobteam.bobpool.vo;

import java.io.Serializable;

/**
 * Created by Osy on 2018-01-29.
 */

public class MenuVO implements Serializable{
    private String name;
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
