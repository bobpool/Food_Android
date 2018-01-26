package com.bobteam.bobpool;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by Osy on 2018-01-19.
 */

public class UserSettingActivity extends AppCompatActivity {
    private TextView addressText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userset);

        addressText = findViewById(R.id.address_text);
        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(){

        WebView webView = findViewById(R.id.address_search);
        // JavaScript 허용
        webView.getSettings().setJavaScriptEnabled(true);
        // JavaScript의 window.open 허용
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        // 두 번째 파라미터는 사용될 php에도 동일하게 사용해야함
        webView.addJavascriptInterface(new AndroidBridge(), "TestApp");
        // web client 를 chrome 으로 설정
        webView.setWebChromeClient(new WebChromeClient());
        // webview url load
        webView.loadUrl("http://oks153123.dothome.co.kr/getAddress.php");

        /*webView.setWebViewClient(new WebViewClient() {
            // 링크 클릭에 대한 반응
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
            // 웹페이지 호출시 오류 발생에 대한 처리
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String fallingUrl) {
                Toast.makeText(UserSettingActivity.this, "오류 : " + description, Toast.LENGTH_SHORT).show();
            }

            // 페이지 로딩 시작시 호출
            @Override
            public void onPageStarted(WebView view,String url , Bitmap favicon){

            }

            //페이지 로딩 종료시 호출
            public void onPageFinished(WebView view,String Url){

            }
        });*/
    }
    private class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    addressText.setText(String.format("(%s) %s / %s", arg1, arg2, arg3));
                    Log.e("test address : "," 1번주소 : " +  arg1 +  " ,2번주소 :  " + arg2 + " ,3번주소 : " + arg3);
                    // WebView를 재사용하기위해 초기화를 해줍시다.
                    initWebView();
                }
            });
        }
    }
}
