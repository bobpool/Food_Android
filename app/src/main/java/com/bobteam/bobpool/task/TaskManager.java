package com.bobteam.bobpool.task;

/**
 * Created by Osy on 2018-01-24.
 */

public class TaskManager<E> {
    private UrlSetting urlSetting;
    private JsonRead jsonRead;
    private JsonParsing<E> jsonParsing;

    public void setUrlSetting(UrlSetting urlSetting) {
        this.urlSetting = urlSetting;
    }

    public void setJsonRead(JsonRead jsonRead) {
        this.jsonRead = jsonRead;
    }

    public void setJsonParsing(JsonParsing<E> jsonParsing) {
        this.jsonParsing = jsonParsing;
    }

    public E run(String... strings){
        return jsonParsing.parse( jsonRead.read (urlSetting.setting(strings)));
    }
}
