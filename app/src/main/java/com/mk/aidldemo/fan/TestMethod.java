package com.mk.aidldemo.fan;

import android.util.Log;

/**
 * Created by liguoying on 2017/10/20.
 */

public class TestMethod {
    public <T> void fun(T t) {
        Log.e("", t.getClass().getName());
    }
}
