package com.ragnor.corejava.chapter05_Inherit;

public class FocusPoint_01_JavaDefineClass_SuperClass_SubClass {
    public static void main(String[] args) {
        System.out.println("你不能调用不存在的东西。由于您尚未创建对象，因此非静态方法尚不存在。静态方法（根据定义）始终存在。");
        // Topic-01: Inherit exist obviously "is-a"特征： 比如如果员工一直是员工、经理一直是经理 那其实经理也是员工
        // Topic-02: 有一个用来判断是否应该设计为继承关系的简单规则： is-a规则.equals("置换法则") ==》程序中出现超类对象的任何地方都可以用子类对象进行置换
        DefineSubclass();
        OverrideParentMethod();
        DefineSubclassConstructor();
        PolymorphismAndDynamicBinding();
        InheritanceHierarchyAndInheritanceChain();
        RealizePolymorphism();
        UnderstandProcessOfJavaMethodCall();
        StopInheritanceByFinal();
        ForceConverseSuperclassToSubClass();
        ShowAbstractCallInstance(); // 为了这里的调用 分别把 ShowAbstractCallInstance DefineAbstractClass Teacher Student都变成了static
        WhatIsProtectedAccess();

    }

    // FocusSubPoint-01：How to define subclass？
    public static void DefineSubclass(){
        // part-01: Animal - Dog
        Animal monkey = new Animal(18, "red");
        System.out.println(monkey.getAge());
        // 子类有父类不一定有；父类有子类也有
        Dog bigdog = new Dog(19, "blue", 18000);
        System.out.println(bigdog.getAge());
        System.out.println(bigdog.getSalary());
    }
    public static class Animal {
        private int age;
        private final String color;

        public Animal(int age, String color){
            this.age = age;
            this.color = color;
        }

//        public Animal(){} // 无参构造器的定义

        public int getAge(){
            return this.age;
        }

        public void setAge(int newAge){
            this.age = newAge;
        }

        public String getColor(){
            return this.color;
        }

    }
    public static class Dog extends Animal{
        // Topic-01: java中使用关键字extends表示继承：extends 表明正在构造的新类派生于一个已存在的类
        //           : 已存在的类称为超类（superclass）、基类（base class）、或父类（parent class）
        //           : 新类称为子类（subclass）、派生类（derived class）、或孩子类（child class）

        // Topic-02: java中所有的继承都是公有继承，没有c++中的私有继承和保护继承
        private double salary;

        // Topic-03: 我们复习一个知识点==》由于父类定义了public Animal构造方法，当类显示定义带参构造函数，而没有显示定义无参的情况，无参构造消失，
        // 而子类Dog继承了父类Animal==》父类Animal没有默认的构造函数==》故 There is no default constructor available in
        // 这个里面子类Dog的构造方法，想扩充实例域变量初始化的方式就是下面，我自己猜出来的哈哈=》类似python的init super()
        public Dog(int age, String color, double salary) {
            super(age, color); // 这是==》调用超类Animal中含有age、color参数的构造器的简写形式
            this.salary = salary;
        }

        public double getSalary(){
            return this.salary;
        }

        public void setSalary(double newSalary){
            this.salary = newSalary;
        }

        // Topic-03: 在通过扩展超类定义子类的时候：仅需要指出子类与超类的不同之处==》
        //  因此在设计类的时候，应该将通用的方法放在超类中，而将具有特殊用途的方法放在子类，
        //  这种通用的功能放到超类的做法，在OOP中十分普遍
    }

    // FocusSubPoint-02：How to override(重写) parent method？
    public static void OverrideParentMethod(){
        // part-02: VipDog
        // 重写父类的方法
        VipDog vpdog = new VipDog(29, "green", 18000, 10000);
        System.out.println(vpdog.getSalary());

    }
    public static class VipDog extends Dog {
        /**
         * VipDog 的 salary比普通的高一个奖金 bonus
         * */
        private double bonus;

        public VipDog(int age, String color, double salary, double bonus) {
            super(age, color, salary);
            this.bonus = bonus;
        }

        public void setBonus(double newBonus){
            this.bonus = newBonus;
            }

        // Topic-01: 这个才是 override 方法=》因为父类Dog的getSalary方法跟子类的不一样，子类VipDog的要 + 奖金bonus
        public double getSalary(){
            // Topic-02: super与this引用并不是类似的概念
            // ==》this是一个对象的引用，是可以赋给另一个对象变量的
            // ==》但是super不是一个对象的引用，不能将super赋给另一个对象变量
            // ==》它只是一个指示编译器调用超类方法的特殊关键字

            return super.getSalary() + this.bonus;  // 不能写成return getSalary() + this.bonus =》无限次自己调自己，直到程序崩溃
        }

