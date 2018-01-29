package com.bobteam.bobpool.task.manager;

import com.bobteam.bobpool.GlobalApplication;
import com.bobteam.bobpool.vo.UserVO;

import org.json.JSONObject;

/**
 * Created by Osy on 2018-01-26.
 */

public class CheckUserTaskManager extends BaseTaskManager<UserVO> {

    @Override
    public String setUrl(String... params) {
        String userID = params[0];

        String urlStr = GlobalApplication.getServerAddress();
        urlStr = urlStr + "&userID=" + userID;

        return urlStr;
    }

    @Override
    public UserVO parse(JSONObject jsonObject) {
//        if ( jsonObject == null )
//            return null;
//
//        ArrayList< UserVO > arrayList = new ArrayList < UserVO > ( );
//
//        for ( int i = 0 ; i < jsonObject.length ( ) ; i++ ){
//            JSONObject jsonObject;
//            UserVO userVO = new UserVO ( );
//
//            try{
//                jsonObject = jsonObject.getJSONObject ( i );
//                userVO.setMember (jsonObject.has("member") && Boolean.parseBoolean(jsonObject.getString("member")));
//                userVO.setNickname ( jsonObject.has ( "nickname" ) ? jsonObject.getString ( "nickname" ) : null );
//                userVO.setAddress ( jsonObject.has ( "address" ) ? jsonObject.getString ( "address" ) : null );
//                userVO.setTempAddress ( jsonObject.has ( "tempAddress" ) ? jsonObject.getString ( "tempAddress" ) : null );
//                userVO.setEmail ( jsonObject.has ( "tempAddress" ) ? jsonObject.getString ( "tempAddress" ) : null );
//            }
//            catch ( JSONException | NullPointerException e ){
//                Log.e ( this.toString() , e.toString ( ) );
//            }
//
//            arrayList.add ( userVO );
//        }
//        return arrayList.get ( 0 );
        return new UserVO();
    }
}
