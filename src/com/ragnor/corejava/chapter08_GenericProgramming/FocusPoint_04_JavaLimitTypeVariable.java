package com.ragnor.corejava.chapter08_GenericProgramming;

import javax.lang.model.type.NullType;
import java.io.Serializable;
import java.util.ArrayList;

public class FocusPoint_04_JavaLimitTypeVariable {
    public static void main(String[] args) {
        // Topic-01: 这里会涉及到一个知识叫接口扩展：interfaceA extend interfaceB
        LimitTypeParameter();

    }

    // FocusPoint-04：How to limit type-parameter?
    public static void LimitTypeParameter(){
        // Topic-01：虽然你可以定义类型参数T，但你怎么知道T一定有compareTo方法呢？
        // Topic-02: 通过 T extends Interface1 & Interface2, U extend Interface3 & Interface4这样的格式来限定类型参数
        RagnorLimitTypeParameter2 rdgm = new RagnorLimitTypeParameter2();
        Integer[] check_num = {1, 2, 3};

        System.out.println(rdgm.<Integer>min(check_num));  // 1

    }

}

/**类型参数-需要限定场景*/
class RagnorLimitTypeParameter{

    public static <T> T min(T[] a)
    {
        if(a == null || a.length == 0) { return null; };
        T smallest = a[0];
        for (int i = 1; i < a.length; i++) {
//            if (smallest.compareTo(a[i]) > 0) {smallest = a[i]; }  // 问题点就在这,取消注释即可看到

        }
        return smallest;

    }
}

/**类型参数-单限定*/
class RagnorLimitTypeParameter2{

    public static <T extends Comparable> T min(T[] a)  //解决问题点在这：为什么用extends而不是implements呢？毕竟Comparable是一个接口==》选择extends的原因是更接近子类的概念
    {
        if(a == null || a.length == 0) { return null; };
        T smallest = a[0];
        for (int i = 1; i < a.length; i++) {
            if (smallest.compareTo(a[i]) > 0) {smallest = a[i]; }
        }
        return smallest;

    }
}

/**类型参数-多限定-, & */
class RagnorLimitTypeParameter3{

    public RagnorLimitTypeParameter3(){};

    public static <T extends Comparable & Serializable, U extends Serializable & NullType> T min(T[] a)  //解决问题点在这：为什么用extends而不是implements呢？毕竟Comparable是一个接口==》选择extends的原因是更接近子类的概念
    {
        if(a == null || a.length == 0) { return null; };
        T smallest = a[0];
        for (int i = 1; i < a.length; i++) {
            if (smallest.compareTo(a[i]) > 0) {smallest = a[i]; }
        }
        return smallest;

    }
}