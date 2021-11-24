package com.ragnor.corejava.chapter06_InterfaceAndlamdbaAndInnerClass;

import com.ragnor.corejava.chapter05_Inherit.FocusPoint_03_JavaGenericArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.function.IntConsumer;
import java.util.stream.Stream;

public class FocusPoint_03_JavaLambda {
    public static void main(String[] args) {
        WhyLambda();
        JavaLambdaSyntax();
        FunctionalInterface();
        ImplementMethod();
        ReferenceConstructor();
        JavaLambdaVariableScope();
        UseLambdaInJava();
        JavaComparatorUage();
    }

    // FocusSubPoint-01: Why introduce lambda expressions?
    public static void WhyLambda(){
        // Topic-01: 考虑一下：回调函数、比较器==》共同点就是将一个代码块传递到某个对象（一个定时器、一个sort方法），而这个代码块会在将来的某个时间调用
        // Topic-02: 发现没，在java中传递一个代码段并不容易，不能直接传递代码段。==》因为java是一种面向对象的语言，所以必须构造一个对象，这个对象的类需要有一个方法能包含所需的代码
        // Topic-03: 在其他语言中可以直接处理代码块==》java设计者觉得只要一个特性能够让代码稍微简介一些，就把这个特性加到语言中==》这个语言会变得一团糟的
        System.out.println("actionPerformed方法包含希望以后执行的代码");

        System.out.println("compare 方法不是立即调用，在数组完成排序之前，sort方法会一致调用compare方法");

        System.out.println("回调函数、比较器==》共同点就是将一个代码块传递到某个对象（一个定时器、一个sort方法），而这个代码块会在将来的某个时间调用");
    }

    // FocusSubPoint-02：What is the syntax of lambda expressions in java?
    public static void JavaLambdaSyntax(){
        // Topic-01: 从一个例子进入：还记得那个比较吗？ first.length() - second.length()
        //          ==> first 跟 second是什么？java是一门强类型语言，所以我们还需要制定它们的类型
        //          ==> (String first, String second) -> first.length() - second.length()
        //          上面就是你看到的第一个lambda表达式：lambda表达式就是一个代码块，以及必须传入代码的变量规范

        // Topic-02: 如果代码要完成的计算无法放在一个表达式中，就可以像写方法一样，把这些代码放在 {} 中，并包含显式的return语句
        //         指的是first与second的类型是String
        Comparator<String> result = (String first, String second) -> {
            if (first.length() < second.length()) return -1;
            else if (first.length() > second.length()) return 1;
            else return 0;
        };
        System.out.println(result.compare("ragnor", "ragnor"));

        // Topic-03: 即时lambda表达式没有参数仍然要提供空括号==》就像无参数方法一样
        Runnable rdemo = () -> {
            for (int cy_ = 1; cy_ <= 10; cy_++) System.out.println("haha");
        };

        // Topic-04: 如果方法只有一个参数，而且这个参数的类型可以推导的出==》那么甚至可以省略小括号：
        ActionListener listener = event -> System.out.println("the time is "+ new Date());

        // Topic-05: 无需指定lambda表达式的返回类型，lambda表达式的返回类型总是会由上下文推导得出
        // Comparator<String> stringComparator = (String first, String second) -> first.length() - second.length();
        Comparator<String> stringComparator = (first, second) -> first.length() - second.length();

        // Topic-06: 如果一个lambda表达式只在某些分支返回一些值而在另外一些分支不返回值，这是不合法的
//        Comparator<Integer> intComparator = (int x) -> {if (x < 10) return 1;};

    }