        // Topic-03: 在子类中可以增加域、增加方法或者覆盖超类的方法==》但是绝对不能删除继承的任何域和方法。

        }

    // FocusSubPoint-03：How to define subclass's constructor？
    public static void DefineSubclassConstructor(){
        Elephant elephant = new Elephant(18, "yellow", 8);
        System.out.println(elephant.getTeeth());
    }
    public static class Elephant extends Animal{
        // Topic-01: 子类Elephant的构造器的写法格式如下
        private double teeth;

        public Elephant(int age, String color, double teeth){
            // Topic-02: 使用super调用构造器的语句必须是子类构造器的第一条语句:
            //          Call to 'super()' must be first statement in constructor body
            super(age, color);
            this.teeth = teeth;
        }

        public double getTeeth(){
            return this.teeth;
        }

        // Topic-03: 如果子类的构造器没有显示的调用超类的构造器，则将自动的调用超类默认（没有参数）的构造器
        //          ==>如果超类没有不带参数的构造器 + 在子类的构造器中又没有显示的调用超类的其他构造器
        //          ==》java编译器报错：There is no default constructor available in

        // Topic-04: 关键字 this VS 关键字 super
        //                 this: 1、引用隐式参数；
        //                       2、调用该类其他的构造器；
        //                 super:
        //                       1、调用超类的方法；
        //                       2、调用超类的构造器；
    }

    // FocusSubPoint-04：What is the level of inheritance？
    public static void PolymorphismAndDynamicBinding(){
        // part-03:Dog-VpDog
        // Topic-01:虚拟机知道实际引用的对应类型==》因此可以正确的调用相应的方法
        // 1、一个对象变量可以指示多种实际类型的现象被称为：多态（polymorphism）
        // 2、java程序在运行时能够自动的选择调用哪个方法的现象称为动态绑定（dynamic binding）
        //    在java中，不需要将方法声明为虚拟方法。动态绑定是默认的处理方式，如果不需要让一个方法具有虚拟特征，可以将它标记为final
        // 3、虚拟机知道实际引用的对应类型==》因此可以正确的调用相应的方法
        Dog[] dogs = new Dog[3]; // 2=>3
        Dog bigdog = new Dog(19, "blue", 18000);
        VipDog vpdog = new VipDog(29, "yellow", 18000, 10000);
        TidiDog tidiDog = new TidiDog(88, "pink", 40000, 10000); // add
        dogs[0] = bigdog;
        dogs[1] = vpdog;
        dogs[2] = tidiDog;  // add


        for (Dog a : dogs){
            System.out.println(a.getAge() + " " + a.getSalary());
        }

        // Topic-02: 动态绑定有一个非常重要的特征：无需对现存的代码进行修改，就可以对程序进行扩展:
        //              比如新增一个TidiDog类继承于Dog类也有getSalary方法，上面的代码具有add标记的就是扩展的

    }
    public static void InheritanceHierarchyAndInheritanceChain(){
        // Topic-01: java中不支持多继承，但是java中可以通过Interface实现多继承的功能
        // Topic-02: Animal 类派生出 Dog类、Elephant类; Dog类又派生出VipDog类
        //           Animal 类是一个公共的超类： Dog类、Elephant类、Dog类、VipDog类集合被称为继承层次
        // Topic-03: 从VipDog=>Dog=>Animal这个链路叫做VipDog的继承链
        System.out.println("子类 Dog与Elephant本质上来讲除了父亲一样，但彼此并没有任何关系，谁死都不影响彼此");
    }
    public static class TidiDog extends Dog{
        private double lovelymonkey;

        public TidiDog(int age, String color, double salary, double lovelymonkey){
            super(age, color, salary);
            this.lovelymonkey = lovelymonkey;
        }

        public double getSalary(){
            return super.getSalary() + lovelymonkey;
        }


    }

