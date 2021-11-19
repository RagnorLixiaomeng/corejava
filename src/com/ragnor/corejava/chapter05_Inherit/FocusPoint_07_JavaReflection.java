package com.ragnor.corejava.chapter05_Inherit;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Scanner;

/**反射(Reflection)是Java程序开发语言的特征之一，它允许运行中的Java程序获取自身的信息，
 * 并且可以操作类或对象的内部属性。主要是指程序可以访问、检测和修改它本身状态或行为的一种能力，
 * 并能根据自身行为的状态和结果，调整或修改应用所描述行为的状态和相关的语义*/
public class FocusPoint_07_JavaReflection {
    public static void main(String[] args) throws Exception {
        //

        // Topic-01: 能够分析类能力的程序称为反射（reflective）==》反射机制可以用来
        //           。在运行时分析类的能力
        //           。在运行时查看对象，例如,编写一个toString方法供所有类使用
        //           。实现通用的数组操作代码
        //          。利用Method对象，这个对象很像C++中的函数指针

        // Topic-02: 反射是一种功能强大且复杂的机制 ==》使用它的主要人员是工具构造者，而不是应用程序
        TrackingAnlysisClass();
        CatchException();
        AnalysisClass();
        AnalysisObjectAtRuntime();
        UseReflectionToWriteGenericArrayCode();
        CallAnyMethod();

    }

