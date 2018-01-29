package com.bobteam.bobpool.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-14.
 */
public class RestaurantVO implements Serializable{
    private String name;
    private String address;
    private String telNum;
    private ArrayList<MenuVO> menuVOS;
    private ArrayList<ReviewVO> reviewVOS;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public ArrayList<MenuVO> getMenuVOS() {
        return menuVOS;
    }

    public void setMenuVOS(ArrayList<MenuVO> menuVOS) {
        this.menuVOS = menuVOS;
    }

    public ArrayList<ReviewVO> getReviewVOS() {
        return reviewVOS;
    }

    public void setReviewVOS(ArrayList<ReviewVO> reviewVOS) {
        this.reviewVOS = reviewVOS;
    }
}