    // FocusSubPoint-05：How to Realize Polymorphism？
    public static void RealizePolymorphism(){
        // Topic-01: java中所谓多态指的是：对象变量是多态的
        //                             就是说一个Animal对象变量 可以引用一个 Animal类对象
        //                             也可以引用一个Animal类的任何一个子类的对象（Dog、VipDog、Elephant）
        VipDog vipDog = new VipDog(18, "red", 1000, 800);
        Dog[] normalDog = new Dog[2];
        normalDog[0] = vipDog;
        normalDog[1] = new Dog(18, "black", 1000);

        // Topic-02:你看一个Dog对象变量normalDog[index] 可以引用 Dog类对象 也 可以引用子类vipDog类的对象
        // normalDog[0] 与 vipDog 引用的是同一个对象：
        //              但是编译器将normalDog[0]看成Dog对象
        //              把vipDog看成是VipDog对象

        for (Dog dg: normalDog){
            System.out.println(dg.getSalary()); // 因为normalDog[0] 与 vipDog 引用的是同一个对象所以调用normalDog[0].getSalary是VipDog的: 动态绑定
            //dg.setBonus()                     // 又因为normalDog[0] 本质是一个Dog对象所以并不能调用setBonus()方法
                                                // normalDog[0].getSalary实际调用的不是Dog类下的getSalary
                                                // 而是VipDog的getSalary原因是虚拟机知道实际引用的对应类型==》因此可以正确的调用相应的方法
        }
        // Topic-03: java中子类数组的引用可以转换成超类数组的引用，无需强制转换
        //           所有的vipdog肯定都是dog
        //           但是所有的dog不一定是vipdog明白吗
        //           什么类型的元素就存放到什么类型的数组容器中
        // case-01: ok

        VipDog[] vipDogs = new VipDog[2];
        Dog[] notvipDogs = vipDogs;
        // case-02: error
        // VipDog[] vvipDogs = notvipDogs; // wrong

    }

    // FocusSubPoint-06：How to understand the process of java method call？
    public static void UnderstandProcessOfJavaMethodCall(){
        // Topic-01: java是如何在对象上应用方法调用的
        //                     : vipdog.getSalary()
        // step-01: 编译器查看对象的声明类型和方法名==》来获取所有可能被调用的候选方法
        //                     : vipdog是VipDog类实例化的；
        //                     : VipDog类中寻找名为getSalary()方法，但是参数可能不对(getSalary(int newSalary)、getSalary(double newSalary)、getSalary())
        //                     : 在VipDog的超类中寻找属性为public（因为超类的私有方法是不可访问的）且名为getSalary()的方法

        // step-02: 编译器查看调用方法时提供的参数类型==》来获取需要调用的方法名与参数类型：方法的名字+参数称为方法的签名
        //                     : 如果在所有名为getSalary()的方法中存在一个与提供的参数完全匹配的方法就选这个==》这个过程叫做重载解析（pverloading resolution）
        //                     : tips：由于java允许类型转换（int==》double； Manager ==》Employee等等）所以重载解析的过程可能会很复杂

        // step-03: 编译器调用方法的方式选择==》生成指令
        //                     : static binding（静态绑定）如果是private方法、static方法、final方法、constructor构造器=》编译器将可以准确的知道应该调用哪个方法
        //                       ==》我们将这种调用方式称为静态绑定
        //                     : dynamic binding (动态绑定) 如果调用的方式依赖隐式参数的实际类型 + 在运行时实现动态绑定==》这种方式就是动态绑定

        // step-04: 程序运行并且采用动态绑定方法时，虚拟机搜索与vipdog所引用对象的实际类型最合适的那个类的方法
        //                     : 因为VipDog是Dog的子类，VipDog类中有getSalary()那就直接调这个了否则将在超类中寻找，以此类推
        //                     : 每次调用方法都要搜索==》时间开销非常大==》虚拟机预先为每个类创建一个方法表（method table）==》列出所有的方法的签名和实际调用的方法
        //                              Dog:
        //                                  getSalary -> Dog.getSalary()
        //                                  ...
        //                              VipDog:
        //                                  getSalary -> VipDog.getSalary()
        //                                  ...
        //                      vipdog.getSalary()
        //                      : 1) 首先，虚拟机提取vipdog的实际类型的方法表=》既可能是VipDog、Dog的方法表，也可能是Dog类的其他子类的方法表
        //                      : 2) 接下来，虚拟机搜索定义getSalary签名的类=》这个时候虚拟机已经知道应该调用哪个方法
        //                      : 3) 最后，虚拟机调用方法



    }

