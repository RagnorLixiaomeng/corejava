package chapter03_JavaBasicProgramDesignStructure;

public class FocusPoint_04_JavaDefineVariables {

    public static void main(String[] args) {
        DefineVariable();
        DefineConstant();

    }

    public static void DefineVariable(){
        // FocusSubPoint-001: the structure of define java variables

        // Tips-01: 尽管定义变量的位置可以在任意地方，凡是尽量保持变量的声明靠近第一次使用的地方，这是一种良好的编程习惯

        /*
        * Flow: define a java variable
        * step01: init variable
        * step02: Assignment variable
        *
        * */

        int age;
        age = 18;
        System.out.println(age);
    }

    public static void DefineConstant(){
        // FocusSubPoint-002：how to define Constant

        // Tips-01: java中利用关键字final指示常量
        // Tips-02: 关键字final表示这个变量只能被赋值一次，一旦被赋值之后，就不能再更改了。
        // Tips-03: 习惯上来讲常量名使用全大写
        // Tips-04: 在java中，经常希望某个常量可以在一个类中的多个方法中使用，通常将这些常量称为类常量，通过static final关键字设置类常量
        final double Race_Score = 9.76;
    }

    // FocusSubPoint-002-01: how to define class Constant in order to used by same class multi-function
    public static final char DefineClassConstant = 'A';
}
