package com.ragnor.corejava.chapter06_InterfaceAndlamdbaAndInnerClass;

import com.ragnor.corejava.chapter05_Inherit.FocusPoint_03_JavaGenericArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;

public class FocusPoint_02_JavaInterfaceExample {
    public static void main(String[] args) {
//        InterfaceAndCallback();
//        JavaComparatorInterface();
        JavaCloneObject();

    }

    // FocusSubPoint-01：What are the interface and callback mechanism in java? How to achieve？
    public static void InterfaceAndCallback(){
        // Topic-01: callback（回调）是一种常见的程序设计模式 ==》在回调模式中，可以指出某个特定事件发生时应该采取的动作
        ActionListenner listenner = new TimePrinter();
        javax.swing.Timer t = new javax.swing.Timer(10000, listenner);  // 构造一个定时器，每隔10000毫秒通告listener一次
        t.start();  // 启动定时器，一旦启动成功，定时器将调用监听器的actionPerformed
        JOptionPane.showMessageDialog(null, "Quit Program?");  // 显示一个包含一条消息和ok按钮的对话框，这个对话框将位于其parent组件的中央。如果parent为null，对话框将显示在屏幕的中央。
        System.exit(0);

    }

    public interface ActionListenner extends ActionListener {
        void actionPerformed(ActionEvent event);  // 超类优先
    }

    static class TimePrinter implements ActionListenner{

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("At the tone, the time is" + ":" +  new Date());
//            Toolkit.getDefaultToolkit().beep();
        }
    }

    // FocusSubPoint-02: What is the Comparator interface in java? How to achieve it?
    public static void JavaComparatorInterface(){
        // Topic-01: java中如果想对一个对象数组排序==》前提是这些对象是实现了Comparable接口的类的实例
        String[] rwords = new String[2];
        rwords[0] = "qna";
        rwords[1] = "ym";

        // Topic-02: 比较器是实现了Comparator接口的类的实例
        RComparator<String> comp = new LengthComparator();

        // Topic-03: 这个compare方法要在比较器对象上调用，而不是字符串本身上调用
        // Topic-04: 我们需要建立一个LengthComparator的对象的实例==》通过这个实例调用compare方法
        if (comp.compare(rwords[0], rwords[1]) > 0){
            System.out.println("first > second!");
        }
    }

    public interface RComparator<T>{
        int compare(T first, T second);
    }

    static class LengthComparator implements RComparator<String>{
        public int compare(String first, String second){
            return first.length() - second.length();
        }
    }

    // FocusSubPoint-03：What is object cloning in java? How to achieve it?
    public static void JavaCloneObject(){
        // Topic-01: java中的Cloneable接口指示了一个类提供一个安全clone方法的：克隆的细节技术性很强

        // Topic-02: java中一个包含对象引用的变量建立副本时会发生什么？==》原变量和副本都是同一个对象的引用==》任何一个变量改变都会影响另一个变量
        FocusPoint_03_JavaGenericArray.EmployeeDB original = new FocusPoint_03_JavaGenericArray.EmployeeDB("ragnor", 12, 20000, 1994, 12, 15);
        FocusPoint_03_JavaGenericArray.EmployeeDB copy = original;
        copy.raiseSalary();  //(default raise 1000) oops-also changed original
        System.out.println(original.getSalary());

        // Topic-03: java中如果希望copy是一个新对象，它的初始状态与original相同，但是之后它们各自会有自己不同的状态，这种情况下就可以使用clone方法。
        try {

            // Topic-04: Object类中的clone方法声明为protected==》所以下面我直接调用original的clone()方法没有？==》不是说所有的子类都能访问protected方法吗？不是所有的类都是Object的子类吗？
            //           ==》 受保护访问的规则比较微妙==》子类只能调用受保护的clone方法来克隆它自己的对象
            //           ==》 必须重新定义clone为public才能允许所有方法克隆对象
//            FocusPoint_03_JavaGenericArray.EmployeeDB copy2 = (FocusPoint_03_JavaGenericArray.EmployeeDB) original.clone();
            FocusPoint_03_JavaGenericArray.EmployeeDB copy2 = original.clone(); // 上面的写法与下面的写法最大的区别就是EmployeeDB类中的clone方法实现
            copy2.raiseSalary(); // original will not raise salary!
            System.out.println(original.getSalary());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // Topic-05: 对于每一个类，需要确定：
        //          1) 默认的clone方法是否满足要求;
        //          2) 是否可以在可变的子对象上调用clone来修补默认的clone方法；
        //          3) 是否不改使用clone （默认选项）
        //          如果选择第1项或第2项，类必须：
        //          1) 实现Cloneable接口；
        //          2) 重新定义clone方法，并指定public访问修饰符。

        // Topic-5: 如果在一个对象上调用clone，但这个对象的类并没有实现Cloneable接口==》Object类的clone方法就会抛出一个CloneNotSupportedException
        //          ==》当然 EmployeeDB类实现了Cloneable接口，所以不会抛出这个异常==》不过编译器并不了解这一点，因此需要声明（throw）这个异常

        // Topic-6: 浅拷贝拷贝的是引用；深拷贝是内存中新开辟空间

    }

}
