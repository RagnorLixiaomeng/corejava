package chapter04_ClassAndObject;

public class FocusPoint_05_JavaDefineMethodParas {
    public static void main(String[] args) {
        // Topic-01: 程序设计语言中将参数传递给方法有哪些方式？
        //           1、call by value : 按值调用（数据的地址）
        //           2、call by reference : 按引用调用（变量的地址）

        // Topic-02: java程序设计语言总是采用按值调用：方法得到的是所有参数值的一个拷贝 + 方法不能修改传递给它的任何参数变量的内容
        //           1、一个方法不可能修改一个基本数据类型的参数。
        //           2、但是对象引用作为参数就不同了：一个方法可以改变一个对象参数的状态
        //           3、一个方法不能让对象参数引用一个新的对象
        /*1*/
        double score = 1;
        changeValue(score);
        System.out.println(score); // 并不能把score 给 升3倍，传递的是引用

        /*2*/
        Automation huayun_auto = new Automation("ui_automation");
        System.out.printf("对象引用作为参数 %s\n", System.identityHashCode(huayun_auto));
        changeAutoType(huayun_auto, "api_autaomation"); // 这里的change方法调用的就是对象的set方法，这才是修改的关键
        System.out.printf("对象引用作为参数 %s\n", System.identityHashCode(huayun_auto));
        System.out.println(huayun_auto.getAutoType()); // 但是对象引用作为参数就不同了


        /*3*/ // 说明参数变量t_1 与 t_2交换了，但是 huayun_1 huayun_2并没有收到影响
        Automation huayun_1 = new Automation("UI-automation");
        Automation huayun_2 = new Automation("API-automation");
        // before
        System.out.printf("BeforeSwap: huayun_1 : %s, 类型是：%s\n", System.identityHashCode(huayun_1), huayun_1.getAutoType());
        System.out.printf("BeforeSwap:huayun_2 : %s, 类型是：%s\n", System.identityHashCode(huayun_2), huayun_2.getAutoType());

        // swap
        System.out.println("*==*");
        swap_auto_type(huayun_1, huayun_2);

        // after
        System.out.println("*==*");
        System.out.printf("AfterSwap: huayun_1 : %s, 类型是：%s\n", System.identityHashCode(huayun_1), huayun_1.getAutoType());
        System.out.printf("AfterSwap: huayun_2 : %s, 类型是：%s\n", System.identityHashCode(huayun_2), huayun_2.getAutoType());

//        huayun_1 : 1456208737, 类型是：UI-automation
//        huayun_2 : 1845066581, 类型是：API-automation
//                *==*
//        t_1: 1845066581 is: API-automation
//        t_2: 1456208737 is: UI-automation
//                *==*
//        huayun_1 : 1456208737, 类型是：UI-automation
//        huayun_2 : 1845066581, 类型是：API-automation



    }


    public static double changeValue(double value_old){
        return value_old * 3;
    }

    public static void changeAutoType(Automation a, String new_a_t){
        a.setAuto_type(new_a_t);
    }

    public static void swap_auto_type(Automation t_1, Automation t_2){
        Automation temp = t_1;
        t_1 = t_2;
        t_2 = temp;

        System.out.printf("EndSwap:t_1: %s hash: %s is: %s\n", t_1, System.identityHashCode(t_1), t_1.getAutoType());
        System.out.printf("EndSwap:t_2: %s hash: %s is: %s\n", t_2, System.identityHashCode(t_2), t_2.getAutoType());
    }


}

class Automation{
    private String auto_type;

    public Automation(String a_t){
        this.auto_type = a_t;
    }

    public String getAutoType(){
        return this.auto_type;
    }

    public void setAuto_type(String new_auto_type){
        this.auto_type = new_auto_type;
    }


}
