package com.bobteam.bobpool.task.manager;

import android.util.Log;

import com.bobteam.bobpool.GlobalApplication;
import com.bobteam.bobpool.vo.ReviewVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Osy on 2018-01-26.
 */

public class GetReviewTaskManager extends BaseTaskManager<ArrayList<ReviewVO>> {
    @Override
    public String setUrl(String... params) {
        String restaurantName = params[0];

        String urlStr = GlobalApplication.getServerAddress();

        if (restaurantName != null)
            urlStr = urlStr + "ReviewService" + "?rt_name=" + restaurantName;

        Log.e(this.toString(), "setUrl: " + urlStr );
        return urlStr;
    }

    @Override
    public ArrayList<ReviewVO> parse(JSONObject jsonObject) {

        if ( jsonObject == null )
            return null;

        Log.e(this.toString(), "parse: " + jsonObject.length() );

        ArrayList<ReviewVO> reviewVOS = new ArrayList<>();

        try{
            JSONArray reviewArray = new JSONArray( jsonObject.getString("Review") );

            for (int i = 0 ; i < reviewArray.length() ; i++){
                JSONObject review = reviewArray.getJSONObject(i);

                ReviewVO reviewVO = new ReviewVO();
                reviewVO.setText( review.has("review_text") ? review.getString("review_text") : "" );
                reviewVO.setAuther( review.has("review_auther") ? review.getString("review_auther") : "" );

                Log.e(this.toString(), "text: " + review.getString("review_text") );
                Log.e(this.toString(), "author: " + review.getString("review_auther") );

                reviewVOS.add(reviewVO);
            }
        }
        catch ( JSONException | NullPointerException e ){
            Log.e ( this.toString() , e.toString ( ) );
        }

        Log.e("뭔데", "parse: " +reviewVOS.size() );

        return reviewVOS;
    }
}
