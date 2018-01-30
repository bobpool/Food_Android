package com.bobteam.bobpool.task.manager;

import android.util.Log;

import com.bobteam.bobpool.GlobalApplication;
import com.bobteam.bobpool.vo.RestaurantVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-25.
 */

public class GetRestaurantsTaskManager extends BaseTaskManager<ArrayList<RestaurantVO>> {
    @Override
    public String setUrl(String... params) {
        String tb_name = params[0];
        String category = params[1];

        String urlStr = GlobalApplication.getServerAddress();

        urlStr = urlStr + "RestaurantService" + "?restaurant=" + tb_name;

        if ( category != null && category != "")
            urlStr = urlStr + "&category" + category;

        Log.e(this.toString(), "setUrl: " + urlStr );

        return urlStr;
    }

    @Override
    public ArrayList<RestaurantVO> parse(JSONObject jsonObject) {
        if ( jsonObject == null )
            return null;

        ArrayList<RestaurantVO> vos = new ArrayList<>();

        try{
            JSONArray jsonArray = new JSONArray(jsonObject.getString("Rdata"));
            for (int i = 0 ; i < jsonArray.length() ; i++){
                jsonObject = jsonArray.getJSONObject ( i );

                RestaurantVO restaurantVO = new RestaurantVO();

                restaurantVO.setName ( jsonObject.has ( "Restaurant_Name" ) ? jsonObject.getString ( "Restaurant_Name" ) : null );
                restaurantVO.setTelNum ( jsonObject.has ( "Restaurant_TellNum" ) ? jsonObject.getString ( "Restaurant_TellNum" ) : null );
                restaurantVO.setAddress ( jsonObject.has ( "Restaurant_Address" ) ? jsonObject.getString ( "Restaurant_Address" ) : null );

                vos.add(restaurantVO);
            }
            // RestaurantVo에 추가할 것들 : 사진, 메뉴, 리뷰, 별점, 즐겨찾기 등.

        }
        catch ( JSONException | NullPointerException e ){
            Log.e ( this.toString() , e.toString ( ) );
        }

        return vos;
    }
}