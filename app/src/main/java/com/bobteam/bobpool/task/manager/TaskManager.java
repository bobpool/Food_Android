package com.bobteam.bobpool.task.manager;

import org.json.JSONObject;

/**
 * Created by Osy on 2018-01-24.
 */

public interface TaskManager<E> {
    String setUrl(String ... params);
    JSONObject read(String urlStr);
    E parse(JSONObject jsonObject);
    void result(E result);
}
