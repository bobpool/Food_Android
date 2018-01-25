package com.bobteam.bobpool.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.bobteam.bobpool.vo.UserVO;

import org.json.JSONArray;

/**
 * Created by Osy on 2018-01-23.
 */

public class TestTask extends AsyncTask< String, Integer, UserVO>{
    private ProgressDialog progressDialog;
    private Context context;

    private JsonRead jsonRead;
    private UrlSetting urlSetting;
    private JsonParsing<UserVO> jsonParsing;

    TestTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute ( ){
        urlSetting = new TestUrlSetting();
        jsonRead = new BaseJsonRead();
        jsonParsing = new TestParsing();

        progressDialog = ProgressDialog.show ( context , "" , "Loading..." , true , false );
        super.onPreExecute ( );
    }

    @Override
    protected UserVO doInBackground (String ... params ){
        if(isCancelled ( ))
            return null;

        //url세팅
        String urlStr = urlSetting.setting(params);
        //json읽기
        JSONArray roadJson = jsonRead.read(urlStr);
        //파싱
        UserVO parsedVO = jsonParsing.parse(roadJson);

        return parsedVO;
    }

    @Override
    protected void onPostExecute ( UserVO result ){
        progressDialog.dismiss ( );
        ( (TaskResultListener< UserVO >) context ).taskResult ( result );
        super.onPostExecute ( result );
    }
}
