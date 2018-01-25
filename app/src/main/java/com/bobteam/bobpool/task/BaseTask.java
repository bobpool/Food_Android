package com.bobteam.bobpool.task;

import android.os.AsyncTask;

/**
 * Created by Osy on 2018-01-24.
 */

public class BaseTask<E> extends AsyncTask<String, Integer, E> {
    private TaskManager<E> manager;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(manager == null){
            throw new NullPointerException("매니저넣어줘요");
        }
    }

    @Override
    protected E doInBackground(String... strings) {
        return manager.run(strings);
    }

    public void setManager(TaskManager<E> manager){
        this.manager = manager;
    }

    @Override
    protected void onPostExecute(E e) {
        super.onPostExecute(e);

        //ㅇㅇ..;
    }
}
