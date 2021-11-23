package com.ragnor.corejava.chapter06_InterfaceAndlamdbaAndInnerClass;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FocusPoint_01_JavaDefineInterfaceAndUseage {
    public static void main(String[] args) {
        JavaInterfaceConcept();
        CharacteristicsOfInterfaceInJava();
        DifferenceBetweenInterfaceAndAbstractClass();
        DefineJavaInterfaceStaticMethod();
        DefineDefaultMethodInJavaInterface();
        ResolveDefaultMethodConflictsInJavaInterface();
    }

    // FocusSubPoint-01: What is the concept of interface in java？
    public static void JavaInterfaceConcept(){
        // Topic-01: java中接口不是类，而是对类的一组需求描述
        // Topic-02: java接口绝不能含有实例域，在java8之前也不能在接口中实现方法==》这些方法不能引用实例域，因为接口没有实例
        // Topic-03: java中让类实现一个接口的步骤-2步
        //          step-01: 将类声明为实现给定的接口
        //          sep-02:  对接口中的所有方法进行定义

        // Topic-04: 实现接口的类格式要收到接口的约束哈
        RelizeInterface rif = new RelizeInterface();
        OfferNums ons = new OfferNums(2000, 2000);
        System.out.println(rif.comparaTo(ons));

    }

    // Topic-04: 定义泛型类型的接口
    // Topic-05: 接口中所有的方法自动地属于public==》因此在接口中声明方法时，不必提供关键字public
    public interface Comparable<T>{
        int comparaTo(T other);  // parameter has type T
    }

    public static class RelizeInterface implements Comparable<OfferNums>{  // 这个<>要注意啊

        @Override
        public int comparaTo(OfferNums other) {
            return Integer.compare(other.salary, other.lucky);  // the value 0 if x == y; a value less than 0 if x < y; and a value greater than 0 if x > y

        }
    }

    public static class OfferNums{
        private final int salary;
        private final int lucky;
        private final String message = "hello ragnor";

        public OfferNums(int salary, int lucky){
            this.salary = salary;
            this.lucky = lucky;
        }

        public int getSalary() {
            return salary;
        }

        public int getLucky() {
            return lucky;
        }
    }

    // FocusSubPoint-02: What are the characteristics of the interface in java?
    public static void CharacteristicsOfInterfaceInJava(){
        // Topic-01: 接口不是类，不可以被new运算符实例化为一个接口对象： x = new Comparable()是错误的
        // Topic-02: 可以声明接口变量，但是接口变量必须引用实现了接口的类对象：Comparable x; x = new RelizeInterface();
        Comparable x;
        x = new RelizeInterface();

        // Topic-03: instanceof检查一个对象是否属于某个特定类 ==》 instanceof也可以检查一个对象是否实现了某个特定的接口
        RelizeInterface check_ = new RelizeInterface();
        if (check_ instanceof Comparable){
            System.out.println("yes! check_ is implecement of interface Comparable!");
        }

        // Topic-04: 接口也是可以被扩展的：允许存在多条从具有较高通用性的接口到较高专用性接口的链: SecondRelizeInterface
        OfferNums offns_ = new OfferNums(1000, 2000);
        SecondRelize srl_ = new SecondRelize();
        System.out.println(srl_.getMsg(offns_));
        System.out.println(srl_.comparaTo(offns_));

        // Topic-05: 在接口中不能供应包含实例域或者静态的方法，但是可以包含常量
        srl_.getDreamSalary(); //SecondRelizeInterface 中的 Double DreamSalary = 500000.00;

        // Topic-06: 接口中的方法都自动的被设置为public一样 ==》接口中的域将被自动设置为public static final

        // Topic-07: 接口中是不允许private 与 protected等修饰符的

        // Topic-08: 尽管每个类只能够拥有一个超类，但是却可以实现多个接口。==》这就为定义类的行为提供了极大的灵活性
        //           ==》比如你如果希望自己设计的类拥有克隆和比较的能力：实现这两个接口就可以了：class Ragnor implecements Cloneable, Comparable

    }

    public interface SecondRelizeInterface<M> extends Comparable<OfferNums>{  // 你能找到下面的问题吗，为什么我实现了接口的方法，还说我没实现，你再试着把这个T改成OfferNums对象
        String getMsg(M other);
        Double DreamSalary = 500000.00;  // a public static final constant

    }

    public static class SecondRelize implements SecondRelizeInterface<OfferNums> {
        @Override
        public String getMsg(OfferNums other) {
            return other.message;
        }

        @Override
        public int comparaTo(OfferNums other) {
            return Integer.compare(other.salary, other.lucky);
        }

        public double getDreamSalary(){
            return DreamSalary;
        }



    }

    // FocusSubPoint-03: Interface and abstract class？
    public static void DifferenceBetweenInterfaceAndAbstractClass(){
        // Topic-01: 想一个问题：java中的抽象目前有两种：abstract抽象类 + interface接口，有什么区别呢？
        //           区别就是：
        //              类只能扩展extends 一个抽象类 ==》 这个叫多继承multiple inheritance，java不支持 ，python支持mro c3算法
        //              但是类可以implements 多个接口  ==》 这个是java使用后的，既提供了多重继承的大多数好处，同时还能避免多车用继承的复杂性与低效性

        System.out.println("类只能扩展extends 一个抽象类  VS  但是类可以implements 多个接口");
    }

    // FocusSubPoint-04: java的接口中的静态方法怎么定义？
    public static void DefineJavaInterfaceStaticMethod(){
        // Topic-01: 在java SE8之前都是：将静态方法放在伴随类中==》例如标准库：Collection接口/Collections伴随类 Path/Paths

        // Topic-02: 目前通用的做法都是将静态方法放在伴随类中==》但是java SE8之后，接口中可以增加静态方法==》人们认为这有违将接口作为抽象规范的初衷

        // Topic-03: 但是在接口中实现静态方法可以直接替代伴随类：Ragnor_Path
        Paths.get("ragnor","niuniu","ning");

        Ragnor_Path.getRagnorPath("ragnor","niuniu","ning");  // 说明Path接口可以取代Paths伴随类中的这一部分作用

    }

    public interface Ragnor_Path<T>{
        static Path getRagnorPath(String first, String... more){
            return FileSystems.getDefault().getPath(first, more);
        }

        int getPassCode(T object);

    }

    // FocusSubPoint-05: How to define the default method in java Interface?
    public static void DefineDefaultMethodInJavaInterface(){
        // Topic-01: java中可以为接口方法提供一个默认实现，必须用default修饰符标记这样一个方法

        // Topic-02: 你接口中声明方法肯定需要被实现，但是default修饰的方法什么都不用做: RelizeDefaultInterface类中不用实现default void mouseClick(){};

        // Topic-03: 实现和这个接口的程序员只需要为他们真正关心的事件覆盖相应的监听器

        // Topic-04: 默认方法的一个重要用法就是"接口演化" （interface evolution）==》比如我新增一个接口的方法，你要是不实现是不是就报错了，那以前的代码不也都报错了
        //           将方法实现为一个默认方法就可以解决这两个问题：旧类代码的正常编译  +  新代码的功能扩充

        RelizeDefaultInterface rdi_ = new RelizeDefaultInterface();
        rdi_.mouseClick();  // 实例调用的是扩充的default方法
    }

    public interface defaultInterface<T>{
        default void mouseClick(){
            System.out.println("Interface default method!");
        }

        int secrityId(T other);

        default int defaultSecurity(OfferNums other) {
            return other.salary;
        }
    }

    public static class RelizeDefaultInterface implements defaultInterface<OfferNums>{

        @Override
        public int secrityId(OfferNums other) {
            return 0;
        }
    }

    // FocusSubPoint-06：How to resolve default method conflicts in java Interface？
    public static void ResolveDefaultMethodConflictsInJavaInterface(){
        // Topic-01: 如果先在一个接口中将一个方法定义为默认方法，然后又在超类或另一个接口中定义了同样的方法==》这就是接口的默认方法冲突

        // Topic-02: java的处理规则如下
        //           1、超类优先：如果超类提供了一个具体方法，那么同名而且有相同参数类型的默认方法会被忽略：1个超接口，一个继承超级口的子接口，2个同名的方法
        //           2、接口冲突：如果一个超接口提供一个默认方法，另外一个接口提供了一个同名而且参数类型相同（无论是否是默认方法）的方法，必须覆盖这个方法解决冲突

        RealizeDefaultMethodConflictOverride rdmco_ = new RealizeDefaultMethodConflictOverride();
        OfferNums off__ = new OfferNums(10000, 20000);
        System.out.println(rdmco_.defaultSecurity(off__));  // 20000  ==》229行RealizeDefaultMethodConflictOverride类中的defaultSecurity

        // Topic-03: 如果一个类中同时继承了两个接口，这连个接口中提供了两个签名一样但是实现不一样的方法，并不是从中随便选一个而是让程序员解决
        // return defaultInterface.super.defaultSecurity(other);
        // return DefaultMethodConflict.super.defaultSecurity(other);

        // Topic-04: 如果一个类扩展extends了一个超类，同时实现了一个接口==》注意并从超类和接口中继承了相同的方法==》这种情况下只会考虑超类方法，接口的所有默认方法都会被忽略
        //           ==》这就是"类优先"的原则 ==》"类优先的原则"可以确保与java SE7兼容==》如果为一个接口增加默认方法，这对于有这个默认方法之前能正常工作的代码不会造成任何影响
        //           ==> 所以千万不要让一个默认方法重新定义Object类中的某个方法，比如toString 或者 equals 因为"类优先"你写了也没用
        System.out.println("所以千万不要让一个默认方法重新定义Object类中的某个方法，比如toString 或者 equals 因为\"类优先\"你写了也没用");

    }

    public interface DefaultMethodConflict {
        default void mouseClick() {
            System.out.println("DefaultMethodConflict interface mouseClick!");
        }

        default int defaultSecurity(OfferNums other) {
            return other.lucky;
        }
    }

    public static class RealizeDefaultMethodConflictOverride implements defaultInterface<OfferNums>, DefaultMethodConflict {


        @Override
        public void mouseClick() {

        }

        @Override
        public int secrityId(OfferNums other) {
            return 0;
        }

        @Override
        public int defaultSecurity(OfferNums other) {           // 你把这个类注释掉就发现了，本来default类型的不用实现，这里因为继
                                                                // 承的两个接口中都有defaultSecurity方法，不知道要实现哪一个，
                                                                // 就让你自己写一个同名的defaultSecurity方法，然后自己指定冲突方法中的一个
            return defaultInterface.super.defaultSecurity(other);  // 自己选继承的那个父接口的同名接口 这里就是1000
//            return DefaultMethodConflict.super.defaultSecurity(other);
        }
    }

}
