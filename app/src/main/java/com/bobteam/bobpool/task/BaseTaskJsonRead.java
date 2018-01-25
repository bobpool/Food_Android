package com.bobteam.bobpool.task;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Osy on 2018-01-24.
 */

public class BaseTaskJsonRead implements TaskJsonRead {
    @Override
    public JSONArray read(String urlStr) {
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
            Log.e ( this.toString() , e.toString ( ) );
        }

        return responseJSON;
    }
}
