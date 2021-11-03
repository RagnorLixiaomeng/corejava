package chapter04_ClassAndObject;


public class FocusPoint_04_JavaDefineStaticFieldsAndStaticMethods {
    public static void main(String[] args) {
        // Core: java定义静态域 静态方法

        // Topic-01: 我们写了这么多，有没有发现main方法都被标记为static修饰符==》static修饰符的含义是什么？
        //           终于找到了：static ：属于类且不属于类对象的变量和函数
        StaticFields();
        StaticConstant();
        StaticMethod();
        FactoryMethod();
        MainMethod();

    }

    // FocusSubPoint-01：What is static fields?
    public static void StaticFields(){
        // Topic-01: 如果将域定义为static那么它就是静态域==》静态域的本质是类域==》用static是因为沿用了c++的关键字
        // Topic-02: 每一个实例域都有自己的一份拷贝 VS 每个类中该域只有一个
        /*
        * 对于下面的EmployeeStatic类，如果进行实例化对象1000个，那么这1000个对象都有自己的id域，但是
        * 这个类的所有实例将共享一个nextID静态域：有没有实例它也存在，你多也好少也好我就是存在1个
        * */
        EmployeeStatic[] staff = new EmployeeStatic[2];

        staff[0] = new EmployeeStatic(1, true, 1.0);
        staff[1] = new EmployeeStatic(2, false, 2.0);

        System.out.printf("staff 0 static nextid : %s\n instance id: %s\n",
                System.identityHashCode(staff[0].get_static_nexID()),
                System.identityHashCode(staff[0].get_instance_id()));

        System.out.printf("staff 1 static nextid : %s\n instance id: %s\n",
                System.identityHashCode(staff[1].get_static_nexID()),
                System.identityHashCode(staff[1].get_instance_id()));
    }

    // FocusSubPoint-02：What is a static constant?
    public static void StaticConstant(){
        // Topic-01: 静态常量的定义: public static final double PI = 3.141592653589793238; 静态域常量：感觉就是类域常量
        //                        public final double PI = 3.141592653589793238; 实例域常量
        // Topic-02: 由于每个类对象都可以对公有域进行修改，所以最好不要将域设计为public==》但是公有常量（就是final域）却没有问题==》
        //           因为被声明为final：final粗一点理解就是 在使用前要求必须被初始化 + 不可变

        System.out.println("public static final double PI = 3.141592653589793238; 静态域常量：感觉就是类域常量");

    }

    // FocusSubPoint-03：What is a static method?
    public static void StaticMethod(){
        // Topic-01: 什么是静态方法？： public static int getNextID(){return nextID}
        //                           静态方法就是一种不能向对象实施操作的方法
        //                           静态方法是没有this参数的方法
        //                           在一个非静态的方法中，this参数表示这个方法的隐式参数

        // Topic-02: 什么时候需要静态方法？：
        //           1、一个方法不需要访问对象的状态 + 其所需要的参数都是通过显示参数提供：Math.pow(2, 3)计算2的3次方
        //           2、一个方法只需要访问类的静态域：Employee.getNextID()

        // Topic-03: 怎么调用静态方法？
        //           1、使用类名的方式调用，不推荐使用对象来调用
        System.out.println("静态方法是没有this参数的方法！");


    }

