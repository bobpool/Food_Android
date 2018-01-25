package com.bobteam.bobpool.task;

import com.bobteam.bobpool.GlobalApplication;

/**
 * Created by Osy on 2018-01-24.
 */

public class TestUrlSetting implements UrlSetting {
    @Override
    public String setting(String... params) {
        String userID = params[0];

        String urlStr = GlobalApplication.getServerAddress();
        urlStr = urlStr + "&userID=" + userID;

        return urlStr;
    }
}
