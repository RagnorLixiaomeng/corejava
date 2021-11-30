package com.ragnor.corejava.chapter07_ExceptionsAndAssertionsAndlogs;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.*;

public class FocusPoint_05_JavaLogging {
    public static void main(String[] args) {
        // 日志：是一种灵活的、格式化的、可自定义的、集收集-处理-format-转存为一体的程序监控方案
//        Logger.getGlobal().setLevel(Level.OFF);  // main方法中控制关闭
//        JavaBasicLogging();
//        try {
//            AdvancedLogging();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ModifyLoggerManagerConfigurationFile();
//        JavaLogLocalization();
//        JavaHandler();
        JavaFilter();
        JavaFormater();

//        final Logger logger = Logger.getLogger("com.ragnor.corajava");
//
//        if(System.getProperty("java.util.logging.config.class") == null
//                && System.getProperty("java.util.logging.config.file") == null){
//            try{
//                Logger.getLogger("").setLevel(Level.ALL);
//                final int LOG_ROTATION_COUNT = 10;
//                Handler handler = new FileHandler("%h/ragnor.log", 0, LOG_ROTATION_COUNT);
//                Logger.getLogger("").addHandler(handler);
//            }catch (IOException E){
//                logger.log(Level.SEVERE, "Can't create log file handler", E);
//            }
//        }


    }

    // FocusSubPoint-01：How to implement the basic logging method in java？
    public static void JavaBasicLogging(){
        // Topic-01: 基本日志：如果需求比较简单的话可以使用全局日志记录器（global logger）并调用其中的info方法
        Logger.getGlobal().info("File -> Open menu item selected");
    }

    // FocusSubPoint-02: How do you implement the advanced logging method in java?
    public static void AdvancedLogging() throws IOException {
        // Topic-01: 在企业级的日志中（industrial-strength）: 不要将所有的日志都记录到一个全局日志记录器中，而是自定义日志记录器
        // Topic-02: 未被任何变量引用的日志记录器可能会被垃圾回收 ==》为了防止这种情况的发生，要像上面的例子中一样，用一个静态变量存储日志记录器的一个引用。
        RagnorLogger.info("self-made logger info level");
        // Topic-03: 可以设置日志记录器的级别：SEVERE WARNING INFO CONFIG FINE FINER FINEST (默认只记录前3个级别)
        RagnorLogger.setLevel(Level.WARNING);

        RagnorLogger.info("self-made logger info level");
        RagnorLogger.warning("self-made logger warning level");

        // Topic-04: 我说上面的setLevel:Fine怎么不生效 ==》因为如果将记录级别设计为INFO或者更低 ==》则需要修改日志处理器的配置 ==》 默认的日志处理器不会处理抵御INFO级别的信息

        // Topic-05: 默认的日志记录将显示包含日志调用的类名和方法名，如同堆栈所显示的那样 ==》 但是如果虚拟机对执行过程进行了优化，就得不到准确的调用信息 ==》此时调用logp方法获得调用类和方法的确切位置
        RagnorLogger.logp(Level.INFO, "FocusPoint_05_JavaLogging", "AdvancedLogging", "trace message！");

        // Topic-06: 有一些最终执行流的方法可供调用
        RagnorLogger.entering("FocusPoint_05_JavaLogging", "AdvancedLogging");
        RagnorLogger.exiting("FocusPoint_05_JavaLogging", "AdvancedLogging");

        // Topic-07: 记录日志的常见用途是记录那些不可预料的异常 ==》可以使用下面的2个方法提供日志记录中包含的异常描述: throw 与 log
        IOException exception = new IOException("my io exception");
        RagnorLogger.throwing("FocusPoint_05_JavaLogging", "AdvancedLogging", exception);
//        throw exception;  // 这个打开会导致下面的代码是unreachable

        // log - api
        IOException excp_2 = new IOException("my io exception2");
        RagnorLogger.log(Level.INFO, "Log api throw exception", excp_2);


    }

    public static final Logger RagnorLogger = Logger.getLogger("com.ragnor.corejava");

    // FocusSubPoint-03: How do I modify the log manager configuration in java?
    // /Library/Java/JavaVirtualMachines/openjdk-11.jdk/Contents/Home/conf/logging.properties
    public static void ModifyLoggerManagerConfigurationFile(){
        // Topic-01: java中可以通过编辑配置文件来修改系统日志的各种属性：默认情况下配置文件存在于：jre/lib/logging.properties,我自己是在上面那个路径下找到的
        //           注意可以在调用时System.setProperty("java.util.logging.config.file", log_path); 来重定义日志配置文件
        //           但是这种方式只能自行手工编译运行哈
        // Topic-02: 配置文件中的：.level= FINE   java.util.logging.ConsoleHandler.level = FINE 要改成你想要的level
        Logger.getGlobal().log(Level.FINE, "hhahhah");
        Logger.getGlobal().fine("HAHHAH");

        // Topic-03: 在IDE中run需要修改run参数中添加：-Djava.util.logging.config.file=/Users/xiaomengli/Desktop/java全栈/ragnor_java/corejava/src/com/ragnor/corejava/chapter07_ExceptionsAndAssertionsAndlogs/logging.properties
        //           在日志管理器配置的属性设置不是系统属性，因此，用-Dcom.ragnor.corejava.chapter07_ExceptionsAndAssertionsAndlogs.FocusPoint_05_JavaLogging=FINE启动应用程序不会对日志记录器产生任何影响


    }