    // FocusSubPoint-04：What is a factory method?
    public static void FactoryMethod(){
        // Topic-01: 定义一个用于创建对象的接口，让子类决定实例化哪一个类。Factory Method使一个类的实例化延迟到其子类。
        // Topic-02: 工厂模式的主要功能就是帮助我们实例化对象。之所以名字中包含工厂模式四个字，是因为对象的实例化过程是通过工厂实现的，是用工厂代替 new 操作的

        // Topic-03: 给你一个需求你就明白了：如果我想做一个计算机程序实现加减乘除运算该怎么做？
        //           step01: 定义4个类
        //           step02: 实例化不同的类 + 不同的方法名（传入参数）进行调用实现

        //           简单工厂或者叫做静态工厂模式是这么去做的
        //           step01: 定义一个类 + 一个方法（意义就在于外界只知道这一个方法，但是需要在方法内部 switch case哈）
        //           step02: 实例化这1个类 + 1个方法（只需要改变传入的 运算符即可）

        /* Topic-04: 想要使用不同的运算的时候就要创建不同的类，并且要明确知道该类的名字。
        那么这种重复的创建类的工作其实可以放到一个统一的类中去管理。
        这样的方法我们就叫做「简单工厂模式」，在简单工厂模式中用于创建实例的方法是静态(static)方法，
        因此简单工厂模式又被称为「静态工厂方法」模式。。简单工厂模式有以下优点：
                         一个调用者想创建一个对象，只要知道其名称就可以了。
                         屏蔽产品的具体实现，调用者只关心产品的接口。*/

        // Topic-05: 为什么不用构造器完成这个操作
        //           1、无法命名构造器
        //           2、当使用构造器的时候无法改变所构造的对象类型
        System.out.println("想要使用不同的运算的时候就要创建不同的类，并且要明确知道该类的名字。\n" +
                "        那么这种重复的创建类的工作其实可以放到一个统一的类中去管理");

        // Topic-06: 简单工厂的构成
        // Factory：即工厂类， 简单工厂模式的核心部分，负责实现创建所有产品的内部逻辑；工厂类可以被外界直接调用，创建所需对象
        //
        // Product：抽象类产品， 它是工厂类所创建的所有对象的父类，封装了各种产品对象的公有方法，它的引入将提高系统的灵活性，
        //                     使得在工厂类中只需定义一个通用的工厂方法，因为所有创建的具体产品对象都是其子类对象
        //
        // ConcreteProduct：具体产品， 它是简单工厂模式的创建目标，所有被创建的对象都充当这个角色的某个具体类的实例。它要实现抽象产品中声明的抽象方法


        // 静态工厂模式

        // 抽象类产品
        //public abstract class calculation{
        //    private double value_1 = 0;
        //    private double value_2 = 0;
        //    protected abstract double getResult();
        //}
        // 具体产品
        //public class OperationAdd extends calculation{
        //    @Override
        //    protected double getResult(){
        //        return getValue1() + getValue2();
        //    }
        //
        //}


        /* 工厂类
        public class OperationFactory{
            public static Operation CreateOperation(String operation_type){
                Operation oper = null;
                switch (operation_type){
                    case "add":
                        oper = new OperationAdd();
                        break;
                    case "mul":
                    ....
                }
            }
        }

        调用
         Operation operationAdd = OperationFactory.createOperation("add");
         operationAdd.setValue1(1);
         operationAdd.setValue2(2)
         System.out.println(operationAdd.getResule());
         */

    }

    // FocusSubPoint-05：What is a main method?
    public static void MainMethod(){
        // Topic-01: 每一个类可以有一个main方法：是程序执行的主入口
        // Topic-02: main方法本质是一个静态方法：不需要构造类对象就可以调用
        // Topic-03: 更多的时候跟python的main一样，用来执行单元测试的
        System.out.println("你看 Math.pow() 不需要构造类对象就可以被调用，本质与我们的main一样就是个静态方法：用来做单元自测的" +
                "如果该类作为更大型应用程序的一部分，那么该类中的main方法则永远不会被执行");
    }

}

// 静态类
class EmployeeStatic{

    private static int nextID = 1;
    private static boolean flag = true;
    private final double id;
    public static final double PI = 3.141592653589793238;


    public EmployeeStatic(int s_id, boolean fg, double i_id){
        this.nextID = s_id; // 这个提示是因为：不应该使用实例访问静态成员变量，而是应该通过类访问比如不是staff[0].nextID而是EmployeeStatic.nexID
        flag = fg;
        this.id = i_id; //原来这里可以加this就是因为它是一个实例域的，上面的可以加但是会提示，原因就是

    }

    public int get_static_nexID(){
        return nextID;
    }

    public double get_instance_id(){
        return this.id;
    }

    // 静态方法：只需要访问静态变量
    public static boolean get_flag(){
        return flag;
    }



}
















































