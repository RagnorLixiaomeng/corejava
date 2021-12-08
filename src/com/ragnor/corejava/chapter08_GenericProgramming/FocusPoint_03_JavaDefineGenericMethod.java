package com.ragnor.corejava.chapter08_GenericProgramming;

import javax.swing.*;

public class FocusPoint_03_JavaDefineGenericMethod {
    public static void main(String[] args) {
        DefineGenericMethod();
    }

    // FocusPoint-03：How to Define Generic Method ?
    public static void DefineGenericMethod(){
        // Topic-01: 泛型方法可以定义在普通类中，也可以定义在泛型类中
        // Topic-02: 在调用一个泛型方法的时候，在方法名前的尖括号中放入具体的类型
        String middle = RagnorDefineGenericMethod.<String>getMiddle("john", "Q", "public");

        // Topic-03: 实际大多数情况下，方法调用中可以省略<String>类型参数，因为编译器有足够的信息能够推断出所调用的方法，
        //           他通过传入的姓名的String[] 与泛型类型T[]进行匹配推断T一定是String
        String middle2 = RagnorDefineGenericMethod.getMiddle("jod", "susan", "private");

        // Topic-04: 少数情况下，编译器会报错 ==> 我们传入的是一个double + 2个 int对象 ==》编译器会自动打包参数为1个Double + 2个Integer
        //                                  ==》找这些类的共同超类 ==》但是找到两个这样的超类型：Number 和 Comparable接口 ==》
        //                                  说明其本身就是一个泛型类型，懂？

        /*Error:(19, 61) java: 不兼容的类型: 推断类型不符合上限
            推断: java.lang.Number&java.lang.Comparable<? extends java.lang.Number&java.lang.Comparable<?>>
            上限: java.lang.Double,java.lang.Object
         * */
//        double middle3 = RagnorDefineGenericMethod.getMiddle(3.14, 1729, 0);
        double middle3 = RagnorDefineGenericMethod.getMiddle(3.14, 1729.0, 0.1);

        System.out.println(middle + " " + middle2 + " " + middle3);

        // Topic-05: 如果想要知道编译器对一个泛型方法调用最终推断出哪种类型 ==》Peter von der Ahe推荐: 有目的地引入一个错误，研究熊爱武消息即可
        JButton btn_1 = new JButton();
//        btn_1 = RagnorDefineGenericMethod.getMiddle("hello", 0, null);
        // 赋值给JButton是不可能的，所以研究报错
        //  Error:(35, 52) java: 不兼容的类型: 推断类型不符合上限
        //    推断: java.lang.Object&java.io.Serializable&java.lang.Comparable<? extends java.lang.Object&java.io.Serializable&java.lang.Comparable<?>>
        //    上限: javax.swing.JButton,java.lang.Object

        // ==> 推断得知可以将结果赋给 Object、Serializable、Comparable而不能是JButton


    }
}


class RagnorDefineGenericMethod{

    public static <T> T getMiddle(T...a){  // 定义一个带有类型参数的简单方法
        return a[a.length / 2];
    }
}
