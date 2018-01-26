package com.bobteam.bobpool.task.manager;

import org.json.JSONArray;

/**
 * Created by Osy on 2018-01-24.
 */

public interface TaskManager<E> {
    String setUrl(String ... params);
    JSONArray read(String urlStr);
    E parse(JSONArray jsonArray);
    void result(E result);
}