    // FocusSubPoint-01：How to Tracking analysis Class？
    public static void TrackingAnlysisClass(){
        ragnorTrackClass rtc = new ragnorTrackClass("RAGNOR", 1800);
        // Topic-01: java中使用Class类专门保存运行时信息
        // Topic-02: Object类中的getClass方法将会返回一个Class类型的实例
        Class cl_01 = rtc.getClass();
        // Topic-03: Class类最常用的方法是getName返回类的名字
        System.out.println(cl_01.getName());

        // Topic-04:可以调用静态方法forName获得类名对应的Class对象==>Class.forName方法就是一个抛出已检查异常的例子
        String className = "com.ragnor.corejava.chapter05_Inherit.FocusPoint_07_JavaReflection$ragnorTrackClass";
        try{
            Class cl_02 = Class.forName(className);
            System.out.println(cl_02.getName());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public static class ragnorTrackClass{
        private String name;
        private double salary;

        public ragnorTrackClass(){};

        public ragnorTrackClass(String name, double salary){
            this.name = name;
            this.salary = salary;

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static String showMenu(String info){
            return info;
        }

        public String showMsg(String msg){
            return msg;
        }
    }

    // FocusSubPoint-02：How to catch exceptions？
    public static void CatchException(){
        // Topic-01: 程序抛出异常比终止程序灵活的多，这是因为可以提供一个"捕获"异常的处理器（handler）对异常情况进行处理

        try {
            Scanner ragnor_scanner = new Scanner(System.in);
            System.out.println("please input value:");
            String opt = ragnor_scanner.nextLine();
            System.out.println(opt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // FocusSubPoint-03：How to use the ability of reflection analysis class？
    public static void AnalysisClass(){
        // Topic-01: 反射机制的核心==》检查类的结构 ==》 方法就是获取对应的Class对象 + 通过Class对象调用getDeclaredFields/getDeclaredConstructors/getDeclaredMethods()/getClasses方法
        // java.lang.reflect包中有4个h核心类：
        //                          Field: 描述类的域
        //                              getName() ==》返回类的名称
        //                              getType() ==》返回描述域所属类型的Class对象
        //                              getModifiers() ==》 返回整型数值，用不同的位开关描述public 和 static这样的修饰符使用状况
        //
        //                          Method: 描述类的方法
        //                                            返回参数类型的方法
        //                                            返回值类型的方法
        //                          Constructor: 描述类的构造器
        //                                            返回参数类型的方法

        //                          Modifier：描述修饰符
        //                              isPublic/isPrivate/isFinal

        ragnorTrackClass rtc = new ragnorTrackClass("nu", 1800);
        Class cl = rtc.getClass();
        // Topic-01: 获取类的名字
        System.out.println(cl.getName());
        // Topic-02: 获取类的类型
        System.out.println(cl.getTypeName());
        // Topic-03: 获取类中修饰符的使用情况
        System.out.println(cl.getModifiers());

        // Topic-04: 获取类的构造器参数
        System.out.println(Arrays.toString(cl.getDeclaredConstructors()));

        // Topic-05: 获取类所有的实例域
        System.out.println(Arrays.toString(cl.getDeclaredFields()));
        try{
            System.out.println(cl.getDeclaredField("salary").getName());
        } catch (NoSuchFieldException e){
            e.printStackTrace();
        }


        // Topic-06: 获取类所有的方法
        System.out.println(Arrays.toString(cl.getDeclaredMethods()));

        // Topic-07: 获取到所有的类名和超类名
        System.out.println(Arrays.toString(cl.getClasses()));
        System.out.println(cl.getSuperclass());

        // Topic-08: 获取组成数组元素的对象的类型
        System.out.println("*******");
        System.out.println(cl.getComponentType());

    }

    // FocusSubPoint-04：How to use reflection to analyze objects at runtime？
    /**Class -> Field -> Object*/
    public static void AnalysisObjectAtRuntime() throws NoSuchFieldException, IllegalAccessException {
        // Dangling Javadoc comment原来是放到inline了

        ragnorTrackClass rtc = new ragnorTrackClass("ning", 8);
        Class<? extends ragnorTrackClass> cl_test = rtc.getClass();  // Todo: 不知道为什么这样才能把Raw use of parameterized class 'Class' 消了

        Field f_field_test_name = cl_test.getDeclaredField("name"); // NoSuchFieldException

        // Topic-01: 由于salary是一个私有域，所以会抛出IllegalAccessException ==》通过设置f_field_test.setAccessible(true);
        f_field_test_name.setAccessible(true);
        Object obj_test_name = f_field_test_name.get(rtc); // IllegalAccessException

        // Topic-02: name 域 返回是一个String 用Object接收没问题，如果是salary呢 double不是对象
        Field f_field_test_salary = cl_test.getDeclaredField("salary");
        f_field_test_salary.setAccessible(true);
        Object obj_test_salary = f_field_test_salary.getDouble(rtc);


    }

    // FocusSubPoint-05：How to use reflection to write generic array code？
    public static void UseReflectionToWriteGenericArrayCode(){
        // Topic-01: java.lang.reflect包中的Array类允许动态的创建数组
        ragnorTrackClass[] rtcs = new ragnorTrackClass[100];
        rtcs[0] = new ragnorTrackClass("li", 1800);
        rtcs[1] = new ragnorTrackClass("wang", 1900);
        // .....code
        // array is full
        rtcs = Arrays.copyOf(rtcs, 2 * rtcs.length);

        // Topic-02: 由于推演出一个想法就是：如何编写一个通用的方法可以把 ragnorTrackClass[] 数组 转换为 Object[]数组
        System.out.println(Arrays.toString(badCopyOf(rtcs, 2)));


        // Topic-03: 将一个ragnorTrackClass[] 临时的转换为 Object[] 数组 ==》然后再把它转换回来是可以的 ==》但是从一来是就是Object[]的数组缺永远不能转换为ragnorTrackClass[]
//        ragnorTrackClass[] new_trcs = (ragnorTrackClass[]) badCopyOf(rtcs, 2);  // 不可以：java.lang.ClassCastException
        // TODO:看下反射到底怎么实现的
        ragnorTrackClass[] new_trcs = (ragnorTrackClass[]) goodCopyOf(rtcs, 2); // 可以




    }

    /** 实现把 ragnorTrackClass[] 数组 转换为 Object[]数组*/
    // public static Object[] badCopyOf(ragnorTrackClass[] old_obj, int newLength)
    public static Object[] badCopyOf(Object[] old_obj, int newLength){
        Object[] newArray = new Object[newLength];
        System.arraycopy(old_obj, 0, newArray, 0, newLength);
        return newArray;
    }

    /**利用反射实现 来扩展任意类型的数组，而不只是对象数组*/
    public static Object goodCopyOf(Object old_obj, int newLength){
        // step01: 首选获取 old_obj数组的类对象
        Class cl = old_obj.getClass();
        // step02: 确认它是一个数组
        if (!cl.isArray()) return null; // 这个为null检查真棒
        // step03: 使用Class类的getComponentType方法确定数组对应的类型
        Class componentType = cl.getComponentType();
        // step04: 获取数组的长度，为什么getComponentType是Class的方法，getLength是Array的方法哈哈，书中时候不知道，反射方法分类有点奇怪
        int length = Array.getLength(old_obj); // java.lang.reflect的Array
        // step05: Array.newInstance返回一个具有给定类型、给定维数的新数组==》核心就是上面那个是Object[] newArray = new Object[newLength];构建的新数组，这里是调用反射的newInstance
        //           componentType: 组成数组元素的对象的类型
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(old_obj, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }

    // FocusSubPoint-06：How to call any(任意) method？
    public static void CallAnyMethod() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        // Focus-01: 将一个方法的存储地址传给另外一个方法，以便于第二个方法能够随后调用它 ==》 这叫做函数指针执行任意函数

        // Focus-02: 如何获取Method对象呢？
        //           1、可以调用getDeclareMethods方法，然后对返回的Method对象数组进行查找，直到发现想要的方法为止
        //           2、也可以调用Class类中的getMethod方法的到想要的方法：getMethod方法的签名是 Method getMethod(String name, Class...parameterTypes)

        // Foucu-03: 获取ragnorTrackClass类的getName 与 getMethod方法指针
        Method m1 = ragnorTrackClass.class.getMethod("getName");
        Method m2 = ragnorTrackClass.class.getMethod("showMenu", String.class);
        Method m3 = ragnorTrackClass.class.getMethod("showMsg", String.class);


        try{
            String info_msg = (String) m2.invoke(null, "hello!"); // showMenu如果是静态方法，这里的第一个传null
            String msg = (String) m3.invoke(new ragnorTrackClass(), "new hello!"); // showMsg不是静态方法就传入 the object the underlying method is invoked from ：调用showMsg方法的对象
            System.out.println(info_msg);
            System.out.println(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
