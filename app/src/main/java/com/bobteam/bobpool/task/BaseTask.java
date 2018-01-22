package com.bobteam.bobpool.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Osy on 2018-01-23.
 */

public abstract class BaseTask<E> extends AsyncTask< String, Integer, E>{
    private static final String TAG = BaseTask.class.toString ( );

    private ProgressDialog progressDialog;
    private Context context;

    private String urlStr;

    BaseTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute ( ){
        progressDialog = ProgressDialog.show ( context , "" , "Loading..." , true , false );
        super.onPreExecute ( );
    }

    @Override
    protected E doInBackground (String ... params ){
        if(isCancelled ( ))
            return null;

        urlStr = urlSetting(params);
        E e = readAndParse();

        return e;
    }

    private E readAndParse(){
        JSONArray responseJSON = null;
        String response;

        try{
            URL url = new URL(urlStr);

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

        return dataParse( responseJSON );
    }

    @Override
    protected void onPostExecute ( E result ){
        progressDialog.dismiss ( );
        ( (TaskResultListener< E >) context ).taskResult ( result );
        super.onPostExecute ( result );
    }


    // URL 세팅.
    protected abstract String urlSetting( String ... params );
    // JSON 파싱
    protected abstract E dataParse(JSONArray jsonArray );

}