    // FocusSubPoint-03：What is the concept of a functional interface in java?
    public static void FunctionalInterface(){
        // Topic-01: 对于只有一个抽象方法的接口，需要这种接口的对象时，就可以提供一个lambda表达式==》这种接口称为函数式接口（functional interface）

        // Topic-02: 理解不了对吧，比如Arrays.sort方法==》它的第二个参数需要一个Comparator实例==》Comparator就是只有一个方法的接口==》所以可以提供一个lambda表达式：
        String[] words = new String[] {"ahha", "xixixixi", "rarar"};
        // todo: Arrays.sort()中 传names 取代words为毛不行啊
        ArrayList<String> names = new ArrayList<>(3);
        names.add("ahha");
        names.add("xixixixi");
        names.add("rarar");

        // 在底层，Arrays.sort方法会接收实现了Comparator<String>的某个类的对象 ==》在这个对象上调用compare方法会执行这个lambda表达式
        // 这些对象和类的管理完全取决于具体实现，与使用传统的内联类相比，这样可能要高效的多
        // ==》 最好把lambda表达式看做是一个函数而不是一个对象
        // ==》 lambda表达式可以传递到函数式接口

        Arrays.sort(words, (first, second) -> first.length() - second.length());
        System.out.println(Arrays.toString(words));  // [ahha, rarar, xixixixi] 已排序

        // Topic-03: lambda可以转换为接口 ==>下面这个 VS 实现了 ActionListenner接口的类相比可读性会很高
        Timer t = new Timer(1000, event -> {
            System.out.println("At the tone, the time is " + new Date());
        //    Toolkit.getDefaultToolkit().beep();
        });
        t.start();
        JOptionPane.showMessageDialog(null, "Quit Program?");
        System.exit(0);
    }

    // FocusSubPoint-04：How do you implement(引用) a reference to a method in java?
    public static void ImplementMethod(){
        // Topic-01: java的玩法真是多哇：java中lambda中的方法引用：就是说不用自己写lambda里面的逻辑了，如果java类库已经提供了现成的方法
        // 用  :: 操作符分割方法名与对象或者类名
        //          method1: object::instanceMethod  ==> 方法引用等价于提供方法参数的lambda表达式 : System.out::println 等价于 x -> System.out::println(x)
        //          method2: Class::staticMethod
        //          method3: Class::instanceMethod  ==> 第一个参数会称为方法的目标 ==> String::compareToIgnoreCase 等价于 x.compareToIgnoreCase(y)

        Timer t1 = new Timer(10000, event -> System.out.println(event)); // 为了演示，现在IDE已经提示推荐下面的格式了
        Timer t1_1 = new Timer(10000, System.out::println);

        String[] scores = new String[]{"AR", "ai"};
        Arrays.sort(scores, String::compareToIgnoreCase);  // 对字符串排序但是不考虑字母的大小写
        System.out.println(Arrays.toString(scores));

        // Topic-02: 可以在方法引用中使用this、super 参数
        // TimerGreeter.greet方法开始执行的时候，会构造一个Timer定时器，他会在每次定时器滴答时执行super::greet方法，这个方法会调用超类的greet方法。
        TimerGreeter tg_ = new TimerGreeter();
        tg_.greet();

    }

    public static class RagnorGreteer{

        public void greet(ActionEvent event) {
            System.out.println("hello java");
        }
    }

    public static class TimerGreeter extends RagnorGreteer{

        public void greet(){
            Timer t = new Timer(10000, super::greet);
            t.start();
            JOptionPane.showMessageDialog(null, "Quit Program?");
            System.exit(0);
        }

    }

    // FocusSubPoint-05：How is the reference to the constructor in java lambda?
    public static void ReferenceConstructor(){
        // Topic-01: 构造器引用与方法引用类似==》只不过方法名为new==》Person::new 是Person构造器的一个引用
        // Topic-02: 我们看下场景吧：我需要一个Employee对象数组 ==》 java的Stream接口有一个toArray方法可以返回Object数组：
        //                      Object[] people = Stream.toArray(); // 但是我想要的是Employee数组
        //                      Employee[] people = Stream.toArray(Employee[]::new); // toArray方法调用这个构造器构造Employee对象，然后填充这个数组并返回

        System.out.println("java中构造器的引用方法还没有吃透"); // Todo: java中构造器的引用方法还没有吃透

    }

