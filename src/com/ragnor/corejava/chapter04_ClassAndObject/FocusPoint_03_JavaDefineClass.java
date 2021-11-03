package com.ragnor.corejava.chapter04_ClassAndObject;

/**
 * This program tests the Employee class
 * @version 1.1.0 2021.11.1
 * @author com.com.ragnor.corejava.com.ragnor.li
 * */

import java.time.LocalDate;

public class FocusPoint_03_JavaDefineClass {

    public static void main(String[] args) {
        // core: java中用户自定义类

        // Topic-01: 截止到目前编写的都是最最最基础简单的类：main方法 + psv类
        // Topic-02: 我们需要学习如何设计复杂应用程序所需要的各种主力类（workhorse class）
        // Topic-03: 通常主力类中是没有main方法的=》但是有自己的实力域与实例方法 =》一个完成的程序包含若干的类 + 1个main入口方法

        DefineAndCallEmployeeClass();
        UseMultipleSourceJavaFiles();
        InDepthAnalysisEmployeeClass();

    }

    // FocusSubPoint-01-Call：Story:How to Define EmployeeClass？
    public static void DefineAndCallEmployeeClass(){
        // step-01: 创建3个staff对象
        Employee[] staff = new Employee[3];
        staff[0]=new Employee("com/com.ragnor",18000,1994,12,15);
        staff[1]=new Employee("qingning",8000,2000,9,21);
        staff[2]=new Employee("qingqing",10000,1995,11,11);

        // step-02: 为每个员工涨薪5%
        for (Employee e: staff){
            e.raiseSalary(5);
        }

        // step-03: 打印出所有的员工基本信息
        for (Employee e: staff){
            System.out.printf("员工姓名: %s\t员工薪资: %e\t员工入职日期: %s\n", e.getName(), e.getSalary(), e.getHireDay().toString());
        }
    }

    // FocusSubPoint-02：How to use multiple source files in java？
    public static void UseMultipleSourceJavaFiles(){
        // Topic-01： 很多程序员是习惯将每一个类存放在一个单独的源文件中的：例如Employee类存放在Employee.java中
        //                                                       EmployeeTest类存在在EmplyeeTest.java中
        // Topic-02: 如果如Topic-01所说，那我怎么编译呢？==》 javac Employee*.java即可 / javac EmployeeTest.java也可以
        //           所有与通配符匹配的源文件都将被编译成类文件 / 自动去发现编译相关的源文件

        System.out.println("Method-01: javac Employee*.java");
        System.out.println("Method-02: javac EmployeeTest.java");
    }

