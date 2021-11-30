package com.ragnor.corejava.chapter07_ExceptionsAndAssertionsAndlogs;

import java.io.*;
import java.util.Random;
import java.util.logging.Logger;

public class FocusPoint_06_JavaDebugSkill {
    public static void main(String[] args) throws IOException {
        // Topic-01: 可以利用下面的方法打印或者记录任意变量的值：==》java中绝大多数的类库都覆盖了toString方法，以便能够提供有用的类信息==》如果你自己写类也应该这样
        // ==》因为x是一个数组则会被转换为等价的字符串
        // ==》如果x是一个对象，那么java就会调用这个对象的toString方法
        // ==》要想获得隐式参数对象的状态，就可以打印this对象的状态
        int x = 0;
        System.out.println("x=" + x);
        // 或者
        Logger.getGlobal().info("x=" + x);
        // 隐式
        // Logger.getGlobal().info("this=" + this);

        // Topic-02: 据说哈哈一个不太为人所知但是却非常有效的方法是在每一个类中放置一个单独的main方法==》这样就可以对每一个类进行单元测试。

        // Topic-03: 如果你接收了2的意见，那么推荐java的JUnit单元测试框架

        // Topic-04: 日志代理（logging proxy）是一个子类的对象，它可以截获方法调用，并进行日志记录，然后调用超类中的方法==》例如如果在调用Random类的nextDouble方法时出现了问题，就可以按照下面的方式，以匿名子类实例的形式创建一个代理对象：
        Random ragnor_generator = new Random(){
            public double nextDouble(){
                double result = super.nextDouble();
                Logger.getGlobal().info("nextDouble: " + result);  // 我理解的就是重写了Random的nexDouble方法加了个打印的功能,当调用nexDouble方法时，就会产生一个日志消息==》要想知道谁调用了这个方法，就要生成一个堆栈轨迹
                return result;
            }
        };
        System.out.println(ragnor_generator.nextInt(1));
        System.out.println(ragnor_generator.nextDouble());  // 子类代理看懂这两行就明白了

        // Topic-05: 利用Throwable类提供的printStackTrace方法，可以从任何一个异常对象中获得堆栈情况
        // ==》捕获到别忘了抛出来，以便于能够重新找到相应的处理器
        // ==》下面的代码就是打印异常和堆栈轨迹
//        try {
//            throw new IOException("io exception");
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }

        // 不一定要通过捕获异常来生成堆栈轨迹，只要在代码的任何位置插入下面这条语句就可以获得堆栈轨迹
          Thread.dumpStack();


        // Topic-06: 一般来说，堆栈轨迹显示在System.err上
        // ==》也可以利用printStackTrace(PrintWriter s)方法将它发送到一个文件中
        // ==》还可以捕获到字符串中如下
        StringWriter out = new StringWriter();
        new Throwable().printStackTrace(new PrintWriter(out));
        String description = out.toString();

        // Topic-07: 通常，将一个程序中的错误信息保存在一个文件中是非常有用的 ==》然而错误信息被发送到System.err中，而不是System.out中
        //           所以不能这样获取: java myprogram > errors.txt
        //           而是         :  java myprogram 2 > errors.txt
        //           如果想在同一个文件中同时捕获System.err和System.out需要： java myprogram 1 > errors.txt 2> &1
        //           以上均工作在bash和windows shell中

        // Topic-08: 让非捕获异常的堆栈轨迹出现在System.err中并不是一个很理想的方法
        // ==》如果在客户端偶然看到这些消息，会迷惑，并且无法实现诊断目的
        // ==》比较好的方法是将这些内容记录到一个文件中：调用静态的Thread.setDefaultUncaughtExceptionHandler方法改善非捕获异常的处理器
        Thread.setDefaultUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler(){
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        // save infomation in log file
                        BufferedWriter log_out = null;
                        try {
                            // java 写文件
                            log_out = new BufferedWriter(new FileWriter("logout.txt"));
                            log_out.write(e.toString());
                            log_out.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                    }
                }
        );

        // Topic-09: 如果你想观察类的加载过程，可以用 -verbose标志启动java虚拟机 ==》这样就可以看到下面的输出结果
        /* run ==> VM -verbose即可
        *   [0.009s][info][class,load] opened: /usr/local/Cellar/openjdk@11/11.0.12/libexec/openjdk.jdk/Contents/Home/lib/modules
            [0.014s][info][class,load] java.lang.Object source: jrt:/java.base
            [0.014s][info][class,load] java.io.Serializable source: jrt:/java.base
            [0.014s][info][class,load] java.lang.Comparable source: jrt:/java.base
            [0.014s][info][class,load] java.lang.CharSequence source: jrt:/java.base
            [0.014s][info][class,load] java.lang.String source: jrt:/java.base
            [0.015s][info][class,load] java.lang.reflect.AnnotatedElement source: jrt:/java.base
            [0.015s][info][class,load] java.lang.reflect.GenericDeclaration source: jrt:/java.base
            [0.015s][info][class,load] java.lang.reflect.Type source: jrt:/java.base
            [0.015s][info][class,load] java.lang.Class source: jrt:/java.base
            [0.015s][info][class,load] java.lang.Cloneable source: jrt:/java.base
            [0.015s][info][class,load] java.lang.ClassLoader source: jrt:/java.base
            [0.015s][info][class,load] java.lang.System source: jrt:/java.base
            [0.015s][info][class,load] java.lang.Throwable source: jrt:/java.base
        *
        *
        * */

        // Topic-10： -Xlint选项告诉编译器对一些普遍容易出现的代码问题进行检查，比如：javac -Xlint:fallthrough
        /*
        *   -Xlint 或者 -Xlint:all        执行所有的检查
        *   -Xlint:deprecation           与-deprecation一样，检查废弃的方法
        *   -Xlint:fallthrough           检查switch语句中是否缺少break语句
        *   -Xlint:finally               警告finally子句不能正常的执行
        *   -Xlint:none                  不执行任何检查
        *   -Xlint:path                  检查类路径和源代码路径上所有目录是否存在
        *   -Xlint:serial                警告没有serialVersionUID的串行化类
        *   -Xlint:unchecked             对通用类型与原始类型之间的危险转换给予警告
        *
        * */

        // Topic-11: java虚拟机增加了对java应用程序进行监控（monitoring）和管理（management）的支持
        // ==》它允许利用虚拟机中的代理装置跟踪内存消耗、线程使用、类加载等情况
        // ==》这个功能对于像应用程序服务器这样大型的、长时间运行的java程序来说特别重要

        // jconsole processID 或者直接输入jconsole再去选择

        // Topic-12: 可以使用jmap实用工具获取一个堆的转储==》其中显示了堆中的每个对象==》使用命令如下：
        //  jmap -dump:format=b,file=dumpFileName processID
        //  jhat dumpFileName ==》这玩意在java9的时候被废弃了==》down visualvm包放在推荐VisualVM Launcher，我IDE插件直接装右上角run with VisualVm

        // Topic-13: 如果使用 -Xprof标志运行Java虚拟机
        // ==》就会运行一个基本的剖析器来跟踪哪些代码中经常被调用的方法
        // ==》剖析信息将发送给System.out
        // ==》输出信息还会显示哪些方法是由即时编译器编译的


    }

}
