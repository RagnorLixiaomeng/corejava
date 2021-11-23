package com.ragnor.corejava.chapter03_JavaBasicProgramDesignStructure;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;


public class FocusPoint_07_JavaInputOutput {

    public static void main(String[] args) throws IOException {
        // Core: java中输入与输出

        // Topic-01: 掌握从控制台交互 读取输入 并输出的方式
        ReadInput();
        FormatOutput();
        FileInputAndOutPut();
    }

    // FocusSubPoint-001：read input
    public static void ReadInput(){
        // Topic-01: 我们使用sout知道可以打印输出到"标转输出流"==》也就是控制台窗口是一件灰常容易的事情==》System.out.println();
        // Topic-02: 读取"标准输入流"麻烦一点点 ==》首先需要构造一个Scanner对象 ==》然后与"标准啊输入流"System.ein关联==》最后就
        //           可以使用Scanner类的各种方法实现输入操作

        Scanner ragnor_in = new Scanner(System.in);
        System.out.println(" please input your name: ");
        String read_input_name = ragnor_in.nextLine(); // get first input

        System.out.println(" how old are you: ");
        int read_input_age = ragnor_in.nextInt();

        System.out.println("Hello, " + read_input_name + ". Next year, you'll be " + (read_input_age + 1));

        // Topic-03: 因为输入是可见的==》所以不适合从控制台读取密码==》javase6中引入了Console类实现这个目的
        Console cons = System.console();
        String username = cons.readLine("User name: ");
        char [] password = cons.readPassword("Password: "); // 注意 char [],为了安全起见，返回的密码存放在一堆字符数组中，而不是字符串中，在对密码进行处理之后，应该马上用一个填充值覆盖数组元素

        // Java.io.Console 只能用在标准输入、输出流未被重定向的原始控制台中使用，在 Eclipse / Idea 或者其他 IDE 的控制台是不可用的。
        // 下面我直接写到文本中，javac编译后执行就ok了， IDE中jshell执行 System.console() 返回null你就知道了
        /*      (base) XiaomengdeMacBook-Pro:AutoTeamFuture xiaomengli$ cat scanner.
                cat: scanner.: No such file or directory
                (base) XiaomengdeMacBook-Pro:AutoTeamFuture xiaomengli$ clear
                (base) XiaomengdeMacBook-Pro:AutoTeamFuture xiaomengli$ cat scanner.java
                import java.io.Console;

                public class scanner{
                        public static void main(String[] args){

                                Console cons = System.console();
                                String username = cons.readLine("User name: ");
                                char[] password = cons.readPassword("Password: ");
                        }
                }

                (base) XiaomengdeMacBook-Pro:AutoTeamFuture xiaomengli$ java scanner
                User name: com.com.ragnor.corejava.com.ragnor
                Password:
                (base) XiaomengdeMacBook-Pro:AutoTeamFuture xiaomengli$
                */

    }

