package com.bobteam.bobpool;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Osy on 2018-01-19.
 */

public class UserSettingActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView firstAddressText;
    private TextView secondAddressText;

    private View searchAddressView;
    private Dialog searchAddressDialog;
    private ProgressDialog progressDialog;

    private TextView targetAddressText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userset);

        initView();
        initProgressDialog();
    }

    private void initView(){
        Button addressSetBtn1 = findViewById(R.id.address_set_btn1);
        Button addressSetBtn2 = findViewById(R.id.address_set_btn2);
        addressSetBtn1.setOnClickListener(this);
        addressSetBtn2.setOnClickListener(this);

        firstAddressText = findViewById(R.id.first_address);
        secondAddressText = findViewById(R.id.second_address);
    }

    private void initProgressDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("다음 우편번호 API를 가져오는 중 입니다. \n 잠시만 기다려주세요.");
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(){
        searchAddressView = getLayoutInflater().inflate(R.layout.dialog_daum_address, null, false);

        WebView webView = searchAddressView.findViewById(R.id.address_search);
        webView.getSettings().setJavaScriptEnabled(true);   // JavaScript 허용
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);   // JavaScript의 window.open 허용
        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        // 두 번째 파라미터는 사용될 php에도 동일하게 사용해야함
        webView.addJavascriptInterface(new AndroidBridge(), "TestApp");
        webView.setWebChromeClient(new WebChromeClient());  // web client 를 chrome 으로 설정
        webView.loadUrl("http://oks153123.dothome.co.kr/getAddress.php");
        webView.setWebViewClient(new WebViewClient() {
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
                progressDialog.show();
            }

            //페이지 로딩 종료시 호출
            public void onPageFinished(WebView view,String Url){
                progressDialog.cancel();
            }
        });

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, 1200);

        searchAddressDialog = new Dialog(this);
        searchAddressDialog.setContentView(searchAddressView, layoutParams);
        searchAddressDialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.address_set_btn1:
                targetAddressText = firstAddressText;
                break;
            case R.id.address_set_btn2:
                targetAddressText = secondAddressText;
                break;
        }

        initWebView();


    }


    private class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    targetAddressText.setText(String.format("%s %s",arg2, arg3));
                    Log.e("test address : "," 1번주소 : " +  arg1 +  " ,2번주소 :  " + arg2 + " ,3번주소 : " + arg3);

                    searchAddressDialog.cancel();

                    /*// WebView를 재사용하기위해 초기화를 해줍시다.
                    initWebView();*/
                }
            });
        }
    }
}
