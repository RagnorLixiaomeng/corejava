package com.ragnor.corejava.chapter07_ExceptionsAndAssertionsAndlogs;

import jdk.jfr.StackTrace;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class FocusPoint_02_JavaCaptureException {
    public static void main(String[] args) {

        CatchingExceptionInJava("/Users/xiaomengli/Desktop/java全栈/ragnor_java/corejava/Files/demo.txt");
        CatchingMultipleExceptionInJava("/Users/xiaomengli/Desktop/java全栈/ragnor_java/corejava/Files/demo.txt");
        ThrowingExceptionAgainAndExceptionChain();
        JavaExceptionFinally("./noexist.txt");
        JavaWithResourceTry();
        JavaAnalyzeStackTraceProcess();
    }

    // FocusSubPoint-01：How to implement catching exceptions in java？
    public static void CatchingExceptionInJava(String filename){
        // Topic-01: 语法就是
        //      try
        //      {
        //          code
        //          more code
        //      } catch (ExceptionType e)
        //      {
        //          handler for this type
        //      }
        //
        // Topic-02: 如果异常没有在任何地方捕获，那么程序就会终止，并在控制台上打印出异常信息：异常的类型和堆栈的内容

        try
        {
            InputStream in = new FileInputStream(filename);
            System.out.println(in.read());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {  // 因为FileNotFoundException extends IOExceptio所以上面的没必要
            e.printStackTrace();
        }


    }

    // FocusSubPoint-02：How do I catch multiple exceptions in java?
    public static void CatchingMultipleExceptionInJava(String filename){
        // Topic-01: 格式如下：

        try {
            InputStream in = new FileInputStream(filename);
            System.out.println(in.read());
            Scanner ragnor_new_scanner = new Scanner(System.in);
            int result = 100 / ragnor_new_scanner.nextInt();
            // 解封下面的才能做处理
//            result.getClass();
//        } catch (IOException | NoSuchMethodException e) {  // 如果这两个异常捕获到做的处理一样可以合并
//            e.printStackTrace();
        } catch (Exception e){
            System.out.println("nothing");
        }
    }

    // FocusSubPoint-03：How do I implement throwing an exception again in java? What is an exception chain?
    public static void ThrowingExceptionAgainAndExceptionChain(){
        // Topic-01: 有时候执行的一段代码（执行servlet）可能并不想知道发生错误的细节原因==》而是想明确的知道这个servlet是不是有问题

        /*try
        {
            access the database
        } catch (SQLException E)
        {
            Throwable myse = new ServleException("database error");
            myse.initCause(E);
            throw myse;
        }

        // 当捕获到异常的时候可以使用下面的方法重得到原始异常
        Throwable e = myse.getCause(); */

        // 这是强烈推荐的包装技术：可以让用户抛出子系统的高级异常
        // ==》而且也不会丢失原始异常的细节
        // ==》 如果在一个方法中发生了一个受查异常，而不允许抛出它，那么包装技术就十分有用。我们可以捕获这个受查异常
        // ==》并将它包装成一个运行时异常。

        System.out.println(" catch e1 -> Throwable e2 -> e2.initCause(e1) -> throw(e2)::e2 -> e2.getCause()::e1 ");

    }

    // FocusSubPoint-04: Java Exception Finally grammar?
    public static void JavaExceptionFinally(String filename){
        // Topic-01: 不管是否有异常被捕获，finally子句中的代码都会被执行 ==>应对一些情况下，不管try运行什么，catch捕获到了什么我不管，最后我必须执行
        // Topic-02: try语句可以只有finally语句没有catch
        try{
            InputStream is_ = new FileInputStream(filename);
        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            System.out.println("i must run whatever try catch situation");
        }

        // Topic-03: java是强烈建议try-catch 与 try-finally解耦合的什么意思呢
        //          ==》例如try-catch的try中包含一个try-finally
        //          ==》内层try职责就是确保关闭输入流==》外层try语句块确保报告出现的错误

    }

    // FocusSubPoint-05：How do I implement a resource-intensive try statement in java?
    public static void JavaWithResourceTry(){
        // Topic-01: java中带资源的try语句在我看来就是上下文管理器
        // Topic-02: 假设资源类是一个是实现了AutoCloseable接口的类==》有一个void close() throws Exception方法
        // Topic-03: 比如下面不用在finally 编写资源关闭操作了==>因为Scanner实现了Closeable接口，会自动调用in.close()方法

        try{
            String file_path_ = "/Users/xiaomengli/Desktop/java全栈/ragnor_java/corejava/Files/demo.txt";
            Scanner in  = new Scanner(new FileInputStream(file_path_), StandardCharsets.UTF_8);
            while (in.hasNext()){
                System.out.println(in.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("假设资源类是一个是实现了AutoCloseable接口的类==》有一个void close() throws Exception方法==》自动关闭句柄");
    }

    // FocusSubPoint-06: How do I analyze stack trace process in java?
    public static void JavaAnalyzeStackTraceProcess(){
        // Topic-01: 堆栈轨迹（stack trace）是一个方法调用过程的列表==》它包含了程序执行过程中方法调用的特定位置
        // Topic-02: 可以调用Throwable类的printStackTrace方法访问堆栈轨迹的文本描述信息
        Throwable t1 = new Throwable();
        StringWriter out = new StringWriter();
        t1.printStackTrace(new PrintWriter(out));
        String description = out.toString();
        System.out.println(description);

        // Topic-03: 比上面更灵活的方式是使用getStackTrace方法，它会得到StackTraceElement对象的一个数组，可以在你的程序中分析这个对象数组
        //           你应该可以看的出来，StackTraceElement类含有能够获得文件名和当前执行的代码行号的方法 + 获取类名 + 获取方法名
        Throwable t2 = new Throwable();
        StackTraceElement[] frames = t2.getStackTrace();
        for (StackTraceElement frame: frames){
            System.out.println(frame);

        }

        // Topic-04: 静态的Thread.getALLStackTrace方法，它可以产生所有线程的堆栈轨迹
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        for (Thread t: map.keySet()){
            StackTraceElement[] frames_ = map.get(t);
            // analysis frames
            System.out.println(Arrays.toString(frames_));
            System.out.println(frames_[1].getFileName()); // 返回堆栈元素对象的源文件 | null
            System.out.println(frames_[1].getLineNumber()); // 返回元素运行时对应的源文件行数  | -1
            System.out.println(frames_[1].getClassName());  // 返回这个元素运行时对应的类的完全限定名
            System.out.println(frames_[1].getMethodName());   // 返回这个元素运行时对应的方法名
            System.out.println(frames_[1].isNativeMethod());  // 如果这个元素运行时是在一个本地方法中，则返回true
            System.out.println(frames_[1].toString());  // 如果存在的话，返回一个包含类名、方法名、文件名、和行数的格式化字符串
        }
    }
}
