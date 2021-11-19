package com.ragnor.corejava.chapter05_Inherit;

public class FocusPoint_08_JavaDesignInheritedTechniques {
    public static void main(String[] args) {
        // Topic-01： 将公共操作和域放在超类 ==》 将name域抽出来放在Person类中若不是Employee和Student类中

        // Topic-02:  不要使用受保护的域 ==》 protected 方法对于指示那些不提供一般用途而应在子类中重新定义的方法很有用,这是优点
        //            缺点：第一，子类集合是无限制的，任何一个人都可以由某个类派生出一个子类，并编写代码以访问protected的实例域 ==》破坏了封装性
        //                 第二，在java中同一个包中的所有的类都可以访问protected域，而不管它是否为这个类的子类

        // Topic-03:  使用继承实现 "is-a"关系  ==》 但是合适的用，可以节省代码，但不可滥用，重要还是权责

        // Topic-04:  除非所有继承的方法都有意义，否则不要使用继承

        // Topic-05:  在覆盖方法的时候，不要改变预期的行为 ==》关键在于，覆盖子类中的方法时，不要偏离最初的设计想法

        // Topic-06:  使用多态，而非类型信息 ==》 处理if() action1; else if action2; ==》如果action表达的是相同的概念==》为这个概念定义一个方法==》放置在两个类的超类或者接口中

        // Topic-07:  不要过多的使用反射 ==》对于系统程序来说运行时的监控、分析是反射的主战场，平时的应用程序不要乱用，因为它是绕过了编译器在运行时，这样会导致一定的脆弱性

        System.out.println("level up !");
    }

}