    // FocusSubPoint-07：How to stop inheritance: the use of final classes and methods？
    public static void StopInheritanceByFinal(){
        // Topic-01: 如果丁克家庭：也就是说希望某个类无法定义子类，也就是不允许扩展==》不允许扩展的类被称为final类
        // Topic-02: 类中的特定方法也可以被声明为final==》如果这样声明子类就不能覆盖这个方法（final类中的所有方法自动地称为final方法，但是不包括域）
        // Topic-03: 域也可以被声明为final==》对于final域，构造对象之后就不允许改变他们的值了（如果把一个类声明为final，类中的所有方法会自动成为final，而不包括域）
        // Topic-04: 将类、方法声明为final主要目的是：确保它们不会在子类中改变语义
        //                          Calendar类中的getTime 和 setTime方法都被声明为final==》Calendar类的设计者负责实现Date类与日历状态之间的转换，不允许子类处理这些问题
        //                          String类也是final类==》不允许任何人定义String的子类==》如果有一个String的引用那一定是String类的对象而不可能是其他

        final String secret = "yeqingning"; // 函数内部的变量

        // Topic-05: 在早期的java中，有些程序员为了避免动态绑定带来的系统开销而使用final关键字
        //           如果一个方法没有被覆盖并且很短==》编译器就能够对它进行优化处理==》这个过程叫做 内联（inlining）
        //           eg: 内联调用e.getName() 会被替换为 访问e.name域==》意义重大,CPU在处理调用方法的指令时使用的分支转移会扰乱预取指令的策略，故开销大
        //               但是如果getName在另一个类中被覆盖重写==》编译器就无法知道覆盖的代码将会做什么操作==》无法内联，新的虚拟机采用的即时编译器对比传统的编译器更优，会判断并取消内联
        System.out.println(secret);
    }

    // FocusSubPoint-08：How to implement coercive type conversion(强制类型转换)？
    public static void ForceConverseSuperclassToSubClass(){
        // Topic-01: 强制类型转换：小房子 与 大房子搬家：
        //              如果被强制的对象是类型那就是强制类型转换 ==》因为需要同样的数据不同的数据类型
        //              如果被强制的对象是类的对象引用==》因为需要在暂时忽视对象的实际类型之后使用对象的全部功能
        // Topic-02: 将一个子类的引用赋给一个超类变量==》编译器是允许的==》继承链从下到上 vipdog 一定是个 dog
        //           将一个超类的引用赋给一个子类变量必须进行强制转换==》继承链从上到下  dog 不一定是 vipdog
        Dog[] staff_dog = new Dog[3];

        VipDog vpdog = new VipDog(18, "red", 1000, 800);
        Dog nordog = new Dog(19, "black", 600);
        Dog nordog2 = new Dog(20, "yellow", 600);

        staff_dog[0] = vpdog;
        staff_dog[1] = nordog;
        staff_dog[2] = nordog2;

        // Topic-3: 将子类的引用赋值给超类变量==》继承链从下往上赋是允许的
        Dog nordog3 = staff_dog[0];

        // Topic-4: 将超类的引用赋值给子类变量==》继承链式从上往下的是需要强制转换
        //          case1: staff_dog[0]尽管是一个Dog类的对象，但是它引用了VipDog类的实例 ==》 强制转换通过
        //          case2: staff_dog[1]是一个Dog类的对象，而且也没有引用VipDog的实例==》ClassCastException（类转换异常）
        VipDog boss = (VipDog) staff_dog[0];
        VipDog boss2 = (VipDog) staff_dog[1];  // ClassCastException todo:这里触发异常哈

        // Topic-5: 为了避免（继承链上进行向下的类型转换 + "谎报"有关对象包含的内容 ==》触发ClassCastException）
        //                  （注意：只要没有捕获ClassCaseException异常，程序就会终止执行，少用类对象转换与instanceof）
        if (staff_dog[1] instanceof VipDog){
            VipDog boss3 = (VipDog) staff_dog[1];
        }else {
            System.out.println("staff_dog[1] is not instanceof VipDog cant coverent normal dog to vipdog");
        }

        // Topic-6: 综上：
        //              1、只能在继承层次内进行类型转换
        //              2、在将超类转换为子类之前，应该使用 instanceof进行检查

        // Topic-7: 实际上通过类型转换调整对象的类型并不是一种好的做法：
        //              case1: 两个类都需要 getSalary()方法==》java的动态绑定机制会帮我们自动的找到响应的方法
        //              case2: 只有vipdog才有setBonus()方法==》而由于什么原因你需要通过dog对象调用setBonus()方法
        //                     ==》 第一反应是应该检查超类Dog的设计是不是合理 ==》重新设计 添加setBonus()才是正确的选择

    }

    // FocusSubPoint-09：How to define abstract class？
    public static void ShowAbstractCallInstance(){
        Student s_1 = new Student();
        s_1.showAbstractCallInstance();  // 调用抽象类的非抽象方法
    }

