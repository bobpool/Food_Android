package com.bobteam.bobpool.task.manager;

import com.bobteam.bobpool.task.TaskJsonRead;
import com.bobteam.bobpool.task.TaskResultListener;

import org.json.JSONArray;

/**
 * Created by Osy on 2018-01-25.
 */

public abstract class BaseTaskManager<E> implements TaskManager<E> {
    private TaskJsonRead taskJsonRead;
    private TaskResultListener<E> taskResultListener;

    public void setTaskJsonRead(TaskJsonRead taskJsonRead){
        this.taskJsonRead = taskJsonRead;
    }

    public void setTaskResultListener(TaskResultListener<E> taskResultListener) {
        this.taskResultListener = taskResultListener;
    }

    @Override
    public JSONArray read(String urlStr) {
        return taskJsonRead.read(urlStr);
    }

    @Override
    public void result(E result) {
        taskResultListener.taskResult(result);
    }
}