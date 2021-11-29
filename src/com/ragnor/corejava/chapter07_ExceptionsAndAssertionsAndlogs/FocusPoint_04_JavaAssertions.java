package com.ragnor.corejava.chapter07_ExceptionsAndAssertionsAndlogs;

import java.util.Scanner;

public class FocusPoint_04_JavaAssertions {
    public static void main(String[] args) {
        // Topic-01: 最早引入断言机制的时候是因为，如果你写大量的"测试代码"去测试你的程序时符合你预期的，那么这部分自测代码会严重影响程序运行的速度==》而java引入assert关键字去做校验，而且可以通过设置运行时的参数控制是否执行assert代码
        // Topic-02: 断言是一种测试和调试阶段所使用的的战术性工具 VS 日志记录是一种在程序的整个生命周期都可以使用的策略性工具
        JavaAssertionConcept();
        EnableAndDisableAssertion();
        UseAssertionsToCheckParameter();
        UseAssertionsForDocumentAssumptions();
    }

    // FocusSubPoint-01：What is the concept of assertion in java？
    public static void JavaAssertionConcept(){
        // Topic-01: 断言机制允许在测试期间向代码中插入一些检查语句==》当代码发布时，这些插入的检测语句会被自动的移走==》这就是断言机制的概念
        // Topic-02: 语法就是assert 条件； 或者 assert 条件：表达式； ==》都会对条件进行检测，如果条件为false则抛出Assertion异常
        Scanner in = new Scanner(System.in);
        System.out.println("please input x value: ");
        int x = in.nextInt();
        assert x >= 0;  // 只想断言x是一个非负数值
        assert x <= 0 : x; // 区别就是这里会将x的实际值传给AssertionError对象，进而可以在后面显示出来

    }

    // FocusSubPoint-02: How to enable and disable assertion in java?
    public static void EnableAndDisableAssertion(){

        // Before: Run - Edit Configurations - Vm Option -ea即可
        // Topic-01: 默认情况下断言被禁用==》可以在运行程序的时候用 -enableassertions 或 -ea选项启用： java -enableassertions MyApp
        // Topic-02: 启动或者禁用断言时，不必重新编译程序 ==》因为这个启动、禁用是类加载器（class loader）的功能，类加载器将会跳过断言代码，因此不会降低程序运行的速度
        // Topic-03: 也可以在某个类或者包中使用断言==》java -ea:MyClass -ea:com.mycompany.mylib...MyApp ==》开启MyClass类以及在com.mycompany.mylib包和它的子包中的所有类的断言
        // Topic-04: 也可以用选项 -disableassertions或者-da禁用某个特定类和包的断言==》java -ea:... -da:MyClass MyApp
        // Topic-05: 启动和禁用所有断言的-ea和-da开关不能应用到没有类加载器的"系统类"上==》对于这些系统类来说 ==》需要使用-enablesystemassertions/-esa 开关来启用断言
        System.out.println("java -ea:FocusPoint_04_JavaAssertions.JavaAssertionConcept");

    }

    // FocusSubPoint-03: How to use assertions to complete parameter checking in java?
    public static void UseAssertionsToCheckParameter(){
        // Topic-01: java语言中给出了3中处理系统错误的机制：
        //          . 抛出一个异常
        //          . 日志
        //          . 使用断言

        // Topic-02: 什么时候应该使用断言呢？==》断言只应该用于在测试阶段确定程序内部的错误位置
        //          . 断言失败是致命的，不可恢复的错误
        //          . 断言检查只用于开发和测试阶段

        // Topic-03: 什么意思呢就是说我下面要用到的值比如不能为负数，我就在前置条件中断言大于等于0
        Scanner in = new Scanner(System.in);
        System.out.println("please input a illegal value [0, +oo):");
        int para_01 = in.nextInt();
        assert para_01 >= 0;  // 测试阶段哈
        System.out.println(para_01 + " must be a >= 0 number");

    }

    // FocusSubPoint-04: How to use assertions for document assumptions in java?
    public static void UseAssertionsForDocumentAssumptions(){
        Scanner in = new Scanner(System.in);
        System.out.println("please input your value: ");
        int value_01 = in.nextInt();

        // 问题在于：一个数 % 3 如果为正则余数0， 1， 2 如果为 负则 -1， -2下面的情况
        if (value_01 % 3 == 0){
            System.out.println(value_01);
        }else if (value_01 % 3 == 1){
            System.out.println(value_01);
        }else{  // we know value % 3 == 2  //没有assert之前通过注释，但是忘记了还有-1， -2
//            ...
            assert value_01 % 3 == 2: value_01;  // 推荐，这样就可以避免忘记-1， -2的情况，会报错
            System.out.println(value_01 + "must be + times");
        }


    }

}