    // FocusSubPoint-03：How to In-depth analysis Employee Class？
    public static void InDepthAnalysisEmployeeClass(){

        // Topic-01: 从类的实例域来看：在Employee类的实例中有3个实例域用来存放将要操作的数据
        //           关键字 private确保只有Employee类自身的方法能够访问这些实例域==》而其他的类的方法不能够读写这些域
        //           可以使用public标记实例域=》但这是一种及不提倡的做法：public数据域允许程序中的任何方法对其进行读取和修改==》完全破坏了封装
        // Topic-02: 需要注意的是：name （name域是String类的独享）
        //                      hireDay（是LocalDate的类对象）这两个实例域本身就是对象
        //                      double就是java内置的数据类型而已 不一样，怪不得IDE显示白/橙 加以区分
        System.out.println("Part-01：整体结构");
        System.out.println("private String name;\n" +
                "private double salary;\n" +
                "private LocalDate hireDay;");

        // Topic-03: 从类的方法上看：Employee类包含 1个构造器 + 4个方法
        System.out.println("EmployeeClass-Constructor: public Employee(String n, double s, int year, int month, int day){...}");
        // Topic-04: 这个类的所有方法都被标记为public：关键字public意味着：任何类的任何方法都可以调用这些方法：共有四种访问级别todo
        System.out.println("EmployeeClass-Method:  " +
                "public String getName(){}\n" +
                "public double getSalary(){}" +
                "\n public LocalDate getHireDay(){}" +
                "\npublic void raiseSalary(double byPercent){}");


        // Topic-05: 构造器与类同名
        // Topic-06: 在构造Employee类的对象的时候，构造器会运行==》将实例域初始化为所希望的状态

        System.out.println("Part-02: 构造器");
        System.out.println("例如：实例化类Employee的时候==> New Employee('com.com.ragnor.corejava.com.ragnor', 40000, 1994, 12, 15 )\n " +
                "将会把实例域设置为：\n" +
                "name = 'com.com.ragnor.corejava.com.ragnor'\n" +
                "salary = 40000\n" +
                "hireDay = 15.12.1994");

        // Topic-07: 构造器与其他方法有一个重要的不同：构造器总是伴随着new操作符的执行被调用 + 而不能对一个已经存在的对象调用构造器来达到重新设置实例域的目的
        System.out.println("例如： staff[0].Employee('qing', 10000, 1995, 1, 1)");

        // Topic-08: 构造器的注意事项：
        // 1、构造器与类同名
        // 2、每个类可以有一个以上的构造器
        // 3、构造器可以有0个、1个或多个参数
        // 4、构造器没有返回值
        // 5、构造器总是伴随着new操作一起调用
        // 6、所有的java对象都是在堆中构造的
        // 7、千万不要在构造器中定义与实例域重名的局部变量：会屏蔽实例域
        /* 正确：
        *  public Employee(String n, double s, int year, int month, int day){
                name = n;
                salary = s;
                hireDay = LocalDate.of(year, month, day);
            }
        *
        * 错误：
        *  public Employee(String n, double s, int year, int month, int day){
        *    String name = n;
        *    double salary = s;
        *    LocalDate hireDay = LocalDate.of(year, month, day);
        *   }
        *
        *  */

        // Topic-09： 比如：staff[0].raiseSalary(5);
        //            隐式（implicit）参数：是出现在方法名前面的Employee类对象 ==》隐式参数就是方法调用的目标或者叫接收者
        //            显示（explicit）参数：是位于方法名后面括号中的数值
        System.out.println("Part-03: 隐式参数与显示参数");
        // Topic-10: 在每个方法中：关键字this表示隐式参数==》所以对于raiseSalary也可以这样写==》好处就是区分：实例域 与 局部变量
        /*
        * public void raiseSalary(double byPercent){   // 带this的就是实例域（salary） 不带的 就是局部变量（raise）
        *   double raise = this.salary * byPercent / 100;
        *   this.salary += raise;
        * }
        *
        *
        * */

        // Topic-11: 看下下面的方法：是3个访问器方法 ==》由于只返回实例域值，又称为域访问器

        // Topic-12: 对于需要获得或者设置实例域的值==》应该提供一下三项内容
        //           1、一个私有的数据访问域： private String name；
        //           2、一个公有的域访问器方法： public String getName(){};
        //           3、一个公有的域更改器方法： public void modifyName(){};

        // Topic-13: 不要编写返回引用可变对象的访问器方法 + 如果需要返回一个可变对象的引用：首先对它进行clone==》return （Date）hireDay.clone();
        //           比如你把Employee类中的  public LocalDate getHireDay(){return hireDay;}
        //           改成 public Date getHireDay(){return hireDay;}
        //           返回值如果是个Date类型==》由于Date对象是可变的==》那就可以调用更改器setTime更改==》可以hiraDay的实例域明确说明是private了==》破坏了封装性
        System.out.println("Part-04: 封装的优点");
        /*
        *  public String getName(){
                return name;
            }

            public double getSalary(){
                return salary;
            }

            public LocalDate getHireDay(){
                return hireDay;
            }
        *
        * */

        // Topic-14: 一个方法可以访问所属类的所有对象的私有数据==》很多人感到奇怪
        System.out.println("Part-05:基于类的访问权限");

        /*
        * class Employee{
        *   private String name;
        *
        *   public boolean equals(Employee other){
        *       return this.name.equals(other.name);
        *   }
        *
        * }
        *
        * if (com.com.ragnor.corejava.com.ragnor.equals(qing){...} ) 调用：这个方法访问了ragnor的name私有域 + qing的name私有域 ==》因为这两个都是Employee类对象
        *  ==》而 Employee类的方法可以访问Employee类的任何一个对象的私有域
        *
        * */

        // Topic-15: 就是说尽管 getName是public的，但是实现getName的算法可能需要好几个函数协同，我对外不需要暴露这些==》那就private
        // Topic-16: 只要方法是私有的：它就不会被外部的其他类操作调用==》你可以删除
        //           如果方法是公有的：就不能直接删去，因为其他的代码很可能依赖它
        System.out.println("Part-06: 私有方法");

        // Topic-17: 第一实例域：就是 构建对象时必须初始化这样的域 + 必须确保在每一个构造器执行之后这个域的值被设置 + 在之后的操作中不能再对它修改：没有set方法
        // Topic-18: 第二实例域：用final修饰符进行生命：private final string name；
        // Topic-19: 第三final修饰符的应用场景：大都应用于
        //                                          1、基本类型域（primitive）
        //                                          2、不可变类的域（immutable）：如果类中的每个方法都不会改变其对象=》就是不可变类：比如String类
        //
        System.out.println("Part-07: final实例域");






    }


}



// FocusSubPoint-01-Define：Story:How to Define EmployeeClass？
class Employee{
    // step-01: field 实例域： 构建对象时必须初始化这样的域 + 必须确保在每一个构造器执行之后这个域的值被设置 + 在之后的操作中不能再对它修改：没有set方法
    private final String name;
    private double salary;
    private final LocalDate hireDay;

    // step-02: constructor 构造器: 有点java的味道了
    public Employee(String n, double s, int year, int month, int day){
        this.name = n;
        this.salary = s;
        this.hireDay = LocalDate.of(year, month, day);
    }


    // step-03: method 方法 ： 4个
    public String getName(){
        return name;
    }

    public double getSalary(){
        return salary;
    }

    public LocalDate getHireDay(){
        return hireDay;
    }

    public void raiseSalary(double byPercent){
        double raise = salary * byPercent / 100;
        salary += raise;
    }


}