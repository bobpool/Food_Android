package com.bobteam.bobpool.main_list;

/**
 * Created by Osy on 2018-01-14.
 */

public class ListProvider {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public ListProvider setName(String name) {
        this.name = name;

        return this;
    }

    public String getAddress() {
        return address;
    }

    public ListProvider setAddress(String address) {
        this.address = address;

        return this;
    }
}