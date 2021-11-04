package com.ragnor.corejava.chapter04_ClassAndObject;

public class FocusPoint_08_JavaClassPath {
    public static void main(String[] args) {
        // Topic-01: 什么是类路径？ ==》就是你看到的 哪些c的路径
        // Topic-02: 类的路径必须与包名匹配
        // Topic-03: 类文件也可以存储在JAR（java归档）文件中

        // Topic-04: 为了使类能够被多个程序共享，需要做到以下几点：
        /*
         * 1、把类放到一个基目录中，例如/Home/user/xiaomengli/code ==》需要注意的是这个目录是【包树状结构的基目录】
         *    如果想把 com.ragnor.corejava.Employee类添加到其中 =》这个Employee.class类文件就必须位于子目录/Home/user/xiaomengli/code/com/ragnor/corejava中
         * 2、将JAR文件放在一个目录中，例如/Home/user/archivers/jars
         * 3、设置类的路径（class path）==>类路径是所有包含类文件的路径的集合
         *    ：unix环境下=》 /home/user/xiaomengli/code:.:/home/user/archivers/jars/Employee.jar (类路径中的不同项目之间采用冒号分割  .表示当前目录)
         *    ：windows环境下 =》 c:\classdir;.;c"\archives\Employee.jar (类路径中的不同项目之间采用分号分割  .表示当前目录)
         *    :
         *    : 点表示当前路径下，分号表示各路径的一个分隔符=>不一定非要.;开头，这个出现的顺序表示搜索的先后
         *    : javac 编译器总是在当前的目录中查找文件 ==》但是java虚拟机仅在类路径中有"."目录的时候才查看当前目录
         *       ==》如果没有设置类路径，也并不会产生什么问题，默认的类路径包含"."目录，然而如果设置了类路径缺忘记了包含"."目录=》则程序仍可以通过编译但是不能运行
         *       ==》就是说classpath中如果设置了但是没有包含"." 目录那么javac可以编译但是java虚拟机不知道去哪里执行
         *
         *    : 假定虚拟机要搜寻 com.ragnor.corejava.Employee类你看虚拟机的搜索策略：
         *       step-01：首先查看存储在 jre/lib 和 jre/lib/ext目录下的归档文件中所存放的系统类文件
         *       step-02: （用unix那个）然后去 /home/user/xiaomengli/code/com/ragnor/corejava/Employee.class
         *                          没有继续 ./com/ragnor/corejava/Employee.class
         *                          没有继续 /home/user/archivers/jars/Employee.jar 里面 com/ragnor/corejava/Employee.class
         *       step-03: 类必须唯一，如果找到了一个以上的类==》就会产生编译错误
         *       step-04: 编译器查看source files 是否比 class file新 ==》触发自动重编译
         *       。。。
         *
         *
         * */
        SetClassPath();
    }


    // 设置类路径
    public static void SetClassPath(){
        System.out.println(" two methods to set class path ");
        /*  Topic-01：设置类的路径推荐两种方式：
                  method-01： java -classpath/-cp /home/user/xiaomengli/code:.:/home/user/archivers/jars/Employee.jar myjavaproject

                  method-02:  export CLASSPATH=/home/user/xiaomengli/code:.:/home/user/archivers/jars/Employee.jar (unix)
                              set CLASSPATH=c:\home\ser\xiaomengli\code;.;\home\ser\archivers\jars\Employee.jar
                              没想到增地方的"gang u" ==》触发了Error java: 非法的 Unicode转义
        */

    }

}
