package chapter03_JavaBasicProgramDesignStructure;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FocusPoint_09_JavaBigNumericType {
    public static void main(String[] args) {
        // Topic-01: 如果基本的整数和浮点数精度不能够满足需求，那么可以使用java.math包中的两个很有用的类： BigInteger 和 BigDecimal
        // Topic-02: 这两个类可以处理任意长度数字序列的数值：BigInteger类实现了任意精度的整数运算；BigDecimal实现了任意精度的浮点数运算。
        BigNumericType();
    }

    // FocusSubPoint-001：what is Big Numeric type?
    public  static void BigNumericType(){
        // Topic-01: 使用valueOf方法可以将普通的数值转换为大数值
        int a = 10;
        BigInteger a_big = BigInteger.valueOf(a);

        float b = 1.234F;
        BigDecimal b_big = BigDecimal.valueOf(b);
        // Topic-02: 遗憾的是，不能使用人们熟悉的算术运算符：+ * 等处理大数值；而需要使用大数值类中的add 和 multiply方法
        // Topic-03: 根本的原因是因为与C++ 不同，java没有提供运算符重载的功能=》就无法重定义 + * 等元运算符使其应用与 BigInteger/BigDecimal中
        BigInteger c_big = a_big.add(a_big); // c_big = a_big + a_big
        BigInteger d_big = c_big.multiply(a_big.add(BigInteger.valueOf(10))); // d_big = c_big * (a_big + 10)

        System.out.println(d_big);
    }


}
