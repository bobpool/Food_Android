package com.bobteam.bobpool.task;

import com.bobteam.bobpool.vo.UserVO;

/**
 * Created by Osy on 2018-01-24.
 */

public class ExtendsTask extends BaseTask2<UserVO> {
    @Override
    UrlSetting setUrlSetting() {
        return new TestUrlSetting();
    }

    @Override
    JsonRead setJsonRead() {
        return new BaseJsonRead();
    }

    @Override
    JsonParsing<UserVO> setJsonParsing() {
        return new TestParsing();
    }
}