    // FocusSubPoint-002：format output
    public static void FormatOutput(){
        // Most-Important:
        // 格式说明符号：
        //   START =》% =》参数索引值 =》$ =》标志=》宽度=》. =》精度 =》转换字符 =》END  ： System.out.printf("|%2$8.2f|", 2222, 3.3333F) =>结果 |    3.33|
        //   START =》% =》参数索引值 =》$ =》标志=》宽度=》t => 转换字符 =》END ：
        // Topic-01: java se5.0 中沿用了 C语言库函数中的printf方法： 例如System.out.printf("%8.2f", x);
        // Topic-02: 每一个%字符开始的格式说明符都用相应的参数替换；格式说明符尾部的转换符将指示被格式化的数值类型：
            /*
            *  转换符                  类型                 目标值           表达式
            *  d                    十进制整数               159          System.out.printf("%d", 159); => 159
            *  x                    十六进制整数              9f           System.out.printf("%x", 159); => 9f
            *  o                    八进制整数               237          System.out.printf("%x", 159); => 237
            *  f                    定点浮点数               15.90        System.out.printf("%.2f", 15.9F); => 15.90
            *  e                    指数浮点数               1.59e+01     System.out.printf("%.2e", 15.9F); => 1.59e+01
            *  g                    通用浮点数               16           System.out.printf("%.2g", 15.9F); => 16
            *  a                    十六进制浮点数            0x1.fcccccp3 System.out.printf("%a", 15.9F); =>0x1.fcccccp3
            *  s                    字符串                  Hello        System.out.printf("%s", "Hello"); => Hello
            *  c                    字符                    H            System.out.printf("%c", 'H');=> H
            *  b                    布尔                    true         System.out.printf("%b", true);=> true
            *  h                    散列码                  a8           System.out.printf("%h", 168); =>168
            *  t / T                日期时间                              已经过时，现在使用java.time类
            *  %                    百分号                  %            System.out.printf("%%h", 168); =>%168
            *
            *
            *
            * */
        System.out.printf("%8.2f", 1.234f); // =>表示用8个字符的宽度和小数点后两个字符的精度打印x


        // Topic-03: 可以使用多个标志， 例如："%，(.2f" 使用分组的分割符并将负数括在括号内
        /* 用于printf的标志
        * 标志                        目的                       示例
        *  +                       打印正数的符号           System.out.printf("+%d", 99); => +99
        *  -                       打印负数的符号           System.out.printf("-%d", 99); => -99
        *  0                       数字前面补0             System.out.printf("|%06d|", 12); => |000012|
        *  -                       左对齐                 System.out.printf("|%-6d|", 12); =>|12    |
        *  空格                     在正数之前添加空格       System.out.printf("| %d|", 12); =>| 12|
        *  (                       将负数括在括号内         System.out.printf("(-%.2f), (%.3f)", 1.134F, 2.456F) => (-1.13), (2.456)
        *  ,                       添加分组分割符           System.out.printf("%,d", 3333333) => 3,333,333
        *  $                       给定被格式化的参数索引    System.out.printf("%2$d", 3333333, 22) => 22
        *  <                       格式化前面说明的数值      System.out.printf("%2$d, %<06d", 3333333, 22) => 22, 000022
        *
        *
        * */
        Scanner my_scanner_01 = new Scanner(System.in);

        System.out.println("please input your name: ");
        String name = my_scanner_01.nextLine();
        System.out.println("please input your age: ");
        int age = my_scanner_01.nextInt();
        String message = String.format("Hello, %s, Next year you will be %d years old!", name, age);
        System.out.println(message);

        // Topic-04: java的printf方法中日期与时间的格式化选项下面了解下：
        //           当你遇到Date类 以及 t开头 + 转换符（c/F/d/t/r/R/Y/y/C/B/b/h/m/d/e/A/a/j/H/k/I/l/M/S/L/N/p/z/Z/s/Q）
        //           就是老式的格式化打印日期的操作==》现在都是使用java.time包
        //           参考表 3-7 日期和时间的转换符

        System.out.printf("%tc", new Date()); // 周四 10月 28 09:13:34 CST 2021
        System.out.printf("%1$s %2$tB %2$te, %2$tY", "Due date:", new Date()); // Due date: 十月 28, 2021
    }

    //  FocusSubPoint-003：File input and output
    public static void FileInputAndOutPut() throws IOException {
        // Topic-01: 如果用一个不存在的文件构造一个Scanner或者用一个不能被创建的文件名构造一个PrintWriter，那么就会发生异常
        //           java编译器认为这些异常比"被零除"异常更严重，所以需要在main方法中用throws字句标记
        // Topic-02:文件的读取
        Scanner read_file_in = new Scanner(Paths.get("/Users/xiaomengli/Desktop/java全栈/ragnor_java/com.com.com.ragnor.corejava.com.ragnor.corejava/Files/hundsun_01_file.txt"), StandardCharsets.UTF_8);
        System.out.println(read_file_in.nextLine()); // line-01: o32 hundsun system

        // Topic-03: 文件的写入
        PrintWriter write_out_to = new PrintWriter("./Files/demo.txt", StandardCharsets.UTF_8);
        write_out_to.write("hello writer, remember to close the buff");
        write_out_to.flush(); // this will push cache content to local file
        write_out_to.close(); // this will finally put all the cache to the local file

        // Topic-04: com.com.com.ragnor.corejava.com.ragnor.corejava 卷1只是要求我们掌握如何读写文本数据的文件，对于更加高级的技术：处理不同字符编码、处理二进制数据、读取目录、写压缩文件在卷2

    }
}
