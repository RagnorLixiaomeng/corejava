package com.ragnor.corejava.chapter05_Inherit;

import java.util.Scanner;

public class FocusPoint_06_JavaEnumClass {
    public static void main(String[] args) {
        // Topic-01: 前倾回顾：public enum Size {SMALL, MEDIUM, LARGE, EXTER_LARGE};
        //           实际上这个声明定义的类型是一个类，它刚好有4个实例，在此尽量不要构造新对象
        
        // Topic-02: 所有枚举类型都是Enum类的子类
        Scanner in = new Scanner(System.in);
        System.out.println("please input size abbreviation:");
        String opt = in.next().toUpperCase();
        // Topic-03: 返回指定名字、给定类的枚举常量
        clouthSize sz = Enum.valueOf(clouthSize.class, opt);
        System.out.println(sz);
        System.out.println(sz.getAbbreviation());

        clouthSize sz2 = clouthSize.LARGE;
        System.out.println(sz2);
        System.out.println(sz2.getAbbreviation());

    }
    
    enum clouthSize {
        SMALL("s"), MEDIUM("m"), LARGE("l");

        private String abbreviation;

        private clouthSize(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getAbbreviation(){
            return this.abbreviation;
        }
    }
}
