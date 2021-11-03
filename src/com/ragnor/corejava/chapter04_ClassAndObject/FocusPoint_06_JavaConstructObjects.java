package com.ragnor.corejava.chapter04_ClassAndObject;

import java.util.Random;

public class FocusPoint_06_JavaConstructObjects {
    public static void main(String[] args) {
        // Core: java对象构造

        Overloading();
        DefaultDomain();
        DefineParameterlessConstructor();
        InitializeAnExplicitField();
        DesignParameterName();
        CallAnotherConstructor();
        InitializeBlock();
        ConstructorTest();
        ObjectDestructionAndFinalizeMethod();

    }

    // FocusSubPoint-01：What is Overloading？
    public static void Overloading(){
        // Topic-01: 我们先看一个重载的例子: 特征是==》相同的名字 不同的参数
        StringBuilder message = new StringBuilder();
        StringBuilder todoList = new StringBuilder("To do:\n");

        System.out.println(message.append("1").toString()); // 这个append更明显
        System.out.println(todoList.append(1).toString());
        // Topic-02: 编译器是必须要挑选出具体执行哪个方法的==》
        //           通过各个方法给出的参数类型 + 特定方法调用所使用的值类型来进行匹配挑选相应的方法==》
        //           如果编译器找不到匹配的参数，就会产生编译时错误，因为根本不存在匹配 ==》这个过程被称为"overloading resolution"重载解析

        // Topic-03: 方法的签名（signature）：java允许重载任何方法==》要完整的描述一个方法，需要指出方法名以及参数类型这就是方法的签名
        //                                 但是不能有两个名字相同+参数类型也相同却返回不同值的方法
    }

    // FocusSubPoint-02：How to initialize the default domain？
    public static void DefaultDomain(){
        // Topic-01: 如果在构造器中没有显式的给域赋予初值，那么就会被自动的赋为默认值：数值为0、布尔值为false、对象引用为null
        //           但是只有缺少程序设计经验的人才会这么做==》如果不明确的对域进行初始化，就会影响程序代码的可读性。
        // 下面这个就没问题：因为实例域中 定义：private String name； + 在构造器中进行初始化： this.name = s;(s为外部传入参数)
        Employee huayun_staff = new Employee("com/com.ragnor", 38000, 1990, 12, 15);
        System.out.println(huayun_staff.getName().length());

        // 但是如果这样：实例域中 定义：private String name； + 构造器中没有：this.name = s; name就被自动赋值为null(因为是个对象)
        //                        这时候调用huayun_staff.getName().otherMethod(); ==>null.otherMethod()==> throws exception if huayun_staff.getName() is null


    }

    // FocusSubPoint-03：How to define a parameterless constructor？
    public static void DefineParameterlessConstructor(){
        // Topic-01: 首先什么是无参数构造器？==》就是没有参数的构造器
        /*
        * public Employee(){
        *   name="";
        *   salary=0;
        *   hireDay=LocalDate.now();
        *   }
        * */

        // Topic-02: 如果希望所有域都被赋予默认值==》可以采用如下的格式
        /*
        * public Employee(){
        * }
        * */

        // Topic-03: 如果在编写一个类的时候没有编写构造器==》系统会提供一个无参数构造器=》这个构造器会将所有的实例域设置为默认值（数值型0、布尔型false、对象null）
        //           仅当类没有提供粉盒构造器的时候==》系统才会提供一个默认的构造器。

        // Topic-04: 如果类中提供了至少一个构造器，但是没有提供无参数的构造器，则在构造对象时如果没有提供参数就被视为不合法
        // Employee illegal_staff = new Employee(); // this is illegal because no paras offered!




    }

    // FocusSubPoint-04：How to initialize an explicit(显式) field？
    public static void InitializeAnExplicitField(){
        // Topic-01: 什么叫显式域初始化呢？=》就是对域进行初始化的操作（赋值）是显式的
        /*
        * class Employee{
        *   private String name = "";
        * }
        * */

        // Topic-02: 通过重载类的构造器方法，可以采用多种形式设置类的实例域的初始状态==》
        //           确保不管怎么调用构造器，每个实例域都可以被设置为一个有意义的初值，这是一种很好的设计习惯

        /*
        * class Employee{
        *   private static int nextId;
        *   private int id = assignID();
        *
        *   private static int assignID(){
        *       int r = nextId;
        *       nextId++;
        *       return r;
        *   }
        * }
        *
        *
        *
        * */

        System.out.println("确保不管怎么样调用构造器，每个实例域都可以被设置为一个有意义的初值，这是一种很好的设计习惯！");
    }

    // FocusSubPoint-05：How to design the parameter name?
    public static void DesignParameterName(){
        // Topic-01: 在编写很小的构造器的时候==》常常在参数命名上出现错误
        /*
        * 只有阅读代码才能够了解参数a、s的含义
        * public Employee(String a, double s){
        *   name = a;
        *   salary = s;
        * }
        *
        * VS
        *
        * public Employee(String aName, double sSalary){
        *   name = aName;
        *   salary = sSalary;
        *
        * VS
        *  this指代隐式参数==》也就是所构造的对象同python的self
        * public Employee(String aName, double sSalary){
        *   this.name = aName;
        *   this.salary = sSalary;
        *
        *
        *
        *
        *
        * */
    }

