package com.ragnor.corejava.chapter06_InterfaceAndlamdbaAndInnerClass;

import com.ragnor.corejava.chapter05_Inherit.FocusPoint_03_JavaGenericArray;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class FocusPoint_05_JavaProxy {
    public static void main(String[] args) {
        // 代理：利用代理可以在运行时创建一个实现了一组给定接口的新类，这种功能只有在编译时无法确定需要实现哪个接口时才有必要使用
        //      ==>对于应用程序设计人员来说，遇到这种情况的机会很少
        //      ==>对于系统程序设计人员来说，代理带来的灵活性十分重要

        JavaWhenToUseProxy();
        CreateProxyObjectInJava();
        CharacteristicsOfProxyInJava();

    }

    // FocusSubPoint-01：when to use proxy in java？
    public static void JavaWhenToUseProxy(){
        // Topic-01: 如果有一个表示接口的 Class对象 ==》有可能只包含一个接口, 它的确切类型在编译时无法知道。
        //           ==> 代理机制可以在运行时创建全新的类==》这样的代理类能够实现指定的接口，而且具有以下的方法
        //           1、指定接口所需要的全部方法
        //           2、Object类中的全部方法，例如，toString、equals等

        System.out.println("我建议往下看");
    }

    // FocusSubPoint-02: How do I create a proxy object in java?
    public static void CreateProxyObjectInJava(){
        // 在我看来，代理跟我们测试一样，就是中间，在C 与 S之间的代理，实现篡改的手段
        // Topic-01: 如果想要创建一个代理对象，需要使用Proxy类的newProxyInstance方法。这个方法有三个参数：
        //          1、一个类加载器（class loader）。作为java安全模型的一部分，对于系统类和从因特网上下载下来的类，可以使用不同的类加载器（卷2-9才有类加载器），目前用null表示使用默认的类加载器
        //          2、一个Class对象数组，每个元素都是需要实现的接口。
        //          3、一个调用处理器。

        // Topic-02: 使用代理机制可以解决什么问题？
        /*
        *   1、路由对远程服务器的方法调用
        *   2、在程序运行期间，将用户接口事件与动作关联起来
        *   3、为调试，跟踪方法调用
        * */

        // Topic-03: 下面的程序：使用代理和调用处理器跟踪方法调用，并且定义一个TraceHandle包装器类存储包装的对象，其中的invoke方法打印出被调用方法的名字和参数，随后用包装好的对象作为隐式参数调用这个方法
        FocusPoint_03_JavaGenericArray.EmployeeDB staff = new FocusPoint_03_JavaGenericArray.EmployeeDB("ragnor", 18, 1900, 1994, 12, 15);
        InvocationHandler handler = new TraceHandle(staff);
        // 为一个或者多个接口构造代理
        Class<?>[] interfaces = new Class[]{Comparable.class};
        Object proxy = Proxy.newProxyInstance(null, interfaces, handler);
        // 无论何时用proxy调用某个方法：这个方法的的所有名字都会被打印出来
        System.out.println(proxy.hashCode());
        System.out.println(proxy.toString());


    }

    static class TraceHandle implements InvocationHandler{
        private final Object target;

        public TraceHandle(Object t){
            this.target = t;
        }

        @Override
        public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
            // 无论何时用proxy调用某个方法：这个方法的的所有名字都会被打印出来
            System.out.println(target);
            System.out.println("." + m.getName());

            return m.invoke(target, args);
        }
    }

    // FocusSubPoint-03: What are the characteristics of proxy classes in Java?
    public static void CharacteristicsOfProxyInJava(){
        // Topic-01: 代理类是在程序运行过程中创建的==》然而一旦被创建就变成了常规类==》与虚拟机中的任何其他类没有什么区别

        // Topic-02； 所有的代理类都扩展与Proxy类

        // Topic-03: 所有的代理类都覆盖了Object类中的方法 toString、equals和hashCode

        // Topic-04: 没有定义代理类的名字，Sun虚拟机中的Proxy类将生成一个以字符串$Proxy开头的类名

        // Topic-05: 对于特定的类加载器和预设的一组接口来说，只能由一个代理类

        // Topic-06: 代理类一定时public和final

        // Topic-07: 可以调用Proxy类中的isProxyClass方法检测一个特定的Class对象代表一个代理类

        System.out.println("java's proxy is a long way to understand");  // todo: java's proxy is a long way to understand"
    }

}



