package com.bobteam.bobpool.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.bobteam.bobpool.GlobalApplication;
import com.bobteam.bobpool.vo.UserVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-19.
 */


public class CheckUserTask extends AsyncTask < String , Integer , UserVO>{
    private static final String TAG = CheckUserTask.class.toString ( );

    private ProgressDialog progressDialog;
    private Context context;

    public CheckUserTask ( Context context ){
        this.context = context;
    }

    @Override
    protected void onPreExecute ( ){
        progressDialog = ProgressDialog.show ( context , "" , "Loading..." , true , false );
        super.onPreExecute ( );
    }

    @Override
    protected UserVO doInBackground ( String ... params ){
        if(isCancelled ( ))
            return null;
System.out.println("for test");
        URL url;
        String userID = params [ 0 ];
        String response;
        JSONArray responseJSON = null;

        try{
            String urlStr = GlobalApplication.getCheckUserIdAddress(userID);

            url = new URL ( urlStr );
            // GET 방식
            URLConnection conn = url.openConnection ( );
            conn.setUseCaches ( false );
            InputStream is = conn.getInputStream ( );

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream ( );
            byte [ ] byteBuffer = new byte [ 1024 ];
            byte [ ] byteData = null;
            int nLength = 0;

            while ( ( nLength = is.read ( byteBuffer , 0 , byteBuffer.length ) ) != -1 ){
                byteArrayOutputStream.write ( byteBuffer , 0 , nLength );
            }

            byteData = byteArrayOutputStream.toByteArray ( );

            if ( byteData.length <= 0 ){
                return null;

            }

            response = new String ( byteData );

            responseJSON = new JSONArray ( response );

            byteArrayOutputStream.close ( );
            is.close ( );
        } catch ( IOException | JSONException e ){
            Log.e ( TAG , e.toString ( ) );
        }

        return getResultList ( responseJSON );
    }

    // JSON 파싱
    private UserVO getResultList ( JSONArray jsonArray ){
        if ( jsonArray == null )
            return null;

        ArrayList< UserVO > arrayList = new ArrayList < UserVO > ( );

        for ( int i = 0 ; i < jsonArray.length ( ) ; i++ ){
            JSONObject jsonObject;
            UserVO userVO = new UserVO ( );

            try{
                jsonObject = jsonArray.getJSONObject ( i );
                userVO.setMember (jsonObject.has("member") && Boolean.parseBoolean(jsonObject.getString("member")));
                userVO.setNickname ( jsonObject.has ( "nickname" ) ? jsonObject.getString ( "nickname" ) : null );
                userVO.setAddress ( jsonObject.has ( "address" ) ? jsonObject.getString ( "address" ) : null );
                userVO.setTempAddress ( jsonObject.has ( "tempAddress" ) ? jsonObject.getString ( "tempAddress" ) : null );
                userVO.setEmail ( jsonObject.has ( "tempAddress" ) ? jsonObject.getString ( "tempAddress" ) : null );
            }
            catch ( JSONException | NullPointerException e ){
                Log.e ( TAG , e.toString ( ) );
            }

            arrayList.add ( userVO );
        }
        return arrayList.get ( 0 );
    }

    @Override
    protected void onPostExecute ( UserVO result ){
        progressDialog.dismiss ( );
        ( (TaskResultListener< UserVO >) context ).taskResult ( result );
        super.onPostExecute ( result );
    }
}
