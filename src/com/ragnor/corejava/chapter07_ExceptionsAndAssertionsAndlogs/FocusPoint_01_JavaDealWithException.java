package com.ragnor.corejava.chapter07_ExceptionsAndAssertionsAndlogs;

import java.io.EOFException;
import java.util.Scanner;

public class FocusPoint_01_JavaDealWithException {
    public static void main(String[] args) {
        // Topic-01: 考虑一种情况：用户输入的数据永远是正确的；选择打开的文件也一定存在，并且永远不会出现bug ==》理想
        // Topic-02: 现实世界中缺充满了不良的数据和带有问题的代码 ==》现实

        // Topic-03: 人们在遇到错误的时候会觉得不爽：如果一个用户在运行程序期间由于程序的错误或一些外部环境的影响造成用户数据的丢失，用户就有可能不再使用这个程序了，故至少应该做到以下几点：
        //              . 向用户通告错误；
        //              . 保存所有的工作结果；
        //              . 允许用户以妥善的形式退出程序。

        // Topic-04: Java使用一种好处呢改为异常处理（exception handing）的错误捕获机制处理

        // Topic-05: 在测试期间可能有大量的检测以验证程序操作的正确性，然而这些检测可能非常耗时，检测完成不必保留==》java使用断言来有选择的启用检测

        // Topic-06: 当程序出现错误的时候，并不是总能与用户或者终端进行沟通 ==》java标准日志框架可以做日志收集

        JavaExceptionClassifications();

        JavaDeclareDetectedException();

        try {
            ThrowAnExceptionInJava();
        } catch (EOFException e) {
            e.printStackTrace();
        }

        try {
            CreateDiyExceptionClass();
        } catch (RagFirstExceptionJava ragFirstExceptionJava) {
            ragFirstExceptionJava.printStackTrace();
        }

    }


    // FocusSubPoint-01：What are the exception classifications in java?
    public static void JavaExceptionClassifications(){
        // Topic-01:异常处理的任务就是将控制权从错误产生的地方转移给能够处理这种情况的错误处理器 ==》而为了能够在程序中处理异常情况，必须研究程序中可能会出现的错误和问题
        /*
        *   1、用户输入错误： 除了键盘输入的错误外，用户往往喜欢各行其是，不遵守程序的要求：eg：用户请求连接一个URL但是语法却不正确，程序代码中应该对此进行检查，如果没有检查，网络层就会给出警告。
        *   2、设备错误： 硬件并不总是让它做什么，它就做什么：eg：打印机在打印过程中没有纸了
        *   3、物理限制：磁盘满了，可用存储空间已被用完了
        *   4、代码错误：程序方法有可能无法正确执行
        * */

        System.out.println("异常处理的任务就是将控制权从错误产生的地方转移给能够处理这种情况的错误处理器");

        // Topic-02: Java中异常对象都是派生于Throwable类的一个实例 ==》如果java中内置的异常类不能够满足需求，用户可以创建自己的异常类
        //           Throwable类下有Error 与 Exception子类，Exception子类下有IOException 与 Runtime Exception

        //           Error类层次结构：java运行时系统内部错误 + 资源耗尽错误 ==》应用程序不应该抛出这样的错误，除了通告用户并尽力是程序安全地终止外再也无能为力==》极少出现
        //           Exception的层次结构：由程序错误导致的异常属于Runtime Exception 而程序本身没有问题由于像I/O错误这类问题导致的异常属于其他异常
        //                                              .错误的类型转换                                                   .试图在文件尾部后面读取数据
        //                                              .数组的访问越界                                                   .试图打开一个不存在的文件
        //                                              .访问null指针                                                    .试图根据给定的字符串查找Class对象，而这个字符串表示的类并不存在

        // Topic-03: 如果出现RuntimeException异常 ==》那么一定是你的问题

        // Topic-04: 应该通过检测数组下标是否越界来避免ArrayIndexOutOfBoundsException异常
        // +         应该通过在使用变量之前检测是否为null来杜绝NullPointerException
        //+          对于不存在的文件，就不那个先检查文件是否存在再打开它吗？

        // Topic-05: java语言规范将派生于Error类或者RuntimeException类的所有异常称为非受查异常（unchecked）==》所有其他的异常称为受查（checked）异常

        System.out.println("java语言规范将派生于Error类或者RuntimeException类的所有异常称为非受查异常（unchecked）==》所有其他的异常称为受查（checked）异常");

    }

