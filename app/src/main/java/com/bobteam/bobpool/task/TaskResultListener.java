package com.bobteam.bobpool.task;

/**
 * Created by Osy on 2018-01-19.
 */

public interface TaskResultListener< E > {
    void taskResult( E result );
}
