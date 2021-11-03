package com.ragnor.corejava.chapter03_JavaBasicProgramDesignStructure;

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
        // Core: java的三种注释类型

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
        /*什么是浮点数？*/
        //  Tips-1: Float 和 Double 主要是为了科学计算和工程计算而设计的，它们执行的是二进制浮点计算，所以计算结果并不是完全精确的
        //  Tips-2: Float 定义的时候需要加尾缀 F， 不加默认是D/d
        //  Tips-3: 存储浮点数的时候根据 IEEE 754分成三部分 符号位 尾数 阶码
        //        符号：s 决定这个数的正负，0 正 1 负
        //        尾数：M 是一个二进制小数，范围在 [1,2) 或者 [0,1)
        //        阶码：e 的作用是对浮点数加权，这个权重是 2 的 e 次幂，e 可能为负数
        //  Tips-4: 数学中并没有浮点数的概念，虽然小数看起来像浮点数，但从不这么叫，那为什么计算机中不叫小数而是叫做浮点数呢？
        //          因为资源的限制，数学中的小数无法直接在计算机中准确的表示(Tips-6解释这件事)，为了更好的表示它，计算机科学家们就发明了浮点数==》对小数的近似表示。
        //          浮点数是相对于定点数而言的，表示小数点的位置是浮动的
        //  Tips-5: 浮点数的构成是指用【符号】【尾数】【基数】【指数】这四部分来表示的小数：2020.9 = +   2.0209  x  10³
        //                                                                     小数    符号   尾数      基数 指数
        //  Tips-6: 为什么数学中的小数无法在计算机中准确的表示？==》根本原因是，有些十进制小数是无法转化为二进制数的：
        /*
        *   二进制数                对应的十进制数         规则（"小数"的二进制和十进制转换）
        *   0.0000                      0
        *   0.0001                      0.0625        2 To 10（采用各位数值和位权相乘）0 * 2的-1次方 + 0 * 2的-2次方 + 0 * 2的-3次方 + 0 * 2的-4次方 = 0.0625
        *                                             10 To 2 (乘 2 取整，顺序排列)
        *   0.0010                      0.125
        *   0.0011                      0.1875
        *   0.0100                      0.25
        *   0.0101                      0.3125
        *
        *  ==> 发现没？ ==》在小数点后 4 位时，连续的二进制数，对应的十进制数却是不连续的，因此只能增加位数来尽可能近似的表示
        * */



        /*IEEE 754又是什么？*/
        // Tips-1: java的浮点数就是采用的IEEE 754标准==》一种规定了浮点数怎么在内存中存储方式的标准
        // Tips-2: java中的浮点数有种： float（4 byte）（32 位） 与 double(8 byte)（64 位） 那怎么存储呢？
        //         单精度浮点数：
        //         1         8                23
        //         o     oooooooo   ooooooooooooooooooooooo
        //       符号部分  指数部分           尾数部分
        //

        //         双精度浮点数：
        //         1          11                                    52
        //         o      ooooooooooo    oooooooooooooooooooooooooooooooooooooooooooooooooooo
        //       符号部分    指数部分                           尾数部分
        //   符号部分：占一位，0表示正数 1表示负数；
        //   指数部分：因为指数有正负，但是为了不占用多一个符号位，IEEEE 754采用 The Biased exponent，也就是规定2ᵉ⁻¹-1 的值是 0，其中 e 表示指数部分的位数，小于这个值表示负数，大于这个值表示正数。
        //   尾数部分：因为浮点数就是小数点是浮动的，但是具体存储时需要固定一种形式，这叫做尾数的标准化：IEEE754 规定，在二进制数中，通过移位，将小数点前面的值固定为 1。IEEE754 称这种形式的浮点数为规范化浮点数（normal number）。
        //   基数部分：计算机中只有0 | 1， 所以不用存就是 2
        //   举个例子1：十进制数 0.15625
        //           符号： 0 （0表示正，1表示负 =》 运算时0对应﹢1， 1对应﹣1）
        //           尾数： 决定了数据的精度。0.15625 转换为2进制就是 0.00101 ==> 为了让尾数标准化（首位为1）小数点右移 3位==>因为右移3位所以指数为-3
        //                 ==> 1.01 ==>因为规定第一位永远为1不用存,那就是0.01==>十进制就是0.25
        //           指数： 因为逻辑右移动3位就是 -3 ==》2ᵉ⁻¹-1 表示0 ==》2的8-1次方 - 1 ==》127 ==> -3就是124喽
        //           0.15625(10) = 0.00101(2) = 1 × 2 -³ × （1 + 0.25） (单精度浮点数0.15625的内存表示)
        //                                    = 1 × 1/8 × 1.25 = 0.15625
        //           0    01111100    01000000000000000000000 (十进制 0.15625 对应的二进制内存表示)
        //           正    124即-3     0.00101 =》1.01=》0.01（1不存） ==》0100就是

        //   举个例子2： 十进制数 2.75 ==》单精度浮点数的表示
        //   符号：正=》0
        //   尾数：2.75（10）=> 10.11（2）=》逻辑左移一位，指数为 1 =》1.011 =》 0.011（不存1）==》0110
        //   指数：1 ==》127为0 ==》 128 ==》10000000
        //   基数：2
        //   单精度浮点数的内存表示：﹢1 * 2的1次方 * （1 + 0.375）= 2.75
        //   二进制内存表示：0 10000000 01100000000000000000000

        /* 经典问题：0.1 + 0.2 = 0.30000000000000004 */
        // 0.1(10) => 0.0001100110011001100110011001100110011001100110011001101(2)
        //  的 IEEE 754表示： 1 * 2的-4次方(逻辑右移4位) * (1 + 0.10011001100110011001101（23位二进制，这里还有截取的规则，就是说你在转二进制的时候，最好就是跟待转的比如单精度有效位数是23， 双精度的是52保持一致）对应 0.6000000238418579（十进制）)
        //  = 0.10000000149011612

        // 0.2(10) => 0.001100110011001100110011001100110011001100110011001101(2)
        // => 1 * 2 的 -3次方 * （1 + 0.10011001100110011001101（2））=》 1 * 2 的 -3次方 * （1 + 0.6000000238418579（10））
        // = 0.20000000298023224

        // 所以 0.1 + 0.2 = 0.10000000149011612 + 0.20000000298023224 = 0.30000000447034836

        /* 特殊值-单精度浮点数的最大值
        *
        *
        *
        *
        * IEEE 754浮点数：
        *       1、指数全是1：特殊值
        *           尾数位全是0：infinity
        *           尾数为不全是0： NaN（not a number）
        *       2、指数全是0：非规范化浮点数
        *           用于表示 +0 和-0，单精度浮点数范围：（-2的-126次方， 2的-126次方）
        *       3、指数不全0且不全1：规范化浮点数，单精度浮点数范围：（-2的128次方， -2的126次方）U （2的-126次方， 2的128次方）
        *
        * -infinity 规范化数  非规范化数 0 非规范化数 规范化数 +infinity
        *
        *
        * */


        /* 1
        * Datatype-Category: float
        * Name: float
        * Memory: 4 byte(32 bit)
        * ValueRange: ±3.40282347e + 38F (有效位数为 6~7位) ==》 （±3.4 * 10 的28次方）
        * Scenario: need single-precision data
        * */
        float pi = 3.1415926F;


        /* 2
        * Datatype-Category: double
        * Name: double
        * Memory: 8 byte(64 bit)
        * ValueRange: ±1.79769313486231570E+308(有效位数为15位) ==》 （±1.7 * 10 的308次方）
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
        // Tips-5: char类型的字面量值要用单引号括起来。'A' stand 65对应的字符常量 BUT ma"A"是包含一个字符为A的字符串

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