    // FocusSubPoint-06：what java lambda variable scope?
    public static void JavaLambdaVariableScope(){
        // Topic-01: 如果你需要在lambda表达式中访问外围方法或类中的变量
        repeatMessageClass rmc_ = new repeatMessageClass();
        rmc_.repeatMessage("ragnor", 1000);

        // Topic-02: lambda表达式的结构
        //          1) 参数；
        //          2) 一个代码块；
        //          3) 自由变量的值，这是值非参数而且不在代码中定义的变量

        // Topic-03: 比如下面的lambda表达式有1个自由变量text==》表示lambda表达式的数据结构必须存储自由变量的值，在这里就是字符串"hello"==》我们说它被lambda表达式捕获了（captured）

        // Topic-04: 代码块 + 自由变量的值 = 闭包（closure）

        // Topic-05: 在lambda中只会引用不会改变的变量：不管是在lambda内部改，还是外部改都是不允许的==》lambda表达式中捕获的变量必须实际上是最终变量（effectively final）==》即这个变量在初始化之后就不会再为它赋新值

        // Topic-06: lambda中的this会调用repeatMessageClass对象的

    }

    public static class repeatMessageClass{

        public static String showSecret(){
            return "secredID: xsxdssds";
        }

        public void repeatMessage(String text, int delay){

            ActionListener listener = event -> {
                System.out.println(text);  // text这个变量并不是在这个lambda表达式中定义的，实际上这是repeatMessage方法的一个参数变量
                // 考虑一个问题，如果lambda中的代码再repeatMessage调用返回很久之后才运行==》那个时候参数变量已经不存在了，如何保留text变量？

                System.out.println(this.showSecret());  // Topic-06
            };

            new Timer(delay, listener).start();
            JOptionPane.showMessageDialog(null, "quit?");
            System.exit(0);
        }

    }

    // FocusSubPoint-07：how to use Lambda in java？
    public static void UseLambdaInJava(){
        // Topic-01: 切记使用lambda表达式的重点是延迟执行：deferred execution ==》毕竟，如果想要立即执行代码，完全可以直接执行
        // Topic-02: 为什么想要以后再执行代码？
        //          1、在一个单独的线程中运行代码；
        //          2、多次运行代码；
        //          3、在算法的适当位置运行代码；==》排序中的比较操作
        //          4、发生某种情况时执行代码；==》web ui自动化 弹窗广告出来了，触发一系列操作等
        //          5、只在必要时才运行代码；
        // story: 假设你想要重复一个动作n次 ==》将这个动作和重复次数传递到一个repeat方法中
        repeat(10, () -> System.out.println("hello ragnor"));


        // Topic-03: Java Api中提供了一些最重要的函数式接口比如你要用的Runnable接口
        /*
        * 函数式接口             参数类型        返回类型        抽象方法名               描述              其他方法
        * Runnable              无             void          run         作为无参数或返回值的动作运行
        * Supplier<T>           无              T            get         提供一个T类型的值
        * Consumer<T>           T              void          accept     处理一个T类型的值              andThen
        * BiConsumer<T, U>     T, U            void          accept     处理T\U类型的值               andThen
        * Function<T, R>        T               R            apply      有一个T类型参数的函数          compose、andThen、identity
        * BiFunction<T, U, R>  T, U            void          accept     有T\U类型参数的函数             andThen
        * UnaryOperator<T>      T                T           apply      类型T上的一元操作符            compose、andThen、identity
        * BinaryOperator<T>     T,T              T           apply      类型T上的二元操作符            andThen、maxBy、minBy
        * Predicate<T>          T              boolean       test       布尔值函数                    and、or、negate、isEqual
        * BiPredicate<T, U>     T, U           boolean       test       有两个参数的布尔值函数          and、 or、negate
        *
        *
        * */

        // Topic-04: 基本类型的函数式接口 (P-、Q-代表Int\Long\Double)(p-、q-代表int\long\double)
        /*
        * 函数式接口                     参数类型                    返回类型                     抽象方法名
        * BooleanSupplier                none                      boolean                   getAsBoolean
        * P-Supplier                    none                        p-                       getAsP-
        * P-Consumer                     p-                         void                     accept
        * ObjP-Consumer<T>              T,p-                        void                     accept
        * P-Function<T>                 p-                          T                        apply
        * P-ToQ-Function                p-                          q-                       applyAsQ-
        * ToP-Function<T>               T                           p-                       applyAsP-
        * ToP-BiFunction<T, U>          T,U                         p-                       applyAsP-
        * P-UnaryOperator                p-                         p-                       applyAsP-
        * P-BinaryOperator              p-,p-                       p-                       applyAsP-
        * P-Predicate                   p-                          boolean                  test
        *
        * */


        // Topic-5: 我如果需要告诉这个动作它出现在哪一次循环中 ==》就是使用IntConsumer函数式接口接收int参数并且返回void
        repeat2(10, i -> System.out.println("CountDown: " + (9 - i)));

        // Topic-6: 如果让你设计自己的接口，其中只有一个抽象方法，可以用@FunctionalInterface注解来标记这个接口
        //           第一：如果你无意中增加了另一个非抽象方法，编译器会报错提示
        //           第二：javadoc页里会指出你的接口是一个函数时接口

    }

