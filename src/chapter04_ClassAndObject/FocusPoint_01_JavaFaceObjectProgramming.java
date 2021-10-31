package chapter04_ClassAndObject;

public class FocusPoint_01_JavaFaceObjectProgramming {
    public static void main(String[] args) {
        // Topic-01: 面向对象程序设计 OOP 是当今主流的程序设计范式 ==》 取代了20世纪70年代的"结构化" 过程程序设计开发技术
        // Topic-02: 面向过程开发本质是：算法+数据结构=程序 ==》第一步考虑怎么解决问题也就是算法 + 第二步考虑怎么存储这些数据
        //           所以这一类的程序员必须要：先考虑如何操作数据，然后再决定如何组织数据，以便于数据操作
        //           而OOP的本质是调换了这个顺序：将数据放在第一位，然后再考虑操作数据的算法
        // Topic-03: 如果处理规模较小的问题用面向过程比较简单，而处理大规模的问题面向对象会更加适用
        ClassIsWhat();
        ObjectIsWhat();


    }

    // FocusSubPoint-01：What is class?
    public static void ClassIsWhat(){
        // Topic-01: 类（class）就是制造对象的模板、蓝图！
        // Topic-02: 对象（object）是类的实例化
        // Topic-03: 封装（encapsulation，数据隐藏）：决不能让类中的方法直接的访问其他类的实例域（对象中的数据称为实例域，操纵数据的过程称为方法）
        //                                        程序仅通过对象的方法与对象数据进行交互；
        //                                        封装给对象赋予了"黑盒"特征，这是提高重用性和可靠性的关键；
        //                                        这也意味着一个类可以全面地改变存储数据的方式，只要仍旧使用同样的方法操纵数据，其他对象就不会知道或者介意所发生的的变化
        // Topic-04: 对象的状态（status）： 对于每个特定的类实例（对象）都有一组特定的实例域值。这些值的集合就是这个对象的当前状态（status）
        System.out.println(" I am the class !");

    }

    // FocusSubPoint-02：What is object?
    public static void ObjectIsWhat(){
        // Topic-01: 如果想使用好OOP必须清楚的掌握对象的3个主要特征：
        //           对象的行为（behavior）：可以对对象施加哪些操作，或可以对对象施加哪些方法？
        //           对象的状态（status）：当施加那些方法时，对象如何响应？
        //           对象的标识（identity）：如何辨别具有相同行为与状态的不同对象？
        //           例如：订单就是一个对象：（互相影响）
        //                             行为：增加订单、删除订单、修改订单、查询订单
        //                             状态：已下单、已送货、已签收
        //                             标识：订单号（id）

        System.out.println("object : behavior\n status\n identity");

    }

    // FocusSubPoint-03: How to identify class?
    public static void IdentifyClass(){
        // Topic-01: 需求中的名词提炼法：可能就是类
        // Topic-02: 需求中的动词：可能就是类的方法
        // Topic-03: 所谓的找名词与找动词只是一种经验：在创建类的时候哪些名词和动词的重要完全取决于个人的实际开发经验
        /*
        *  例如： 在订单处理系统中，商品被添加到订单中，订单被发送或者取消
        *  类：订单、商品
        *  方法：添加商品 发送订单 取消订单
        *  分析：添加商品应该作为订单类的一个方法
        * */
        System.out.println("oop 思路：首先从设计类开始 =》 然后往每个类中添加方法");

    }

    // FocusSubPoint-04：The relationship between class and class?
    public static void RelationshipBetweenClasses(){
        // Topic-01: 类与类之间的关系最常见的有3个
        //           R-01: 依赖: dependence（"uses-a"）  OrderClass --------> AccountClass 订单类依赖账户类是因为需要访问Account类来查看信用状态
        //           R-02: 聚合: aggregation（"has-a"）   GoodsClass 长实线<> OrderClass 商品类聚合于订单类是因为一个订单对象包含一些商品对象
        //           R-03: 继承: inheritance ("is-a")    VipOrderClass  长实线|> OrderClass 特殊与一般的关系

        //           R-04: 接口实现: implement                       虚线|>
        //           R-05: 关联: association                        长实线
        //           R-06: 直接关联 association-directly             长实线>

        System.out.println("R-01: 如果一个类A的方法操纵另一个类B的对象：我们就说一个类A依赖类B\n" +
                "应该尽可能的把相互依赖的类减至最少：如果类A不知道类B的存在，它就不关心类B的任何变化=》B的改变不会导致A的任何bug=》此为低耦合！");
        System.out.println("R-02: 如果类A的对象包含类B的对象：我们就说类B聚合于类A");
        System.out.println("R-03: 如果类A是类B的扩展，也就是类A不仅包含了类B的所有，还拥有自己额外的：我们就说类A继承于类B");
        // TODO: 2021/10/31  R-04\R-05\R-06 未学习
    }
}
