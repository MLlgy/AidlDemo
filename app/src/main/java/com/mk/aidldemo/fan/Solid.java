package com.mk.aidldemo.fan;

/**
 * Created by liguoying on 2017/10/20.
 */

public class Solid<T extends Dimension & Weight> {
    private T item;
    Solid(T item){
        this.item = item;
    }
    private T getItem(){
        return item;
    }
    public int getA (){
        return item.a;
    }

    public int getWeight(){
        return item.weight();
    }

}
