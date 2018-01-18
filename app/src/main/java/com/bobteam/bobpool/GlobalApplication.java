package com.bobteam.bobpool;

import android.app.Application;
import android.content.Context;

import com.bobteam.bobpool.vo.UserVO;
import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.kakao.usermgmt.response.model.UserProfile;

/**
 * Created by Osy on 2018-01-19.
 */

public class GlobalApplication extends Application {
    private static GlobalApplication INSTANCE;
    private UserVO userVO;
    private static UserProfile userProfile;

    //서버주소 들어가야함.
    private static final String ADDRESS = "";

    private static class KakaoSDKAdapter extends KakaoAdapter {
        /**
         * Session Config에 대해서는 default값들이 존재한다.
         * 필요한 상황에서만 override해서 사용하면 됨.
         * @return Session의 설정값.
         */
        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[] {AuthType.KAKAO_LOGIN_ALL};
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Context getApplicationContext() {
                    return GlobalApplication.getGlobalApplicationContext();
                }
            };
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        KakaoSDK.init(new KakaoSDKAdapter());

    }

    public static GlobalApplication getGlobalApplicationContext() {
        if(INSTANCE == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return INSTANCE;
    }

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
        GlobalApplication.userProfile = userProfile;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        INSTANCE = null;
    }
}
