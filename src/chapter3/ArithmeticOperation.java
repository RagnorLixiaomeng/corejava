package chapter3;

import static java.lang.Math.*;

public class ArithmeticOperation {
    public static void main(String[] args){
        // Arithmetic operation： + - * / %
        int num1 = 10;
        int num2 = 20;

        System.out.println(num1 + num2);
        System.out.println(num1 - num2);
        System.out.println(num1 * num2);
        System.out.println(num1 / num2);
        System.out.println(num1 % num2);
        mathMethod();

        // 自增、自减运算符
        int niu = 10;
        int pi = 10;
        int a = 2 * ++niu; // 前缀形式会先完成 +1； a=22 niu=11
        int b = 2 * pi++; //而后缀形式会使用变量原来的值 b = 20 pi = 11
        // 建议不要在表达式中使用 ++，因为这样的代码很容易让人困惑，而且会带来烦人的bug。
        System.out.println(a);
        System.out.println(b);
    }


    public static void mathMethod() {
        double x = 4;
        int a = 2;

        double z = Math.pow(x , a); // java中没有幂运算，需要借助于,Math类中的pow方法

        double y = Math.sqrt(x); // 这个是静态方法：因为println和sqrt方法存在微小的差异。
        // println方法处理的是System.out对象。但是Math类中的sqrt方法处理的不是对象，这样的方法被称为静态方法\

        System.out.println(z + "\n" + y);



    }
}