    // FocusSubPoint-02：How do I declare(声明) an detected exception in java?
    public static void JavaDeclareDetectedException(){
        // Topic-01: 如果遇到了无法处理的情况，那么Java的方法可以抛出一个异常==》不仅要告诉编译器将要返回什么值，还要告诉编译器有可能发生什么错误？
        // Topic-02: 方法应该在其首部声明所有可能抛出的异常==》这样可以从首部反映出这个方法可能抛出哪类受查异常
        /*
        * 比如：public FileInputStream(String name) throws FileNotFoundException
        *
        * 这个构造器将根据给定的String参数产生一个FileInputStream对象，但也有可能抛出一个FileNotFoundException异常==》如果发生了这种按情况，构造器将不会初始化一个新的FileInputStream对象，而是抛出一个FileNotFoundException类对象
        * ==》而如果这个方法真的抛出了这样的一个FileNotFoundException异常类对象==》运行时系统就会开始搜索异常处理器，以知道如何处理FileNotFoundException对象
        *
        * */

        // Topic-03: 在自己编写的方法中，不必将所有可能抛出的异常都进行声明 ==》规则就是记住遇到下面4种情况的时候就应该抛出异常了
        /*  1、调用一个抛出受查异常的方法 ：例如FileInputStream构造器
        *   2、程序运行过程中发现错误，并且利用throw语句抛出一个受查异常
        *   3、程序出现错误，例如 a[-1] = 0 会抛出一个ArrayIndexOutOfBoundsException这样的非受查异常
        *   4、java虚拟机和运行时库出现的内部错误
        *
        *
        * */

        // Topic-04: 总之，一个方法必须声明所有可能抛出的受查异常==》而非受查异常要么不可控制（Error）要么就应该避免发生（RuntimeException）==》如果方法没有声明所有可能发生的受查异常，编译器就会发出一个错误消息。

        // Topic-05: 如果在子类中覆盖了超类的一个方法，子类方法中声明的受查异常不能比超类方法中生命的异常更通用 ==》而且如果超类方法没有抛出任何受查一行，子类也不能抛出任何受查异常

        // Topic-06: 如果类中的一个方法声明将会抛出一个异常，而这个异常是某个特定类的实例时，则这个方法就有可能抛出一个这个类的异常，或者这个类的任意一个子类的异常
        System.out.println("总之，一个方法必须声明所有可能抛出的受查异常==》而非受查异常要么不可控制（Error）要么就应该避免发生（RuntimeException）==》如果方法没有声明所有可能发生的受查异常，编译器就会发出一个错误消息。");

    }

    // FocusSubPoint-03：How to throw an exception in java？
    public static void ThrowAnExceptionInJava() throws EOFException{
        // Topic-01: 对于一个已经存在的异常类将它抛出来非常容易
        /*
        *  1、找到一个合适的异常类。
        *  2、创建这个类的一个对象。
        *  3、将对象抛出。
        * */

        // Topic-02: 一旦方法抛出了异常，这个方法就不可能返回到调用者，也就是说，不必为返回的默认值或者错误代码担忧

        // story: 一个文件：Content-length: 1024 结果读到666个字符就结束了==》不正常==》javaapi中找到EOFException异常"在输入过程中，遇到了一个未预期的EOF后的信号"

        EOFException ragnor_file_lenght = new EOFException();
        System.out.println(ragnor_file_lenght.toString());
        // readfile-find Content-length: 1024 - real 666
        throw ragnor_file_lenght;


    }

    // FocusSubPoint-04：How do I create an DIY-exception class in java?
    public static void CreateDiyExceptionClass() throws RagFirstExceptionJava{
        // Topic-01: 在程序中，可能会遇到任何标转异常类都没有能够充分地描述清楚的问题==》创建自己的异常类
        throw new RagFirstExceptionJava("this is my first java exception!");
    }

    static class RagFirstExceptionJava extends Exception{
        public RagFirstExceptionJava(){};
        public RagFirstExceptionJava(String msg){
            super(msg);
        }
    }
}
