package com.bobteam.bobpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bobteam.bobpool.task.CheckUserTask;
import com.bobteam.bobpool.task.TaskResultListener;
import com.bobteam.bobpool.vo.UserVO;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

/**
 * Created by Osy on 2018-01-18.
 */

public class KakaoLoginActivity extends Activity implements TaskResultListener<UserVO>{

    private static final String TAG = KakaoLoginActivity.class.toString();

    private LoginButton loginButton;
    Session session;
    private final SessionCallback mySessionCallback = new SessionCallback();
    private CheckUserTask checkUserTask;
    private Long userId = 0L;

    private static final int NOT_USER = 0;
    private static final int OUR_USER = 1;
    private static final int NOT_HAVE_USERID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_login);

        loginButton = (LoginButton) findViewById(R.id.com_kakao_login);

        // 세션 콜백 추가
        session = Session.getCurrentSession();
        session.addCallback(mySessionCallback);
        session.checkAndImplicitOpen();

        if (session.isClosed()) {
            // 세션이 완전 종료된 상태로 갱신도 할 수 없으므로 명시적 오픈을 위한 로그인 버튼을 보여준다.
            loginButton.setVisibility(View.VISIBLE);
        } else {
            // 세션을 가지고 있거나, 갱신할 수 있는 상태로 명시적 오픈을 위한 로그인 버튼을 보여주지 않는다.
            loginButton.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        session.removeCallback(mySessionCallback);
        // onClickLogout();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("2");
        System.out.println("2 : " + resultCode);
        System.out.println("");
        //resultCode가 -1이면 return되어 바로 빠져나감.
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            System.out.println("2-1 : " + resultCode);
            System.out.println("2-1");
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("2-2");
    }

    @Override
    public void taskResult(UserVO result) {
        if (result == null) {
            Log.e(TAG, "유저 정보값을 받아오지 못했습니다.");
            Log.e("result","result :"+result);

            //서버에서 못받아오는경우 다시
            requestUserInfo(String.valueOf(userId));
            return;
        }

        //아이디값 입력
        result.setEmail(String.valueOf(userId));
        ( (GlobalApplication) getApplication()).setUserVO(result);
        checkUserTask.cancel(true);

        if (result.isMember()) {
            redirectMainActivity();
        } else {
            redirectNickNameActivity();
        }
    }

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            // 프로그레스바 종료

            Log.e(TAG, "onSessionOpened: ????????????" );
            requestMe();
        }

        @Override
        public void onSessionOpenFailed(final KakaoException exception) {
            exception.printStackTrace();
            Log.e(TAG, "onSessionOpenFailed: !!!!!!!!!!!!!!!!!" );
            // 프로그레스바 종료
            // 세션 오픈을 못했으니 다시 로그인 버튼 노출.
            loginButton.setVisibility(View.VISIBLE);
        }
    }

    /* 자동가입앱인 경우는 가입안된 유저가 나오는 것은 에러 상황.*/
    protected void showSignUp() {
        Log.d(TAG, "not registered user");
        redirectLoginActivity();
    }

    // 닉네임 설정 액티비티로 이동
    protected void redirectNickNameActivity() {
        final Intent intent = new Intent(KakaoLoginActivity.this, UserSettingActivity.class);
        intent.putExtra("userId", String.valueOf(userId));
        intent.putExtra("mode", "new");
        startActivity(intent);
        finish();
    }

    // 메인 액티비티로 이동
    protected void redirectMainActivity() {
        final Intent intent = new Intent(KakaoLoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    protected void redirectLoginActivity() {
        Intent intent = new Intent(KakaoLoginActivity.this, KakaoLoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void requestUserInfo(String userID) {
        checkUserTask = new CheckUserTask(this);
        checkUserTask.execute(userID);
    }

    /* 사용자의 상태를 알아 보기 위해 me API 호출을 한다. */
    private void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {

            @Override
            public void onSuccess(UserProfile userProfile) {
                Log.e("유저프로필","성공");
                Log.e(TAG, "UserProfile : " + userProfile);
                userProfile.saveUserToCache();
                userId = userProfile.getId();

                GlobalApplication.setUserProfile(userProfile);

                //Task서버설정후 실행하장
                //requestUserInfo(String.valueOf(userId));

                redirectMainActivity();
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.e(TAG, "FAIL.");
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {
                showSignUp();
            }

            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Log.e(TAG, message);

                Toast KakaoToast = Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT);
                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    KakaoToast.show();
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }
        });
    }
}
