package com.bobteam.bobpool.task;

import org.json.JSONArray;

/**
 * Created by Osy on 2018-01-24.
 */

public interface JsonParsing<E> {
    E parse(JSONArray jsonArray);
}
