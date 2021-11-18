package com.ragnor.corejava.chapter05_Inherit;

import java.util.ArrayList;

public class FocusPoint_04_JavaObjectWrapperAndAutomaticBoxing {
    public static void main(String[] args) {
        // Topic-01: 如果我需要将int这样的基本类型抓换为对象==》所有的基本类型都有一个与之对应的类，比如int对应的Integer==》通常叫做包装器（wrapper）
        //           byte   Byte
        //           short  Short
        //           int    Integer
        //           long   Long
        //           float  Float
        //           double Double

        ArrayList<Integer> auto_staff = new ArrayList<>();
        // Topic-02: 下面的调用将自动的变换成auto_staff.add(Integer.valueOf(1)); ==》这种变换就叫做自动装箱（autoboxing）
        auto_staff.add(1);

        // Topic-03: 如果将一个Integer对象（包装器）复制给一个int值时 ==》会自动变换为 int temp_int = auto_staff.get(i).intValue();
        int temp_int = auto_staff.get(0);

        // Topic-04: 装箱与拆箱是编译器认可的，而不是虚拟机==》编译器生成类的字节码 虚拟机执行字节码
        System.out.println("一句话： int ==》 Integer就是自动装箱，反之就是自动拆箱");
    }
}