    // FocusSubPoint-06：How to call another constructor？
    public static void CallAnotherConstructor(){
        // Topic-01: 什么是调用另一个构造器：顾名思义就是在一个构造器中调用另外一个构造器
        // Topic-02: 如果构造器的第一个语句形如this(...) ==>这个构造器将调用同一个类的另一个构造器
        // Topic-03: 采用这种方式使用this关键字非常有用：这样对公共的构造器代码部分只编写一次即可

        /*
        * 当调用new Employee(80000)时Employee(double s)构造器将会调用Employee(String， double)构造器
        * public Employee(double s){
        *   this("Employee #" + nextId, s); // call Employee(String, double)
        *   nextId++;
        *   }
        * */
    }

    // FocusSubPoint-07：How to initialize the "block"？
    public static void InitializeBlock(){
        // Topic-01: 初始化数据域的方法有3种：
        //           1、在构造器中设置值 public Employee(int i_id){this.id = i_id;}
        //           2、在声明中赋值： private int id = 0;
        //           3、初始化块： 不常见，通常会直接将初始化代码放在构造器中
        //
        //                           class Employee{
        //                              private static int nextId;
        //                              private int id;
        //
        //                              下面这个就是初始化块==》无论使用哪个构造器构造对象==》id域都在对象初始化块中被初始化
        //                              首先运行初始化块，然后才运行构造器的主体部分
        //                              {
        //                                  id = nextId;
        //                                  nextId++;
        //
        //                              }
        //                              下面的step-02原因：建议我们的初始化块是放在 域 下面的
        //
        // Topic-02: 由于初始化数据域有多种途径==》所以列出构造过程的所有路径可能相当混乱==》调用构造器的具体处理步骤：
        //          step-01: 所有数据域被初始化为默认值（0， false、null）
        //          step-02: 按照在类声明出现的次序，依次执行所有域初始化语句和初始化块
        //          step-03: 如果构造器的第一行调用了第二个构造器，则执行第二个构造器主体
        //          step-04: 执行这个构造器的主体。

        // Topic-03： 对类的静态域进行初始化的方式：
        //            1、提供初始值： private static int nextId = 1;
        //            2、静态的初始化块：
        //                          static
        //                          {
        //                              Random generator = new Random();
        //                              nextId = generator.nextInt(1000);
        //                          }




    }

    // FocusSubPoint-08：How to use object destruction and finalize methods？
    public static void ObjectDestructionAndFinalizeMethod(){
        // Topic-01: 析构器：其中放置一些当对象不再使用时需要执行的清理代码
        //           在析构器中最常见的操作就是回收分配给对象的存储空间 ==》 java有自动的垃圾回收器故java不支持析构器
        // Topic-02: java中可以为任何一个类添加finalize方法 ==》finalize方法将在垃圾回收器清除对象之前调用==》在实际使用
        //           中不要依赖于使用finalize方法回收任何短缺的资源（除了内存还有文件句柄等）
        // Topic-03: 有两个方法可以确保finalizer方法在java关闭前被调用：
        //            1、System.runFinalizersOnExit(true); ==>不安全、不推荐使用
        //            2、Runtime.addShutdownHook添加关闭钩
        // Topic-04: 如果某个资源需要在使用完毕后立刻被关闭，那么就需要由人工来管理==》对象用完时可以应用一个close方法来完成相应的清理操作==》
        //           todo:怎么确保这个方法得到自动调用
        System.out.println("finalize");
    }

    // summary
    public static void ConstructorTest(){
        // Topic-01:下面的ConstructorTest就整合所有的点
        //       1、重载构造器
        //       2、用this(。。。) 调用另一个构造器
        //       3、无参数构造器
        //       4、对象初始化块
        //       5、静态初始化块
        //       6、实例域初始化块

        // step-01: fill the staff array with three Employee object
        EmployeeNew[] staff = new EmployeeNew[3];

        staff[0] = new EmployeeNew("harry", 40000);
        staff[1] = new EmployeeNew(60000);
        staff[2] = new EmployeeNew();

        // step-02: print out information about all employeenew objects
        for (EmployeeNew e: staff){
            System.out.printf("name: %s\t id: %d\t salary: %g\n", e.getName(), e.getId(), e.getSalary());
        }


    }
}


class EmployeeNew{

    // step-01: 初始化域
    private static int nextId; // 定义静态初始化域

    private final int id; // id不可变
    private String name = ""; // 类的实例域进行初始化
    private double salary;

    // step-02： 初始化 静态块
    static{
        Random generator = new Random();
        nextId = generator.nextInt(1000);

    }

    // step-03: 初始化 实例块
    {
        id = nextId;
        nextId++;
    }

    // step-04: 三种重载构造器的方法
    public EmployeeNew(String n_name, double s_salary){
        this.name = n_name;
        this.salary = s_salary;
    }

    public EmployeeNew(double s){
        this("EmployeeNew #" + nextId, s); // 调用EmployeeNew(Steing, double)构造器方法

    }

    public EmployeeNew(){} // 无参数构造器 + 默认构造器 ==》 name = "" + salary = 0.0 + id = step-03初始化实例块的结果

    // step-05: get 方法
    public String getName(){
        return this.name;
    }

    public double getSalary(){
        return this.salary;
    }

    public int getId(){
        return this.id;
    }

    // step_06: set方法
    public double raiseSalary(double byPercent){
        return this.salary * byPercent / 100 + this.salary;
    }

}