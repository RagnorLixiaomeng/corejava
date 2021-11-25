package com.ragnor.corejava.chapter06_InterfaceAndlamdbaAndInnerClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class FocusPoint_04_JavaInnerClass {
    public static void main(String[] args) {
        // Topic-02: 内部类是定义在另一个类中的类,内部类的使用场景有哪些？
        /*
        *   1、内部类既可以访问自身的数据域，也可以访问创建它的外围类对象的数据域
        *   2、内部类可以对同一个包中的其他类隐藏起来。
        *   3、当想要定义一个回调函数且不想编写大量代码时，使用匿名（anonymous）内部类比较便捷
        *   4、内部类的对象总有一个隐式引用，他指向了创建它的外部类对象
        *   5、内部类中声明的所有静态域都必须是final ==》因为我们希望一个静态域只有一个实例，不过对于每个外部对象，会分别有一个单独的内部类实例==》如果这个域不是final，它可能就不是唯一的。
        *   6、内部类不能有static方法：java语言规范对这个限制没有任何解释，也可以允许有静态犯法，但只能访问外网类的静态域和方法，很明显java设计者觉得这样带来的好处是弊大于利
        * */
        UseInnerClassAccessObjectState();
        JavaInnerClassSpecialSyntaxRules();
        IsInnerClassUsefulNecessarySafe();
        JavaLocalInnerClass();
        AccessVariablesFromExternalMethod();
        ImplementAnonymousInnerClass();
        ImplementStaticInnerClass();

    }

    // FocusSubPoint-01：How do I use an internal class to access object state in java?
    public static void  UseInnerClassAccessObjectState(){
        // Topic-01: 使用内部类访问外部对象的状态==》就是内部类中的方法可以引用调用这个方法的对象数据域，内部类既可以访问自身的数据域，也可以访问创建它的外围类对象的数据域
        UpdatedClock clk_ = new UpdatedClock(10000, false);
        clk_.start();

        // Topic-02: 这里的TimePrinter就是内部类，外部类就是UpdatedClock ==》把TimePrinter类声明为私有的==》这样只有UpdatedClock的方法才可以构造TimePrinter对象
        //           只有内部类可以是私有类，而常规类值可以具有包可见性，或者公有可见性

        // Topic-03: 为什么使用内部类可以访问外部对象的状态==》因为内部类对象总有一个隐式引用，它指向了创建它的外部类对象==》这个引用在内部类的定义中是不可见的==》
        //           为了说明这个概念，我们将外部类的引用称为outer：
        //           ==》 如果理解不了就记着一句话：内部类对象拥有一个对外围类对象的引用

        /*
        *
        *   private class TimePrinter implements ActionListener{
        *
        *   public TimePrinter(UpdatedClock clock){  // automatically genetated code
                outer = clock;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone, the time is " + new Date());
                if (outer.beep) Toolkit.getDefaultToolkit().beep();  // 令人惊讶：TimePrinter类中并没有实例域或者名为beep的变量，
                // 取而代之的是beep引用了创建TimePrinter的UpdatedClock对象的域 ==》内部类既可以访问自身的数据域，也可以访问创建它的外围类对象的数据域
            }
        }
        * */
    }

    public static class UpdatedClock{
        private final int interval;
        private final boolean beep;

        public UpdatedClock(int interval, boolean beep){
            this.interval = interval;
            this.beep = beep;
        }

        public void start(){
            ActionListener listener = new TimePrinter();
            Timer t = new Timer(interval, listener);
            t.start();

            // below aim to keep program running until user selects "ok"!
            JOptionPane.showMessageDialog(null, "quit?");
            System.exit(0);
        }

        private class TimePrinter implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone, the time is " + new Date());
                if (beep) Toolkit.getDefaultToolkit().beep();  // 令人惊讶：TimePrinter类中并没有实例域或者名为beep的变量，
                // 取而代之的是beep引用了创建TimePrinter的UpdatedClock对象的域 ==》内部类既可以访问自身的数据域，也可以访问创建它的外围类对象的数据域
            }
        }
    }

    // FocusSubPoint-02：What are the special syntax rules for internal classes in java?
    public static void JavaInnerClassSpecialSyntaxRules(){
        // Topic-01: 上面讲述了内部类有一个外围类的引用outer==》那只是为了便于理解，实际上使用外围类引用的正规语法还要更复杂一些
        //             OuterClass.this 表示 外围类引用
        //             outerObject.new InnerClass(construction parameters) 表示 内部对应的构造器:
        UpdateUnderstandClock usc_ = new UpdateUnderstandClock(10000, false);
        usc_.start(); // 注释掉下面才能运行
        // Topic-02：在外围类的作用域之外，可以这样引用内部类
        UpdateUnderstandClock.timePrinter listener = usc_.new timePrinter();
        Timer t = new Timer(1000, listener);
        t.start();
        JOptionPane.showMessageDialog(null, "quit?");
        System.exit(0);


    }

    public static class UpdateUnderstandClock{
        private final int interval;
        private final boolean beep;

        public UpdateUnderstandClock(int interval, boolean beep){
            this.interval = interval;
            this.beep = beep;
        }

        public void start(){
            ActionListener listener = this.new timePrinter();  // 这里的this
            Timer t = new Timer(interval, listener);
            t.start();

            JOptionPane.showMessageDialog(null, "quit?");
            System.exit(0);

        }

        private class timePrinter implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone: " + new Date());
                if (UpdateUnderstandClock.this.beep) Toolkit.getDefaultToolkit().beep();  // 这个
            }
        }



    }

    // FocusSubPoint-03: Is the internal class in java useful? necessary? safe?
    public static void IsInnerClassUsefulNecessarySafe(){
        // Topic-01: 内部类是一种编译器现象，与虚拟机无关 ==》编译器将会把内部类翻译成用$(美元符号)分割外部类名与内部类名的常规类文件，而虚拟机则对此一无所知。

        // Topic-02: 举个例子，在UpdateUnderstandClock类内部的TimePrinter类将被翻译成类文件UpdateUnderstandClock$TimePrinter.class ==》为了能够看到执行的效果
        // javap -private ClassName  注意javap后面跟的是java编译后的.class文件
        System.out.println("FocusPoint_04_JavaInnerClass$UpdateUnderstandClock$timePrinter");
        // todo: 没有理解的透彻还，需要应用场景的扶持
    }

    // FocusSubPoint-04: How is the local(局部) internal class defined in java? How do I use it?
    public static void JavaLocalInnerClass(){
        // Topic-01: 什么意思呢，就是说上面的TimePrinter内部类只在start方法中创建这个类型的对象时使用了一次 ==》 如果遇到这种情况可以在start方法中定义局部内部类
        // Topic-02: 局部类不能用public 或 private访问说明符进行声明，它的作用域被限定在声明这个局部类的块中
        UpdateUnderstandTwinceClock uutc_ = new UpdateUnderstandTwinceClock(1000, false);
        uutc_.start();
    }

    public static class UpdateUnderstandTwinceClock{
        private int interval;
        private boolean beep;

        // default constructor
        public UpdateUnderstandTwinceClock(){};

        public UpdateUnderstandTwinceClock(int interval, boolean beep){
            this.interval = interval;
            this.beep = beep;
        }

        public void start(){


            // 局部内部类
            class LocalTimePrinter implements ActionListener{

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("At this tone: " + new Date());
                    if (UpdateUnderstandTwinceClock.this.beep) Toolkit.getDefaultToolkit().beep();
                }
            }

            ActionListener listener = new LocalTimePrinter();
            Timer t = new Timer(interval, listener);
            t.start();

            JOptionPane.showMessageDialog(null, "Really Quit?");
            System.exit(0);

        }
    }

    // FocusSubPoint-05：How to implement variables accessed by external methods in java？
    public static void AccessVariablesFromExternalMethod(){
        // Topic-01: 与其他内部类相比：局部类还有一个优点，他们不仅能够访问包含它们的外部类，还可以访问局部变量==》但是那些局部变量必须事实上为final==》也就是说他们一旦赋值就绝不会改变
        // Topic-02: 比如我想把UpdateUnderstandTwinceClock构造的参数interval、beep移动到start方法中
        UpdataUnderstandThirdClock uutc_ = new UpdataUnderstandThirdClock(); // 这里就不传入intercal与beep的参数
        uutc_.start(10000, false);
    }

    public static class UpdataUnderstandThirdClock extends  UpdateUnderstandTwinceClock{

        // 由外部方法访问变量
        public void start(int interval, final boolean beep){

            // 局部内部类
            class Local2TimePrinter implements ActionListener{
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("at the tone: " + new Date());
                    if(beep) Toolkit.getDefaultToolkit().beep();  // 局部类的方法只可以引用定义为final的局部变量所以上面⬆️ final boolean beep ==》这样对它初始化后就不能再更改了
                }
            }

            ActionListener listener = new Local2TimePrinter();
            Timer t = new Timer(interval, listener);
            t.start();

            JOptionPane.showMessageDialog(null, "Really Really Quit?");
            System.exit(0);
        }

    }

    // FocusSubPoint-06：How do I implement anonymous internal classes in java?
    public static void ImplementAnonymousInnerClass(){
        // Topic-01: 将局部颞部类的使用再深入一步：假如只创建这个类的一个对象，就不必命名了，这种类被称为匿名内部类（anonymous inner class）
        // Topic-02: 匿名内部类的语法格式：其中SuperType 可以是 ActionListener这样的接口，于是内部类就要实现和这个接口；SuperType也可以是一个类，于是内部类就要扩展它。
        /*
        *  new SuperType(construction parameters){
        *
        *       inner class methods and data
        *   }
        * */

        // Topic-03: 由于构造器的名字必须与类名相同，而匿名类没有类名 ==》 所以匿名类不能有构造器 ==》取而代之的是需要将构造器参数传递给超类（superclass）的构造器 ==》尤其在内部类实现接口的时候，不能有任何构造参数，而且还要像下面这样提供一组括号：
        /*
        *  new InterfaceType(){
        *
        *       methods and data
        *   }
        * */

        // Topic-04: 仔细看看构造一个类的新对象与构造一个扩展了那个类的匿名内部类的对象之间有什么差别。==》如果构造参数的闭小括号后面跟一个开大括号==》正在定义的就是匿名内部类
        /*
        *  Person ragnor = new Person("mary");  ==> a Person Object
        *  Person count  = new Person("Dracula"){...};  ==> an object of an inner class extending Person
        *
        * */

        UpdataUnderstandFourthClock uusfc_ = new UpdataUnderstandFourthClock();
        uusfc_.start(1000, false);

        // Topic-05: java程序员的习惯做法是使用匿名内部类实现时间监听器和其他回调 ==》如今最好的处理方式是使用lambda表达式
        UpdataUnderstandFifthClock uusffc_ = new UpdataUnderstandFifthClock();
        uusffc_.start(1000, false);
    }

    public static class UpdataUnderstandFourthClock extends  UpdateUnderstandTwinceClock{

        // 由外部方法访问变量
        public void start(int interval, final boolean beep){


            // 匿名内部类  ==》 它的含义就是：创建一个实现ActionListener接口的类的新对象，需要实现的方法actionPerformed定义在{}内
            ActionListener listener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("at the tone: " + new Date());
                    if(beep) Toolkit.getDefaultToolkit().beep();
                }
            };

            Timer t = new Timer(interval, listener);
            t.start();

            JOptionPane.showMessageDialog(null, "Really Really Quit?");
            System.exit(0);

        }

    }

    public static class UpdataUnderstandFifthClock extends  UpdateUnderstandTwinceClock{

        // 由外部方法访问变量
        public void start(int interval, final boolean beep){

            Timer t = new Timer(interval, event ->
                {
                System.out.println("at the tone: " + new Date());
                if(beep) Toolkit.getDefaultToolkit().beep();}
                );
            t.start();

            JOptionPane.showMessageDialog(null, "Really Really Quit?");
            System.exit(0);

        }

    }

    // FocusSubPoint-07: How do I implement static internal classes in java?
    public static void ImplementStaticInnerClass(){
        // Topic-01: 这句话细细品：有时候使用内部类只是为了把一个类隐藏在另外一个类的内部，并不需要内部类引用外围类对象==》为此可以将内部类声明为static，以便取消产生的引用。

        // Topic-02: 在内部类不需要访问外围类对象的时候，应该使用静态内部类，有些程序员用嵌套类（nested class）表示静态内部类。

        // Topic-03: 与常规内部类不同，静态内部类可以有静态域和方法

        // Topic-04: 声明在接口中的内部类自动称为static 和 public类

        System.out.println("就是 加个 static！我理解的");

    }


}


