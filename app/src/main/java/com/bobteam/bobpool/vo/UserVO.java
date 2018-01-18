package com.bobteam.bobpool.vo;

/**
 * Created by Osy on 2018-01-18.
 */

public class UserVO {
    private String nickname;
    private String address;
    private String tempAddress;
    private String email;
    private boolean member;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTempAddress() {
        return tempAddress;
    }

    public void setTempAddress(String tempAddress) {
        this.tempAddress = tempAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMember(boolean member){
        this.member = member;
    }

    public boolean isMember() {
        return member;
    }
}
