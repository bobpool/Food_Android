package com.bobteam.bobpool.task.manager;

import com.bobteam.bobpool.task.TaskJsonRead;
import com.bobteam.bobpool.task.TaskResultListener;

import org.json.JSONObject;

/**
 * Created by Osy on 2018-01-25.
 */

public abstract class BaseTaskManager<resultType> implements TaskManager<resultType> {
    private TaskJsonRead taskJsonRead;
    private TaskResultListener<resultType> taskResultListener;

    public void setTaskJsonRead(TaskJsonRead taskJsonRead){
        this.taskJsonRead = taskJsonRead;
    }

    public void setTaskResultListener(TaskResultListener<resultType> taskResultListener) {
        this.taskResultListener = taskResultListener;
    }

    @Override
    public JSONObject read(String urlStr) {
        return taskJsonRead.read(urlStr);
    }

    @Override
    public void result(resultType result) {
        taskResultListener.taskResult(result);
    }
}