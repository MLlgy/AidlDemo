package com.mk.aidldemo.fan;

/**
 * Created by liguoying on 2017/10/20.
 */

public class Holder<T, G> {
    private T t;
    private G g;

    public Holder(T t, G g) {
        this.t = t;
        this.g = g;
    }

    public void set(T intT) {
        t = intT;
    }

    public String toString(G intG) {
        return intG.toString();
    }
}
