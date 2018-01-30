package com.bobteam.bobpool.task.manager;

import android.util.Log;

import com.bobteam.bobpool.GlobalApplication;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Osy on 2018-01-30.
 */

public class OrderTaskManager extends BaseTaskManager<String> {
    @Override
    public String setUrl(String... params) {
        String restaurantName = params[0];
        String menu = params[1];
        String userName = params[2];
        String price = params[3];
        String college = params[4];
        String roomNum = params[5];

        String urlStr = GlobalApplication.getServerAddress();

        urlStr = urlStr + "PartyMatchingService" +
                "?rt_name=" + restaurantName+
                "&user_name="+ userName +
                "&user_menu="+menu+
                "&user_menu_price="+price+
                "&collage_addr="+college+
                "&room_num="+roomNum;

        Log.e(this.toString(), "setUrl: " + urlStr );

        return urlStr;
    }

    @Override
    public String parse(JSONObject jsonObject) {
        if ( jsonObject == null )
            return null;

        Log.e(this.toString(), "parse: " + jsonObject.length() );

        String str = "";

        try{
            JSONObject ok = new JSONObject( jsonObject.getString("Result") );
            str = ok.getString("result");
        }
        catch ( JSONException | NullPointerException e ){
            Log.e ( this.toString() , e.toString ( ) );
        }

        return str;
    }
}
