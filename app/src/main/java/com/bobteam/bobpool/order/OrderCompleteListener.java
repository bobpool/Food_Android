package com.bobteam.bobpool.order;

import java.io.Serializable;

/**
 * Created by Osy on 2018-01-30.
 */

public interface OrderCompleteListener extends Serializable{
    void orderComplete();
}
