package com.ragnor.corejava.chapter03_JavaBasicProgramDesignStructure;

import java.util.Random;

public class FocusPoint_05_JavaCalculateSigns {
    public static void main(String[] args) {
        // Core: java运算符

        LearnJavaMath();
        CoversionNumericalDataTypes();
        CombineAssignmentAndOperators();
        SelfIncreasingAndSelfDecrementOperator();
        RelationalAndBooleanOperator();
        enumMethod();

    }

    // FocusSubPoint-001：java math class offers us which MathMethod、ConstantMethod
    public static void LearnJavaMath(){
        // Topic-01: 在java的Match类中，提供了各种各样的数学函数供使用
        // Topic-02: 不必要在数学方法名和常量名前添加前缀"Math"，只需要在原文件的顶部加上import static java.lang.Math.*;
        double score = 98;
        double target = Math.sqrt(score); // sqrt
        double dream = Math.pow(score, target); // 幂运算
        System.out.println(dream);
    }

    // FocusSubPoint-002：Conversion between Numerical data types(byte、short、int、long、float、double) - auto conversion
    public static void CoversionNumericalDataTypes(){
        // Topic-01: 数值类型之间转换无信息丢失的转换有6个（小房子换大房子 自己原来的东西不会丢）
        // Topic-02: 数值类型之间转换可能有精度损失的转换有3个（大房子换小房子 自己原来的东西可能放不下丢了）

        /* 6 numerical conversion - auto conversion - no Loss of accuracy - little house to big house wont loss your olds
        * Flow-01: byte(1 byte) => short(2 byte) => int(4 byte) => long(8 byte)
        * Flow-02: float(4 byte) => double(8 byte)
        * Flow-03: char(2 byte) => int(4 byte) => double(8 byte)
        *
        * */
        // Flow-01
        byte b_num_1 = 2;
        short s_num_1 = 2;
        int i_num_1 = 2;
        long l_num_1 = 2L;

        System.out.println(b_num_1 + s_num_1 + i_num_1 + l_num_1);
        // b_num_1 + s_num_1 ==> short(auto conversion) + short
        // + i_num_1 ==> int(result auto conversion) + int
        // + l_num_1 ==> long(result auto conversion) + int

        // Flow-02
        float f_num_1 = 3.14F;
        double d_num_1 = 3.14;

        System.out.println(f_num_1 + d_num_1);
        // f_num_1 + d_num_1 ==> double(auto conversion) + double

        // Flow-03
        char c_num_1 = 'A';
        int i_num_2 = 2;
        double d_num_2 = 4;
        System.out.println(c_num_1 + i_num_2 + d_num_2);
        // c_num_1 + i_num_1 ==> int(auto conversion) + int
        // + d_num_2 ==> double(result auto conversion) + double




        /* 3 numerical conversion - auto conversion - possible loss accuracy - big house to little house may loss your olds
        * int(4 byte) => float(4 byte)(有效位数 6-7位)
        * long(8 byte) => float(4 byte)
        * long(8 byte) => double(8 byte)（有效位数15位）
        * */

        int i_num_3 = 123456789; // 1234567891 10位
        float f_num_2 = i_num_3;
        System.out.println(f_num_2); // f_num_2 is  123456792


        // FocusSubPoint-002：Conversion between Numerical data types - force conversion
        // Tips-01: cast(强制类型转换通过截取小数部分将浮点数值转换为整型)
        double d_num_3 = 9.997;
        int i_num_4 = (int) d_num_3; // 形式
        System.out.println(i_num_4); // = 9 精度丢失
        int i_num_5 = (int)Math.round(d_num_3);
        System.out.println(i_num_5); // = 10 四舍五入



    }

    // FocusSubPoint-003：combine Assignment and operators
    public static void CombineAssignmentAndOperators(){
        // Topic-01 java中可以在赋值中使用二元运算符，这是一种方便的简写形式
        float t_height= 1.70F;
        t_height += 0.1F;
        System.out.println(t_height);
    }