    public static void repeat(int n, Runnable action){
        for (int i = 0; i < n; i++) action.run();
    }

    public static void repeat2(int n, IntConsumer action){
        for (int i = 0; i < n; i++) action.accept(i);
    }

    // FocusSubPoint-08：java's Comparator usage？
    public static void JavaComparatorUage(){
        FocusPoint_03_JavaGenericArray.EmployeeDB[] people = new FocusPoint_03_JavaGenericArray.EmployeeDB[6];
        people[0] = new FocusPoint_03_JavaGenericArray.EmployeeDB("ragnor_01", 18, 20000, 1994, 12, 15);
        people[1] = new FocusPoint_03_JavaGenericArray.EmployeeDB("ragnor_02", 18, 30000, 1994, 12, 15);
        people[2] = new FocusPoint_03_JavaGenericArray.EmployeeDB("ragnor_03", 18, 40000, 1994, 12, 15);
        people[3] = new FocusPoint_03_JavaGenericArray.EmployeeDB("ragnor_04", 18, 50000, 1994, 12, 15);
        people[4] = new FocusPoint_03_JavaGenericArray.EmployeeDB("ragnor_05", 18, 50000, 1994, 12, 15);
        people[5] = new FocusPoint_03_JavaGenericArray.EmployeeDB("ragnor_06", 18, 70000, 1994, 12, 15);
        // Topic-01: Comparator接口包含很多方便的静态方法来创建比较器 ==》这些方法可以用于lambda表达式或者方法引用
        //      ==> 按照员工的薪资进行排序，代码更清晰
        Arrays.sort(people, Comparator.comparing(FocusPoint_03_JavaGenericArray.EmployeeDB::getSalary));

        //   ==> 先按照薪资排序，相同的话按照名字排序, 代码更灵活
        Arrays.sort(people, Comparator.comparing(FocusPoint_03_JavaGenericArray.EmployeeDB::getSalary).
                thenComparing(FocusPoint_03_JavaGenericArray.EmployeeDB::getName));

        // ==>comparing 和 thenComparing 都有变体形式 为了避免int long double值的装箱，比如comparingInt哈哈
//        Arrays.sort(people, Comparator.comparing(p -> p.getName().length()));  // 会触发自动装箱
        Arrays.sort(people, Comparator.comparingInt(p -> p.getName().length()));

        // todo:还有很多：用进废退
    }

}
