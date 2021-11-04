package com.ragnor.corejava.chapter04_ClassAndObject;

import java.time.LocalDate;

public class FocusPoint_10_JavaDesignClassSkills {
    public static void main(String[] args) {
        DesignJavaClassSkills();
    }

    public static void DesignJavaClassSkills(){
        // Topic-01: 一定要保证数据私有!!!
        //           这是最重要的：绝对不要破坏封装性！
        //           有时候需要编写一个访问器方法或者更改器方法，但是最好还是保持【实例域的私有性】
        //           很多惨痛的经验告诉我们，数据的表示形式很可能会改变，但是他们的使用方式却不会经常发生变化
        //           那么当数据保持私有的时候，他们的表示形式的变化不会对类的使用者产生影响，及时出现bug也易于检测

        // Topic-02: 一定要对数据初始化！！！
        //           java不对局部变量进行初始化，但是会对对象的实例域进行初始化。
        //           最好不要依赖于系统的默认值，而是应该显示地初始化所有的数据，具体的初始化方式可以是【提供默认值】，也可以是【在所有的构造器中设置默认值】。

        // Topic-03：不要在类中使用过多的基本类型
        //           就是用其他的类代替多个相关的基本类型的使用。
        //           这样会使类更加易于理解且易于修改。
        //           例如：class Customer{
        //                      private String street;
        //                      private String city;
        //                      private String state;
        //                      private int zip;
        //           }
        //          ==> 构造一个新的类 Address，替换上面Customer中的这些实例域 ==》会更容易处理地址的变化（因为Customer范围更大里面的内容更多，address权责集中）
        //

        // Topic-04: 不是所有的域都需要独立的域访问器和域更改器
        //           就是说：如果一个Employee类里面的实例域-salary - hiraDay 可能需要获取（get） + 设置（set）
        //           但是一旦构造了Employee对象，那么这个对象是不应该由设置自己的薪资、入职日期的权限的
        //           并且在对象中，常常也包含一些不希望别人获取或者设置的实例域：例如每个顾客都有自己的地址信息是不希望别的顾客获取到的

        // Topic-05: 将职责过多的类进行分解
        //           什么是过多呢？==》每个人看法都一样
        //           case1: 如果可以明显的将一个复杂的类分解成两个更为简单的类，那就应该将其分解：比如一个类叫牌：里面有一副牌的洗牌、发牌、换牌 还有一张牌的值、花色那就分成两个类：CardDeck + Card就好啦
        //           case2: 如果你设计了10个类，每个类只有一个方法，未免也太极端了

        // Topic-06: 类名和方法名要能够体现它们的职责
        //          1、变量名都要求见名知意，类更应该如此（你看java的Date实际上就是一个时间而不是日期呵呵）
        //          2、命名类：形容词/动名词（有-ing后缀的） + 名词。例如： RushOrder BillingAddress
        //          3、命名方法： 访问器 get + xxx getName; 更改器 set + xxx setName;

        // Topic-07: 优先使用不可变的类
        //          1、更改对象的问题在于：如果多个线程试图同时更新一个对象，就会发生并发更改，其结果是不可预料的，如果类是不可变的，就可以安全的在多个线程间共享其对象
        //                             比如java.time.LocalDate类是不可变的，你调用plusDays方法并不是改变对象，而是返回状态已经修改的新对象
        //          2、并不是所有的类都应当是不可变：比如Employee类的raiseSalary方法返回一个新的Employee对象，这是很奇怪的

        System.out.println("until now you just learn OOP: Encapsulation\n " +
                "inherit " +
                "Polymorphism wating for you");

    }
}
