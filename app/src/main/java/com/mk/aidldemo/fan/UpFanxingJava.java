package com.mk.aidldemo.fan;

import com.mk.aidldemo.contants.Apple;
import com.mk.aidldemo.contants.Furite;
import com.mk.aidldemo.contants.Jonathan;
import com.mk.aidldemo.contants.Orange;
import com.mk.aidldemo.contants.WhateEver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liguoying on 2017/10/20.
 */

public class UpFanxingJava {
    public static void main(String[] args) {
        /**
         * 上界通配符 -- 泛型表达式的表示的对象存在最高父类（比例中的Furite）   ? extent XXXX
         * 其对象可以引用 XXX的子类
         * 比如本例中的 ? extend Furite ,其对象可以引用Furite的任何子类
         * 但是需要注意的是：
         *         使用通配符的引用，不能通过关引用调用任何带有泛型参数的方法
         *         比如本例中的 filst1.add(T t)
         *
         *    filst 指向存放 Furite 及任何子类的List容器
         */
        List<? extends Furite> filst = Arrays.asList(new Apple());
        Orange orange = (Orange) filst.get(0);
        filst.remove(new Orange());
        List<? extends Furite> filst1 = Arrays.asList(new Orange());//Orange 没什么限制，没用
        filst1.remove(new Orange());
        filst1.remove(new Apple());

        /**
         * 向下通配符 -- 泛型表达式的表示的对象存在最低子类    ？ super XXXX
         * List<? super Furite> 表示他定义的 whateverList 对象可以指向 Furite及其子类的容器
         *
         * 这里使用的是 super ，所以new ArrayList<XXX>()中的XXX只能是Furite 或其父类，
         * 因为只有这样才能确保创建出来的容器能  存放Furite及子类实例
         *
         * 下界通配符可以存放下界及下界的子类，取出的对象都是  Object
         */

        List<? super Furite> whateverList = new ArrayList<Object>();
        whateverList.add(new Apple());

        List<? super Apple> apples = new ArrayList<Furite>();
//        apples.add(new Furite());  error  apples 只可添加 Apple或其子类
        apples.add(new Apple());
        apples.add(new WhateEver());
//        Apple item1 = apples.get(0);
        Apple item2 = (Apple) apples.get(0);
        Object item3 = apples.get(1);
        Apple apple = new WhateEver();

        List<? super Jonathan> apples2 = Arrays.asList(new Apple());

        //一个能放水果以及一切是水果派生类的盘子
        //一个能放水果以及一切是水果基类的盘子
    }
}
