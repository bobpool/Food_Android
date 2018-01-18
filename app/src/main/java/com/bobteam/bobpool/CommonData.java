package com.bobteam.bobpool;

import android.app.Application;

import com.bobteam.bobpool.vo.UserVO;
import com.kakao.usermgmt.response.model.UserProfile;

/**
 * Created by Osy on 2018-01-19.
 */

public class CommonData extends Application {
    private UserVO userVO;
    private static UserProfile userProfile;

    //서버주소 들어가야함.
    private static final String ADDRESS = "";

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public static String getCheckUserIdAddress(String userID){
        //userID를 이용하여 url 리턴하도록 바꿔야함.
        return ADDRESS ;
    }

    public static UserProfile getUserProfile() {
        return userProfile;
    }

    public static void setUserProfile(UserProfile userProfile) {
        CommonData.userProfile = userProfile;
    }
}
