package com.ragnor.corejava.chapter08_GenericProgramming;

import java.util.ArrayList;

public class FocusPoint_01_JavaGenericProgrammingGoods {
    public static void main(String[] args) {
        // Topic-01: java专家组耗时5年的心血结晶:定义规范和测试实现
        // Topic-02: 使用泛型机制编写的程序代码要比哪些杂乱地使用Object变量，然后再进行强制转换的代码具有更好的安全性和可读性
        // Topic-03: java中的泛型是假泛型
        //           真泛型：泛型中的类型是真实存在的。
        //           伪泛型：仅于编译时类型检查，在运行时擦除类型信息。

        BenefitsOfJavaGeneric();
        WhoWantTobeGenericProgrammingEngineer();
    }

    // FocusSubPoint-01：What are the benefits of type parameters?
    public static void BenefitsOfJavaGeneric(){
        // Topic-01: Generic programming 泛型程序设计：意味着编写的代码可以被很多不同类型的对象所重用
        // Topic-02: 再具象一点：ArrayList<String> names = new ArrayList<String>(); ==> 一看就知道这个数组列表中包含的是String对象
        // Topic-03: 类型参数的魅力在于：使得程序具有更好的可读性和安全性

        ArrayList<Integer> taskNums = new ArrayList<>(5);
    }

    // FocusSubPoint-02：who want to be generic programming engineer?
    public static void WhoWantTobeGenericProgrammingEngineer(){
        // Topic-01: 可能有一个既定事实，就是java程序员在编写代码的时候会直接使用ArrayList<String>这样的类型，就好像一种内置类型一样，但是实现一个泛型类并不容易
        //           ==> 哪里不容易？==> 一个泛型程序员的任务就是预测出所用类的未来可能有的所有用途。
        // Topic-02: 泛型程序设计划分为3个能力级别：
        //           基本级别：仅仅使用泛型类 -- 典型的是像ArrayList这样的集合，不必考虑它们的工作方式与原因，大多数应用程序员将会停留在这一级别上，直到出现了什么问题。当把
        //                   不同的泛型类混合在一起时，或者是在与对类型参数一无所知的遗留的代码进行衔接时，可能会看到含混不清的错误消息
        //           进阶级别: 系统的学习java泛型来解决上述的问题，而不是胡乱地猜测
        //           大神级别：开发自己的泛型类与泛型方法

        System.out.println("一句话：泛型程序设计更具有通用性，可以被不同类型的对象所重用，但是设计泛型类相较于使用能力是有拔高的");
    }

    //

}

