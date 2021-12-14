package com.ragnor.corejava.chapter08_GenericProgramming;

import com.ragnor.corejava.chapter05_Inherit.FocusPoint_03_JavaGenericArray;

import javax.lang.model.type.NullType;
import java.io.Serializable;

public class FocusPoint_05_JavaGenericCodeAndVirtualMachine {
    public static void main(String[] args) {
        // Tips： java中对于没有方法的接口叫做tagging 接口：标签接口
        // java泛型转换的事实：
        //          虚拟机中没有泛型，只有普通的类和方法
        //          所有的类型参数都用它们的限定类型替换
        //          桥方法被合成来保持多态 ==》产生桥方法的原因是需要进行类型分发
        //          为了保持类型安全性，必要时插入强制类型转换
        // 关闭代码检查的注解： @SuppressWarnings("unchecked")

        TypeErasure();
        TranslateGenericExpression();
        TranslateGenericMethod();
        CallLegacyCode();
    }

    // FocusSubPoint-01: What is type erasure?
    public static void TypeErasure(){
        // Topic-01: 所谓类型擦除很形象了，就是所谓的泛型类型名 会被 擦除为原始类型（raw type）并替换为限定类型的方式
        // Topic-02: 无论何时定义一个泛型类型，都自动的提供一个相应的原始类型（raw type）
        //           原始类型的名字就是删去类型参数后的泛型类型名: Pair<T> ==> Pair<Object>


        RagnorPair2$<NullType, Integer> $_ = new RagnorPair2$<>(null, 17);
        System.out.println($_.getFirst());
        System.out.println($_.getSecond());

        // Topic-03: 原始类型用第一个限定的类型变量来替换；如果没有给定限定就用Object替换



    }

    // FocusSubPoint-02: What is a translation generic expression?
    public static void TranslateGenericExpression(){
        // Topic-01: 翻译泛型表达式我不明白为什么叫这个名字，它是为了说明:当程序调用泛型方法的时候，如果擦除返回类型，编译器将会插入强制类型转换
        RagnorPair<FocusPoint_03_JavaGenericArray.EmployeeDB, FocusPoint_03_JavaGenericArray.EmployeeDB> buddies
                = new RagnorPair<>();  // 因为传入的为空，所以下面调用staff.getName 会报空指针异常
        FocusPoint_03_JavaGenericArray.EmployeeDB staff = buddies.getFirst(); // 这里面就是他说的那种翻译泛型表达式，我好像明白一点了：
        System.out.println(staff.getName());

        //    buddies 是一个泛型的对象，调用getFirst() 返回的是Object类型 ==》 那我staff明确定位为一个Employee类型的对象
        //    ==》故编译器会在buddies.getFirst()泛型表达式的返回值前加入EmployeeDB进行类型转换

        //  人家解释的是：
        //              step-01: 擦除getFirst的返回类型后将返回Object类型
        //              step-02: 编译器自动插入Employee的强制类型转换
        //      ==> 编译器把这个方法调用翻译为两条虚拟机指令
        //              对原始方法 RagnorPair.getFirst的调用
        //              将返回的Object类型强制转换为Employee类型
    }

    // FocusSubPoint-03: What is the translation generic method?
    public static void TranslateGenericMethod(){
        // Topic-01: 我也不明白为什么这个叫做翻译泛型表达式，主要的目的是为了突出：类型擦除也会出现在泛型方法中
        /*
        * 运行的时候，会对Child类的方法表进行搜索，先分析一下Child类的方法表里有哪些东西：
            1. sayHello(Object value) : 从类型被擦除后的超类中继承过来
            2. sayHello(String value) : 自己新增的方法，和超类毫无联系
            3. 一些从Object类继承来的方法，这里忽略
        * */
        Child child = new Child();
        Parent<String> parent = child;
        parent.sayHello("hello world");

        /*按理来说，这段测试代码应该不能通过编译，因为要实现多态的话，所调用的方法必须在子类中重写，
        但是在这里Child类并没有重写Parent类中的sayHello(Object value)方法，
        只是单纯的继承而已，并且新加了一个参数不同的同名方法。*/

        /*原因是编译器在Child类中自动生成了一个桥方法：
        * public void sayHello(Object value)
            {
                sayHello((String) value);
            }
        * */

        /*
         * 这个桥方法实际上就是对超类中sayHello(Obejct)的重写。
         * 这样做的原因是，当程序员在子类中写下以下这段代码的时候，本意是对超类中的同名方法进行重写
         * 但因为超类发生了类型擦除，所以实际上并没有重写成功，因此加入了桥方法的机制来避免类型擦除与多态发生冲突。
         *
         * */

        /*
        * public class Child extends Parent<String>
        {
            public void sayHello(String value)
            {
                System.out.println("This is Child class, value is " + value);
            }
        }
        * */


    }

