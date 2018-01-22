package com.bobteam.bobpool.task;

import android.content.Context;
import android.util.Log;

import com.bobteam.bobpool.GlobalApplication;
import com.bobteam.bobpool.vo.RestaurantVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Osy on 2018-01-23.
 */

public class SearchRestaurantTask extends BaseTask<RestaurantVO> {
    SearchRestaurantTask(Context context) {
        super(context);
    }

    @Override
    protected String urlSetting(String... params) {
        String restaurantName = params[0];

        String urlStr = GlobalApplication.getServerAddress();

        if (restaurantName != null)
            urlStr = urlStr + "&restaurant_name=" + restaurantName;

        return urlStr;
    }

    @Override
    protected RestaurantVO dataParse(JSONArray jsonArray) {
        if ( jsonArray == null )
            return null;

        JSONObject jsonObject;

        RestaurantVO restaurantVO = new RestaurantVO();

        try{
            jsonObject = jsonArray.getJSONObject ( 0 );
            restaurantVO.setName ( jsonObject.has ( "name" ) ? jsonObject.getString ( "name" ) : null );
            restaurantVO.setTelNum ( jsonObject.has ( "tel_num" ) ? jsonObject.getString ( "tel_num" ) : null );
            restaurantVO.setAddress ( jsonObject.has ( "address" ) ? jsonObject.getString ( "address" ) : null );

            // RestaurantVo에 추가할 것들 : 사진, 메뉴, 리뷰, 별점, 즐겨찾기 등.

        }
        catch ( JSONException | NullPointerException e ){
            Log.e ( this.toString() , e.toString ( ) );
        }

        return restaurantVO;
    }
}
