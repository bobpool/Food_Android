package com.bobteam.bobpool.task;

import android.os.AsyncTask;

/**
 * Created by Osy on 2018-01-24.
 */

public abstract class BaseTask2<E> extends AsyncTask<String, Integer, E> {
    private UrlSetting urlSetting;
    private JsonRead jsonRead;
    private JsonParsing<E> jsonParsing;

    @Override
    protected void onPostExecute(E e) {
        super.onPostExecute(e);

        urlSetting = setUrlSetting();
        jsonRead = setJsonRead();
        jsonParsing = setJsonParsing();
    }

    @Override
    protected E doInBackground(String... strings) {
        return jsonParsing.parse( jsonRead.read (urlSetting.setting(strings)));
    }

    abstract UrlSetting setUrlSetting();
    abstract JsonRead setJsonRead();
    abstract JsonParsing<E> setJsonParsing( );
}