    // FocusSubPoint-04: What is localization in the java log?
    public static void JavaLogLocalization(){
        // Topic-01: 所谓本地化指的是日志应用程序的国际化
        // Topic-02: 日志消息：
        //              中文 com/mycompany/logmessages_zh.properties
        //              英文 com/mycompany/logmessages_en.properties
        //              德文 com/mycompany/logmessages_de.properties
        System.out.println("本地化的应用程序包含资源包中本地特定信息，资源包有各个地区的映射集合组成");

    }

    // FocusSubPoint-05: What is the handler in the java log? How do you do that?
    public static void JavaHandler(){
        // Topic-01: 日志记录器 ==> 日志处理器 ==》日志过滤器 ==》日志格式化器
        Logger mylogger_01 = Logger.getLogger("com.ragnor.coreajava");  // 定义一个自己的日志记录器
        mylogger_01.setLevel(Level.INFO);
        mylogger_01.setUseParentHandlers(false);
        Handler myhandler_01 = new ConsoleHandler();  // 定义一个自己的日志处理器
        try {
            Handler myfilehandler_01 = new FileHandler();  // 定义一个自己的文件处理器
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Topic-02: 日志处理器配置参数：
        myhandler_01.setLevel(Level.INFO);

        mylogger_01.addHandler(myhandler_01);  // 将日志处理器接到日志记录器中
        mylogger_01.info("it's my log info");
    }

    // FocusSubPoint-06: What are the filters in the java log? How do you do that?
    public static void JavaFilter(){
        // Topic-01: 默认情况下过滤器根据日志记录的级别进行过滤：每个日志记录器和处理器都可以有一个可选的过滤器来完成附加的过滤==》可以通过实现Filter接口并完后曾isLoggable方法来自定义过滤器

        // Topic-02: 要想将一个过滤器直接安装到一个日志记录器或者处理器中==》只需要调用setFilter方法就可以了==》但是注意，同一时刻最多只能由一个过滤器

        // 假设我们设置了关键词过滤
        Logger myfilterlogger = Logger.getLogger("com.filter,log");
        ConsoleHandler ch = new ConsoleHandler();
        ch.setFilter(new DiyFilter("qingning"));

        myfilterlogger.setUseParentHandlers(false);
        myfilterlogger.addHandler(ch);

        myfilterlogger.info("qingning");  // filter in
        myfilterlogger.info("qingning2"); // filter in
        myfilterlogger.info("liqing");  // filter out

    }

    /**敏感词汇过滤*/
    public static class DiyFilter implements Filter{

        private String keyword;

        public DiyFilter(String keyword){
            this.keyword = keyword;
        }

        @Override
        public boolean isLoggable(LogRecord record) {
            String msg = record.getMessage();
            if (this.keyword == null){
                return true;
            }

            return msg.contains(this.keyword);

        }
    }

    // FocusSubPoint-07: What is the formatter in java? How do you do that?
    public static void JavaFormater(){
        // Topic-01: 扩展Formatter类并覆盖 String format(LogRecord record)方法
        // Topic-02: 调用setFormatter方法将格式化器安装到处理器中
        System.out.println("java foramter");
    }

    // FocusSubPoint-08: Logging instructions in java?
    public static void JavaLoggingInstructions(){
        // Topic-01: 日志记录可选项太多了，乱花渐欲迷人眼==》勿忘基础
        // 1) 为一个简单的应用程序，选择一个日志记录器，并把日志记录器命名为与主应用程序包一样的名字：com.mycompany.myprog==>为了方便，可以利用一些日志操作将下面的静态域添加到类中：
        //  private static final Logger logger = Logger.getLogger("com.mycompany.myprog");

        // 2) 默认的日志配置将级别等于或高于INFO级别的所有消息记录到控制台==》用户可以覆盖默认的配置文件==》建议根据实际使用需要自定义配置

        /* 为了将所有的消息记录到应用程序特定的文件中可以将下列代码放置到程序的main方法中
        *
        * if(System.getProperty("java.util.logging.config.class") == null
                && System.getProperty("java.util.logging.config.file") == null){
            try{
                Logger.getLogger("").setLevel(Level.ALL);
                final int LOG_ROTATION_COUNT = 10;
                Handler handler = new FileHandler("%h/ragnor.log", 0, LOG_ROTATION_COUNT);
                Logger.getLogger("").addHandler(handler);
            }catch (IOException E){
                logger.log(Level.SEVERE, "Can't create log file handler", E);
            }
        }
        * */

        // 3) 最好将对程序用户又意义的消息设置为这几个级别，将程序员想要的日志记录，设定为FINE是一个很好的选择==》不影响程序纯调试

    }
}
