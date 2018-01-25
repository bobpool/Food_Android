package com.bobteam.bobpool.task;

import android.content.Context;
import android.util.Log;

import com.bobteam.bobpool.GlobalApplication;
import com.bobteam.bobpool.vo.RestaurantVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-23.
 */

public class GetRestaurantTask extends TestTask< ArrayList<RestaurantVO>> {
    GetRestaurantTask(Context context) {
        super(context);
    }

    @Override
    protected String urlSetting(String... params) {
        String category = params[0];

        String urlStr = GlobalApplication.getServerAddress();

        if (category != null)
            urlStr = urlStr + "&category=" + category;

        return urlStr;
    }

    @Override
    protected ArrayList<RestaurantVO> dataParse(JSONArray jsonArray) {
        if ( jsonArray == null )
            return null;

        ArrayList< RestaurantVO > arrayList = new ArrayList <> ( );

        for ( int i = 0 ; i < jsonArray.length ( ) ; i++ ){
            JSONObject jsonObject;
            RestaurantVO restaurantVO = new RestaurantVO ( );

            try{
                jsonObject = jsonArray.getJSONObject ( i );
                restaurantVO.setName ( jsonObject.has ( "name" ) ? jsonObject.getString ( "name" ) : null );
                restaurantVO.setTelNum ( jsonObject.has ( "tel_num" ) ? jsonObject.getString ( "tel_num" ) : null );
                restaurantVO.setAddress ( jsonObject.has ( "address" ) ? jsonObject.getString ( "address" ) : null );

            }
            catch ( JSONException | NullPointerException e ){
                Log.e ( this.toString() , e.toString ( ) );
            }

            arrayList.add ( restaurantVO );
        }

        return arrayList;
    }
}
