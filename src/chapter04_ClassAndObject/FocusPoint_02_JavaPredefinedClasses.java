package chapter04_ClassAndObject;

import java.time.LocalDate;
import java.util.Date;

public class FocusPoint_02_JavaPredefinedClasses {
    public static void main(String[] args) {
        DefineObjectAndObjectVariables();
        UseJavaLocalDateClass();
        MutatorAndAccessor();
    }

    // FocusSubPoint-01：How to Define objects And object variables？
    public static void DefineObjectAndObjectVariables(){
        // Topic-01: java中如果想使用对象==》必须首先构造对象 + 指定其初始状态 ==》然后对对象应用方法
        // Topic-02: java中使用构造器：constructor构造新实例=》构造器是一种特殊的方法：用来构造并初始化对象
        // Topic-03: 构造器的名字应该与类名相同：例如 Date类的构造器名为Date
        // Topic-04: 如果想构造一个对象，需要在构造器前面加上new操作符：例如 Date birthday = new Date();
        // Topic-05: 一定要意识到：一个对象变量并没有实际包含一个对象，而仅仅引用一个对象。
        // Topic-06: java中，任何对象变量的值都是对存储在另外一个地方的一个对象的引用, new操作符的返回值也是一个引用

        // step-01: 左边就是定义对象变量    右边就是定义新变量
        Date birthday = new Date(); // 其实是两个部分：表达式new Date() 构造了一个Date类型的对象 并且它的值是对新创建对象的引用
                                    // 这个引用存储在变量deadline中

        Date mybirthday;
        mybirthday = birthday;    // mybirthday 与 birthday这两个变量引用同一个对象
        
        System.out.printf("birthday mem hashcode is: %s\n mybirthday men hashcode is: %s", System.identityHashCode(birthday), System.identityHashCode(mybirthday));


    }

    // FocusSubPoint-02：java local date class？
    public static void UseJavaLocalDateClass(){
        // Topic-01: 时间：是距离一个固定时间点的毫秒数（可正、可负）表示的==》这个点就是纪元（epoch）==》UTC(Coordinated Universal Time)时间 1970年1月1日 00:00:00
        //           GMT(Greenwich Mean Time)代表的是格林威治时间

        // Topic-02: java类库表示分别包含了两个类：一个用来表示时间点的Date类；另一个是用来表示大家熟悉的日历的 LocalDate类是为了表示日历==》将时间与日历分来是一种很好的面向对象设计：不同的类表示不同的概念
        // Topic-03: 不要使用构造器来构造LocalDate类的对象==》应该使用 静态工厂方法（factory method）代表你调用构造器
        LocalDate ragnorCalendar = LocalDate.of(1994, 12, 31);

        // Topic-04: 实际上，Date类还要getDay， getMonth以及getYear等方法，然而并不推荐使用这些方法=》当类库的设计者意识到某个方法不应该存在时，就把它标记为不鼓励使用。
        System.out.println(ragnorCalendar.plusDays(1000));
        System.out.println(ragnorCalendar.getYear());
        System.out.println(ragnorCalendar.getMonthValue());
        System.out.println(ragnorCalendar.getDayOfYear());



    }

    // FocusSubPoint-03：What is a mutator(更改器)? Accessor（访问器）? What are the changer methods and accessor methods?
    public static void MutatorAndAccessor(){
        // Topic-01: 更改器就是更改了调用这个方法的对象 的方法
        String s_1 = "hello java";
        String s_2 = s_1.toUpperCase();
        // 说明在s_1字符串上调用toUpperCase方法时，s_1这个字符串仍保持不变， s_2是一个将字符串大写的新字符串
        System.out.printf("s_1 mem id is %s: \n s_2 mem id is %s:", System.identityHashCode(s_1), System.identityHashCode(s_2));


        // Topic-02: 只访问对象而不修改对象的方法有时称为访问器方法
        LocalDate ragnor_date_format = LocalDate.of(1994, 12, 15);

        System.out.println(ragnor_date_format.getDayOfYear()); // 只是访问对象，而不是修改对象

        // Topic-03： 类的接口就是函数：使用类的接口来完成相当复杂的任务，
        // 而无需了解实现的细节：比如LocalDate.plusDays()就可以实现推断多少天后是哪年几月几日；复杂性被封装了起来
    }

    // 
}
