package com.ragnor.corejava.chapter05_Inherit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class FocusPoint_03_JavaGenericArray {
    public static void main(String[] args) {
        // Topic-01: 传统数组是存放在栈区的，栈区是有大小限制的，如果你申请了一个大于2M的数组，程序就会报错……
        //           但是动态数组是在堆空间的，理论上，内存有多大，就可以申请多大的数组。

        // Topic-02：首先数组的定义：int[] score = new int[10]; 一开始就要决定数组的存储容量太死板
        //          int actualsize = xxxx; int[] score = new int[] 这样来允许暂时不定义数组的长度，运行时再确定，但并不能解决运行时动态更改数组的问题
        //          ArrayList<int> score = new ArrayList<>(); 泛型类型为int，甚至可以变量化，重点是它使用蕲艾有点像数组，但是在添加或者删除元素的时候，具有自动调节数组容量的功能，而无需为此编写任何代码
        DefineGenericArray();
        AccessArrayListElements();
        AchieveCompatibilityBetweenTypedAndOriginalArrayList();
    }

    // FocusSubPoint-01:How to define a generic array？
    public static void DefineGenericArray(){
        // Topic-01: 格式：菱形语法 ArrayList<int> = new ArrayList<int>(); ==>new  new ArrayList<int>() 将赋值一个类型为ArrayList<int>的变量，泛型类型为int
        ArrayList<Integer> score = new ArrayList<>(3);  // 这里的3是初始容量，也就是你大概会需要3个add

        // Topic-02: 使用add方法可以将元素添加到数组列表中
        score.add(1);
        score.add(2);
        score.add(3);

        // Topic-03: 数组列表管理者对象引用的一个内部数组 ==》最终，数组的全部空间有可能被用尽==》如果调用add且内部数组已经满了==》数组列表就将自动地创建一个更大的数组，并将所有的对象从较小的数组中拷贝到较大的数组中
        score.add(4); // 发现没，一开始传入的3不是死的而是能力这是与普通数组最大的区别: 3个空位置 VS 保存3个元素的能力

        // Topic-04: 如果在调用数组前已经清楚或者能够估计出数组可能存储的元素数量==》调用ensureCapacity方法 ==》确保数组列表在不重新分配存储空间的情况下就能够保存给定数量的元素
        score.ensureCapacity(4);
        score.add(5);

        // Topic-05: 一旦确认不会再添加任何元素的时候，调用trimToSize将数组列表的容量削减到当前尺寸
        score.trimToSize();

        System.out.println(Arrays.toString(score.toArray()));

    }

    // FocusSubPoint-02：How to access array list elements？
    public static void AccessArrayListElements(){
        ArrayList<String> girls = new ArrayList<>();
        // 增
        girls.add("qingning");

        // Topic-01: 泛型数组，自动扩展容量的便利增加了访问元素语法的复杂程序：
        //
        // Topic-02: 改
        girls.set(0, "qingqing");  // 等价于 staff[0] = "harry";

        // Topic-02: 查
        System.out.println(girls.get(0));

        // Topic-03: 查数组的length
        System.out.println(girls.size());


    }

    // FocusSubPoint-03：How to achieve compatibility between typed and original array lists 怎么实现类型化与原始数组列表的兼容性？
    public static void AchieveCompatibilityBetweenTypedAndOriginalArrayList(){
        // Topic-01: 实现类型化与原始数组列表的兼容性==》就是说如何与没有使用类型参数的遗留代码交互操作
        //          注意看下面的 EmployeeDB类中的update方法，要求传入ArrayList类型 传入ArrayList<Integer>也可以,并不需要进行任何类型转换

        ArrayList<Integer> emp_int_arraylist = new ArrayList<>();
        emp_int_arraylist.add(1);

        ArrayList<String> emp_str_arraylist = new ArrayList<>();
        emp_str_arraylist.add("niu");

        EmployeeDB staff_01 = new EmployeeDB("ragnor", 18, 30000, 2021,7,5);
        // Topic-02: 尽管编译器没有给出任何错误信息或者警告，但是这样调用并不安全==》因为在update方法中，添加到数组列表中的元素可能不是Employee类型
        staff_01.update(emp_int_arraylist);  // [1]
        staff_01.update(emp_str_arraylist);  // [niu]

        // Topic-03: 将一个原始的ArrayList赋给一个类型化的ArrayList就会得到警告
        //           :Unchecked assignment: 'java.util.ArrayList' to 'java.util.ArrayList<java.lang.Integer>'

        // Raw use of parameterized class 'xxxx' 警告
        //                  ==>泛型不要使用原生态类型 会导致 丢失类型安全性
        //                  ==>意思是这个类要使用泛型 在类后面加上，<> 对应泛型
        //                  ==>为什么会出现和这个警告因为ArrayList是个泛型类，你点进去看看
        ArrayList list = new ArrayList();
        list.add("hello");

        @SuppressWarnings("unchecked")
        ArrayList<Integer> score = list;

        // Topic-04: 一旦确保这些警告不会造成什么严重的后果，可以用@SuppressWarnings("unchecked")标注来标记这个变量能够接受类型转换

    }

    public static class EmployeeDB{
        private final String name;
        private final int age;
        private final double salary;
        private final LocalDate hireDay;

        public EmployeeDB(String name, int age, double salary, int year, int month, int day){
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.hireDay = LocalDate.of(year, month, day);
        }

        public void update(ArrayList list){
            System.out.println(list.toString());
        }
    }
}
