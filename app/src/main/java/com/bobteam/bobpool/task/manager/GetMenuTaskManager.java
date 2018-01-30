package com.bobteam.bobpool.task.manager;

import android.util.Log;

import com.bobteam.bobpool.GlobalApplication;
import com.bobteam.bobpool.vo.MenuVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-26.
 */

public class GetMenuTaskManager extends BaseTaskManager<ArrayList<MenuVO>> {
    @Override
    public String setUrl(String... params) {
        String restaurantName = params[0];

        String urlStr = GlobalApplication.getServerAddress();

        if (restaurantName != null)
            urlStr = urlStr + "MenuService" + "?rt_name=" + restaurantName;

        Log.e(this.toString(), "setUrl: " + urlStr );

        return urlStr;
    }

    @Override
    public ArrayList<MenuVO> parse(JSONObject jsonObject) {
        if ( jsonObject == null )
            return null;

        Log.e(this.toString(), "parse: " + jsonObject.length() );

        ArrayList<MenuVO> menuVOS = new ArrayList<>();

        try{
            JSONArray menuArray = new JSONArray( jsonObject.getString("Menu") );

            for (int i = 0 ; i < menuArray.length() ; i++){
                JSONObject menu = menuArray.getJSONObject(i);

                MenuVO menuVO = new MenuVO();
                menuVO.setName( menu.has("menu_name") ? menu.getString("menu_name") : "" );
                menuVO.setPrice( menu.has("menu_price") ? menu.getString("menu_price") : "" );

                menuVOS.add(menuVO);
            }

            // RestaurantVo에 추가할 것들 : 사진, 메뉴, 리뷰, 별점, 즐겨찾기 등.

        }
        catch ( JSONException | NullPointerException e ){
            Log.e ( this.toString() , e.toString ( ) );
        }

        return menuVOS;
    }
}
