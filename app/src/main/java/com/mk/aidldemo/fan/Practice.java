package com.mk.aidldemo.fan;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liguoying on 2017/10/20.
 */

public class Practice {
    public <T> void funOone(T t) {
        Log.e("Tag", t.toString());
    }

    public static <K, V> Map<K, V> map() {
        return new HashMap<K, V>();
    }

    public static Map<String, List<String>> h() {
        return map();
    }

    public static void showMethod(){
        TestMethod testMethod = new TestMethod();
        testMethod.fun("");
        Practice practice = new Practice();
        Map<String,List<String>> sls = Practice.map();
    }
}