    // FocusSubPoint-04: How do I call legacy（遗留） code?
    public static void CallLegacyCode(){
        // Topic-01: 毕竟java选择伪泛型的目的就是为了老代码的兼容性问题
        System.out.println("使用注解忽略警告！"); // Todo:需要场景支撑
    }
}

/**泛型类*/
class RagnorPair2<T, U extends Integer>{
    private T first;
    private U second;

    public RagnorPair2(){first = null; second = null;}
    public RagnorPair2(T first, U second) {this.first = first; this.second = second;}

    public T getFirst() { return first; }
    public U getSecond() { return second; }

    public void setFirst(T first) { this.first = first; }
    public void setSecond(U second) { this.second = second; }
}


/**原始类型类*/
class RagnorPair2$<Object, Integer>{ // $是我自己写的，为了不重名实际没有
    private Object first;
    private Integer second;

    public RagnorPair2$(){first = null; second = null;}
    public RagnorPair2$(Object first, Integer second) {this.first = first; this.second = second;}

    public Object getFirst() { return first; }
    public Integer getSecond() { return second; }

    public void setFirst(Object first) { this.first = first; }
    public void setSecond(Integer second) { this.second = second; }
}

/**限定类型变量*/
class RagnorPair3<T extends Comparable & Serializable> implements  Serializable{
    private T first;
    private T second;

    public RagnorPair3(){first = null; second = null;}
    public RagnorPair3(T first, T second) {this.first = first; this.second = second;}

    public T getFirst() { return first; }
    public T getSecond() { return second; }

    public void setFirst(T first) { this.first = first; }
    public void setSecond(T second) { this.second = second; }
}


/**限定类型参数-替换*/
class RagnorPair3$ implements  Serializable{
    private Comparable first;
    private Comparable second;

    public RagnorPair3$(){first = null; second = null;}
    public RagnorPair3$(Comparable first, Comparable second) {this.first = first; this.second = second;}

    public Comparable getFirst() { return first; }
    public Comparable getSecond() { return second; }

    public void setFirst(Comparable first) { this.first = first; }
    public void setSecond(Comparable second) { this.second = second; }
}


/**翻译泛型方法-擦除-桥方法*/
// step-01: 比如这样一个泛型类
//class Parent<T>
//{
//    public void sayHello(T value)
//    {
//        System.out.println("This is Parent Class, value is " + value);
//    }
//}


// step-02：编译后（类型擦除后）变成
//      对类型变量进行替换的规则有两条：
//      1、若为无限定的类型，如<T>，被替换为Object
//      2、若为限定类型，如<T extends Comparable & Serializable>，则用第一个限定的类型变量来替换，在这里被替换为Comparable

//class Parent$
//{
//    public void sayHello(Object value)
//    {
//        System.out.println("This is Parent Class, value is " + value);
//    }
//}

// step-03: 类型擦除后以一个奇怪的现象：假设有这么一个类 以及一个子类

class Parent<T>
{
    public void sayHello(T value)
    {
        System.out.println("This is Parent Class, value is " + value);
    }
}


class Child extends Parent<String>
{
    public void sayHello(String value)  // 这里的本意是重写父类的方法，问题就出来由于超类发生了类型擦除，所以实际上并没有重写成功，因为擦除后变成了public void sayHello(Object value)==>而你重写的是public void sayHello(String value)
    {
        System.out.println("This is Child class, value is " + value);
    }
}


// step-04: 有以下代码实现多态==》TranslateGenericMethod方法中

