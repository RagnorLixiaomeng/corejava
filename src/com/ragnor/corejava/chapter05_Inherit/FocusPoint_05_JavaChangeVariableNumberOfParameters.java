package com.ragnor.corejava.chapter05_Inherit;

import java.util.Arrays;

public class FocusPoint_05_JavaChangeVariableNumberOfParameters {
    public static void main(String[] args) {
        // Topic-01: Object...args中的...是java代码的一部分，表示这个方法可以接收任意数量的对象
        ChangingParameters("li", 1, 2, 3, 4, "hello");  // li~[1, 2, 3, 4, hello]
    }

    public static void ChangingParameters(String name, Object...args){
        System.out.println(name + "~" + Arrays.toString(args));
    }
}
