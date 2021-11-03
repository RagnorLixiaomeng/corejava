package com.ragnor.corejava.chapter04_ClassAndObject;

public class FocusPoint_07_JavaPackage {
    public static void main(String[] args) {
        // Topic-01: java中允许使用包（package）将类组织起来
        // Topic-02: java中的包是具有层次结构的
        // Topic-03: 使用包的主要原因是确保类名的唯一性：假如两个程序员都谢了Employee类但是只要在不同的包中就避免了冲突
        // Topic-04: sun公司建议将公司的因特网域名的逆序作为包名 + 对于不同的项目使用不同的子包
        //                                      huayun.com =》 com.huayun ==> com.huayun.com.com.com.ragnor.corejava.com.ragnor.corejava
        // Topic-05: 从编译器的角度看：嵌套的包之间没有任何关系==》比如java.util 与java.util.jar毫无关系==》每一个都有独立的类集合

        ImportJavaClass();
        StaticImport();
        PutClassIntoPackage();
        JudgeScopePackage();

    }

    // FocusSubPoint-01：How to import class？
    public static void ImportJavaClass(){
        // Topic-01: 一个类可以使用所属包中的所有类 + 其他包中的公有类（public class）
        //           那怎么访问其他包中的共有类？
        //           1、在每个类名前添加完整的包名：java.time.LocalDate today = java.time.localDate.now();
        //           2、import语句（一种引用包含在包中的类的简明描述）

        // Topic-02: 可以使用import 语句导入一个特定的类或者整个包
        // Topic-03: import 语句应该位于源文件的顶部 但是 位于package语句的后面
        //           import java.util.*; / import java.time.LocalDate;
        //           LocalDate today = localDate.now();

        // Topic-04: .* 的语法比较简单，对代码的大小也没有任何负面影响；当然如果能明确的指出所导入的类，将会使代码的读者更加准确的指导加载了哪些类
        // Topic-05: 如果遇到两个包下面的类名字一样：
        //                          import java.util.*
        //                          import java.sql.*
        //                          Date today; Reeor--java.util.Date or java.sql.Date

        //           解决办法1：只使用 java.util.Date
        //                          import java.util.*
        //                          import java.sql.*
        //                          import java.util.Date

        //           解决办法2：在每个类名的前面加上完整的包名
        //                          import java.util.*
        //                          import java.sql.*
        java.util.Date deadline = new java.util.Date();
        java.sql.Date today = new java.sql.Date(1994, 12, 15);

        System.out.println(deadline);
        System.out.println(today);

    }

    // FocusSubPoint-02：What is static import？
    public static void StaticImport(){
        // Topic-01: import 语句不仅可以导入类 还 可以导入静态方法、静态域
        //           example-01: import static java.lang.System.* ==>就可以使用System类的静态方法和静态域而不用加类名前缀
        //                       out.println("helllo")

        // Topic-02: 当然那样看起来也许不清晰如果是Math呢
        //           example-02: import java.lang.Math.*
        //                       sqrt(pow(x, 2) + pow(y, 2))  VS Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))
        int x = 10;
        int y = 20;
        System.out.println(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
    }

    // FocusSubPoint-03：How to put the class in the package?
    public static void PutClassIntoPackage(){
        // Topic-01: 如果想把一个类放入包中，就必须将包的名字放在源文件的开头 + 包中定义类的代码之前 ==》我们这个就是的
        // Topic-02: 如果没有在源文件中放置package语句==》这个源文件中的类就被放置在一个默认包（default package）中==》默认包是一个没有名字的包
        // Topic-03: 要将包中的文件放到与完整的包名匹配的子目录中=》com.huayun.com.com.com.ragnor.corejava.com.ragnor.corejava 应该对应com/huayun/com.com.com.ragnor.corejava.com.ragnor.corejava
        System.out.println("OLD: package chapter04_ClassAndObject;");
        System.out.println("NEW: package com.com.com.ragnor.corejava.com.ragnor.com.com.com.ragnor.corejava.com.ragnor.corejava.chapter04_ClassAndObject;");
    }

    // FocusSubPoint-04：How to judge the scope of the package？
    public static void JudgeScopePackage(){
        // Topic-01: 包作用域：
        //                  1、标记为public的部分可以被任意的类使用；
        //                  2、标记为private的部分只能被定义它们的类使用；
        //                  3、如果没有指定public或private，这个部分（类、方法或者变量可以被同一个包中的所有方法访问）

        // Topic-02: 举个没有私有导致的修改java包的例子
        //            public class Window extends Container{ String warningSting;...}
        //            ==> java.awt包中的waringString变量不是private
        //            ==> 这意味着java.awt包中的所有类的方法都可以访问该变量 + 设置任意值（"例如：ragnor"）
        //            ==> java早期版本（1.2之前）你自己建一个java文件 把 package java.awt;放在文件头部 ==》然后把文件放在java/awt目录下
        //            ==> 就可以改这个控件的本来不可改的字段

        // Topic-03: 从1.2之后：JDK的开发者修改了类加载器==》明确地禁止加载用户自定义的、包名以"java."开始的类
        //           对我们程序员来说没好处==》但是可以通过（package sealing）机制来密封包=》就不能再向这个包添加类了==》jar包
        System.out.println("package scope!");

    }

    //



}