    // FocusSubPoint-004：Self-increasing and Self-Decrement Operator
    public static void SelfIncreasingAndSelfDecrementOperator(){
        // Topic-01: 建议不要使用 ++ --， 因为这样的代码容易让人困惑，而且极可能引入烦人的bug
        // Topic-02: 后缀和前缀形式都会使变量值 加1 或者 减1，但用在表达式中是有本质区别的：
        //                                             1、前缀形式会先完成 +1
        //                                             2、后缀新式会使用变量原来的值

        int i_num_6 = 7;
        int i_num_7 = 7;
        int result_1 = 2 * ++i_num_6; // now result_1 is 16， i_num_6 is 8
        int result_2 = 2 * i_num_7++; // now result_2 is 14, i_num_7 is 8
        System.out.println(result_1 + " VS " + result_2);
    }

    // FocusSubPoint-005：Relational And boolean operator
    public static void RelationalAndBooleanOperator(){
        // Topic-01: java中包含丰富的关系运算符：
        // 相等 ==
        // 不相等 ！=
        // 大于 >
        // 小于 <
        // 小于等于 <＝
        // 大于等于 >=


        // 短路与 &&
        // 短路或 ||
        // 非 ！

        // 三元操作符 condition ? expression1 : expression2
        Random random = new Random();
        int comp_01 = random.nextInt();
        int comp_02 = random.nextInt();
        int min_comp = comp_01 < comp_02 ? comp_01 : comp_02;
        System.out.println(min_comp);
    }

    // FocusSubPoint-006：bit operator
    public static void BitOperator(){
        // Topic-01: 位运算中的运算符如下：
        //           and : &
        //           or  : |
        //           xor : ^
        //           not : -
        //           有符号位模式左移： <<
        //           有符号位模式右移： >>
        //           无符号右移：      >>> (不存在左)
        /* 例子：A=8=1000， B=9=1001
        *  1、 按位与&	如果相对应位都是1，则结果为1，否则为0	A&B=8，即1000
        *  2、 按位或|	如果相对应位都是0，则结果为0，否则为1	A|B=9，即1001
        *  3、 按位异或^	如果相对应位值相同，则结果为0，否则为1	A^B=1，即0001
        *  4、 位取反~	按位取反运算符翻转操作数的每一位，即0变成1，1变成0	~A=7，即0111
        *  5、 左移 <<	按位左移运算符。左操作数按位左移右操作数指定的位数	A << 2 = 32，即1000 00
        *  6、 右移 >>	按位右移运算符。左操作数按位右移右操作数指定的位数	A >> 2 = 2，即0010
        * */

        // Topic-02: 处理整数类型的时候，可以直接对组成整型数值的各个位完成操作。这意味着可以使用掩码技术得到整数中各个位
    }

    // FocusSubPoint-007：Brackets and operator levels
    public static void BracketsAndOperatorLevel(){
        // Topic-01： 运算符的级别其实正常都是从左到右，除了加（），以及右结合运算符
        /*
        *  运算符                                                    结合性
        *  [] ()(方法调用)                                          从左向右
        *  ！- ++ -- + （一元运算）- （一元运算）（）（强制类型转换）new   从右向左
        *  * / %                                                  从左向右
        *  + -                                                    从左向右
        *  << >> >>>                                              从左向右
        *  < <. > >= instanceof                                   从左向右
        *  == !=                                                  从左向右
        *  &                                                      从左向右
        *  ^                                                      从左向右
        *  |                                                      从左向右
        *  &&                                                     从左向右
        *  ||                                                     从左向右
        *  ? :                                                    从右向左
        *  = += -= *= /= %= &= |= <<= >>= >>>=                    从右向左
        *
        *
        * */

    }

    // FocusSubPoint-008：enum type
        // Topic-01: 对于变量的取值只在一个有限的集合内，可以自定义枚举类型
        // Topic-02: 枚举类型最大的意思可以指定逻辑意义
        // Topic-03: 不能在方法中定义enum
    enum Size {
        SMALL, MEDIUM, LARGE, EXTER_LARGE
    }

    public static void enumMethod(){
        System.out.println(Size.SMALL);
    }


}
