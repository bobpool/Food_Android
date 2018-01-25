package com.bobteam.bobpool.task;


import android.util.Log;

import com.bobteam.bobpool.vo.UserVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-24.
 */

public class TestParsing implements JsonParsing<UserVO> {

    @Override
    public UserVO parse(JSONArray jsonArray) {
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
