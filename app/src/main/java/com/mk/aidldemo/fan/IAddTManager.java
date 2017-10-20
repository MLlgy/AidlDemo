package com.mk.aidldemo.fan;

import java.util.List;

/**
 * Created by liguoying on 2017/10/20.
 */
interface IAddTManager<T> {
    /**
     * 返回boollist
     *
     * @return
     */
    List<T> getList();

    /**
     * add T
     *
     * @param t
     */
    void addT(T t);
}
