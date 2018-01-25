package com.bobteam.bobpool.task;

import android.content.Context;
import android.util.Log;

import com.bobteam.bobpool.GlobalApplication;
import com.bobteam.bobpool.vo.UserVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-19.
 */


public class CheckUserTask extends TestTask<UserVO> {
    public CheckUserTask( Context context ){
        super(context);
    }

    @Override
    protected String urlSetting(String... params) {
        String userID = params[0];

        String urlStr = GlobalApplication.getServerAddress();
        urlStr = urlStr + "&userID=" + userID;

        return urlStr;
    }

    @Override
    protected UserVO dataParse(JSONArray jsonArray ){
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
                Log.e ( this.toString() , e.toString ( ) );
            }

            arrayList.add ( userVO );
        }
        return arrayList.get ( 0 );
    }
}
