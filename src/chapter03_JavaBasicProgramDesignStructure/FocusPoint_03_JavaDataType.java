package chapter03_JavaBasicProgramDesignStructure;

import jdk.swing.interop.SwingInterOpUtils;

/**
 * 1、Java is a strong-style programming language ==> that means you must statement every variable it's data-type
 * 2、Java offer 8 basic data-types:
 *          4 Integer data type (Integer means no Decimal):
 *              byte
 *              short
 *              int
 *              long
 *
 *          2 Float data type (according to IEEE 754 And Float means has Decimal):
 *              float
 *              double
 *
 *          1 char in order to describe Unicode encode character +
 *              char
 *
 *          1 boolean to describe true/false
 *              boolean
 * */
public class FocusPoint_03_JavaDataType {
    public static void main(String[] args) {

        //  Tips-1：because of java want to be run every platform and get the same result,
        //         so each data-type's valueRange must be fixed）
        //  Tips-2: 1 byte(字节) = 8 bit(位)   1 bit 存放 1位二进制数
        //  Tips-3: ValueRange come from Memory: 1 byte ==> 8 bit ==> 2(0 | 1) 的8次方 ==》分正负（一半一半）故 2 的 7次方 ==》减1是因为有0
        //  Tips-4: java中十六机制：前缀0x / 0X表示 ==》 0xCAFE  ; 八进制 前缀 0  ==》010 就是 8进制的10 也就是10进制的8哈哈
        //  Tips-5: java没有任何 unsigned 类型的byte、short、int、long类型


        DataCategoryInteger();
        DatatypeFloat();
        Datatypechar();
        Datatypeboolean();

    }
    // FocusSubPoint-001
    public static void DataCategoryInteger(){
        /* 1
         * Datatype-Category: Integer
         * Name: byte
         * Memory: 1 byte(8 bit)
         * ValueRange: -2的7次方 ~ 2的7次方减1
         * Scenario： Low-level file processing OR Need-low-Memory array
         */
        byte file_size = 127;

        /* 2
         * Datatype-Category: Integer
         * Name: short
         * Memory: 2 byte（16 bit）
         * ValueRange: -2的15次方 ~ 2的15次方减1
         * Scenario：Low-level file processing OR Need-low-Memory array
         * */
        short document_size = 32767;

        /* 3
         * Datatype-Category: Integer
         * Name: int
         * Memory: 4 byte (32 bit)
         * ValueRange: -2的31次方 ~ 2的31次方减1
         * Scenario：default and most commonly used
         * */
        int company_employee_nums = 100000;

        /* 4
         * Datatype-Category: Integer
         * Name: long
         * Memory: 8 byte (64 bit)
         * ValueRange: -2的63次方 ~ 2的63次方减1 （20亿）
         * Scenario：for example:Number of humans on earth
         * */

        long earth_humans_nums = 8000000000L; // remember to add L symbol

    }

    // FocusSubPoint-002
    public static void DatatypeFloat(){
        //  Tips-1: Float 和 Double 主要是为了科学计算和工程计算而设计的，它们执行的是二进制浮点计算，所以计算结果并不是完全精确的
        //  Tips-2: Float 定义的时候需要加尾缀 F， 不加默认是D/d
        //  Tips-3: 存储浮点数的时候根据 IEEE 754分成三部分 符号位 尾数 阶码
        //        符号：s 决定这个数的正负，0 正 1 负
        //        尾数：M 是一个二进制小数，范围在 [1,2) 或者 [0,1)
        //        阶码：e 的作用是对浮点数加权，这个权重是 2 的 e 次幂，e 可能为负数

        /* 1
        * Datatype-Category: float
        * Name: float
        * Memory: 4 byte(32 bit)
        * ValueRange: ±3.40282347e + 38F (有效位数为 6~7位)
        * Scenario: need single-precision data
        * */
        float pi = 3.1415926F;

        /* 2
        * Datatype-Category: double
        * Name: double
        * Memory: 8 byte(64 bit)
        * ValueRange: ±1.79769313486231570E+308(有效位数为15位)
        * Scenario: mostly used and default type
        * */
        double pi_2 = 3.1415926;
        System.out.println(2.0 - 1.1);  // 0.89999999999 而不是 0.9，因为浮点数值运算会有舍入误差 python也一样

    }

    //  FocusSubPoint-003
    //  FocusSubPoint-004
    public static void Datatypechar(){
        // Tips-1: Unicode编码看名字就知道了是为了统一当时编码界的混乱：ASCII(美)、KOI-8(俄)、GB 18030/BIG-5(中)
        // Tips-2: java中，char类型描述了UTF-16编码中的一个代码单元
        // Tips-3: 在设计java的时候，采用了16位的unicode字符集
        // Tips-4: 强烈建议不要使用char类型，除非确实需要处理UTF-16代码单元，最好将字符串作为抽象数据类型处理
        // Tips-5: char类型的字面量值要用单引号括起来。'A' stand 65对应的字符常量 BUT "A"是包含一个字符为A的字符串

        /*
        * Datatype-Category: char
        * Name: char
        * Memory: 2 byte (16 bit)
        * ValueRange: '${}' ==> ${}为 0-9、a-z、A-Z、中文、特殊字符、其他语言等
        * Scenario: need to deal with UTF-16 code unit
        * */

        char utf_16_a = 'a';
    }

    // FocusSubPoint-005
    public static void Datatypeboolean(){
        // Tips-1: 布尔类型在python中属于数字类型（0，1）print(True + True) 是 2；
        // Tips-2: boolean在java中有两个值false 与 true，不能与整型之间互相转换

        boolean flag = true;

    }
}
