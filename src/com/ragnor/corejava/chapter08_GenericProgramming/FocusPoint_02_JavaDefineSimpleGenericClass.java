package com.ragnor.corejava.chapter08_GenericProgramming;

public class FocusPoint_02_JavaDefineSimpleGenericClass {
    public static void main(String[] args) {
        // 精髓：一个泛型类（generic class）就是具有一个或多个类型变量的类
        // java泛型类类似于c++的模板类，不同的就是java中没有c++的template关键字
        DefineSimpleGenericClass();

    }

    // FocusPoint-01：How to define Simple Generic Class？
    public static void DefineSimpleGenericClass(){
        // Topic-01: 在java库中类型变量一般使用：E ==> 表示集合的元素类型  K V ==> 表示表的关键字与值的类型  T U S ==> 表示任意类型
        // Topic-02: 换句话说：泛型类可以看做普通类的工厂
        // 构造对象 - 带有构造器的普通类
        Pair<String, Integer> demo = new Pair<>("ragnor", 18);
        Pair<String, String> demo2 = new Pair<>();
        // 方法
        System.out.println(demo.getFirst());
        System.out.println(demo2.getSecond());

    }

}

/**一个泛型类（generic class）就是具有一个或多个类型变量的类
 * 我们只关注泛型，而不会为了数据存储的细节烦恼 */

class Pair<T, U>{ // 类定义中的类型变量指定方法的返回类型以及域和局部变量的类型
    private T first;  // 使用类型变量, 类型变量使用大写形式且短
    private U second;

    public Pair(){first = null; second = null;}
    public Pair(T first, U second) {this.first = first; this.second = second;}

    public T getFirst() { return first; }
    public U getSecond() { return second; }

    public void setFirst(T first) { this.first = first; }
    public void setSecond(U second) { this.second = second; }
}