    public static abstract class DefineAbstractClass {
        // Topic-01: 记着这句话：如果自下而上在类的继承机构中上移，位于上层的类更具有通用性，甚至可能更加抽象
        // ==》从某种角度来看，祖先类更加通用，通常我们将它作为派生其他类的基类，而不作为想使用的特定的实例类

        // Topic-02: java中使用abstract关键字来表示抽象：所谓抽象既有抽（向上）又有象（就是无需实现）

        public abstract String getDescription(); // no implementation required

        // Topic-03: 昨天python定义抽象类的时候也是这样的，python定义的抽象类里面也有对应的具体的数据和具体方法
        //     1: 为了提高程序的清晰度，包含一个或多个抽象方法的类本身必须被声明为抽象的。python的 meta_class = ABCMenta Vs java 的abstract
        //     2: 除了抽象方法之外，抽象类还可以包含具体数据(下面的name)和具体方法（下面的getName）。
        //        但是很多程序员认为，在抽象类中不能包含具体方法==》他们建议尽量将通用的域和方法（不管是否是抽象的）放在超类（不管是否是抽象类）中。
        private String name;

        //  public static String getName(){  // 3、回顾static： 属于类但是不属于类对象的属性或者方法，所以加了static下面的不能使用this
        //      return this.name;
        //}

        public String getName(){
            return name;
        }

        // Topic-04: 抽象方法充当着"占位"的角色，它们的具体实现在子类中。==》扩展抽象类有两种选择：
        //          一：在抽象类中定义部分抽象类方法或者不定义抽象类方法 ==》这样就必须将子类也标记为抽象类
        //          二：定义全部的抽象方法，这样一来，子类就不是抽象的了。


        // Topic-05: @Override标签的好处：
        //
        //      1.作为注释，帮助自己检查是否正确的复写了父类中已有的方法
        //      2.便于别人理解代码
        //      3.编译器可以给你验证@Override下面的方法名是否是你父类中所有的，如果没有则报错．

        // Topic-06: 抽象类的变量可以引用非抽象子类的对象
        public void showAbstractCallInstance(){
            DefineAbstractClass[] staff = new DefineAbstractClass[2];
            staff[0] = new Student();
            staff[1] = new Teacher();

            for(DefineAbstractClass p: staff){
                System.out.println(p.getDescription()); // 1、这里的p 就是抽象类 DefineAbstractClass 的对象变量
                                                        // 2、调用的p.getDescription()并不是自己的没有定义的方法 public abstract String getDescription();
                                                        // 3、而是引用具体的子类对象 Student Teacher的getDescription()方法
            }


        }



    }

    public abstract class Son extends DefineAbstractClass{ // 因为继承的父类是抽象类，父类中的抽象方法getDescription()是要被实现的，
        // 你这里加了abstract说明也是个抽象类，也等着被实现呢，那就不报错了

        // @Override
        // public String getDescription() {  // 这种写法就是可以不加abstract修饰类，而通过重写父类的抽象方法实现 该类的非抽象化，
        //                                       因此Son类中的全部方法都是非抽象的，这个类不再是抽象类。
        //     return null;
        // }

        private int age;

        public int getAge(){
            return this.age;
        }
    }

    public static class Teacher extends DefineAbstractClass{

        @Override
        public String getDescription(){
            return "i am a teacher";
        }

    }

    public static class Student extends DefineAbstractClass {

        @Override
        public String getDescription(){
            return "i am a student";
        }
    }

    // FocusSubPoint-10：What is protected access？
    public static void WhatIsProtectedAccess(){
        // Topic-01: 记着这几个关键词
        //          1、仅对本类可见 ---- private
        //          2、对所有类可见 ---- public
        //          3、对本包和所有子类可见 ---- protected
        //          4、对本包可见 ---- 默认，不需要修饰符

        // Topic-02: 按照习惯，我们最好将类中的域标记为private， 而方法标记为 public ==》而任何声明为private的内容对其他类都是不可见的。
        //           ==》如果需要限制某个方法的使用==》可以声明为protected ==》这样子类得到信任可以使用该方法，而其他类没有得到信任则不能使用
        ProtectedSubclass pds = new ProtectedSubclass();
        System.out.println(pds.echoFatherName());


    }
    private static class showProtected{
        private float salary;
        protected String name = "father-default";

        protected String getName(){
            return name;
        }
    }
    public static class ProtectedSubclass extends showProtected{
        private final String code = "secret-code";

        public String echoFatherName(){
            return this.getName() + "~~" + code;
        }

        public String getCode() {
            return this.code;
        }
    }
}


