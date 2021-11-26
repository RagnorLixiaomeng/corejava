package com.ragnor.corejava.chapter07_ExceptionsAndAssertionsAndlogs;

import java.util.EmptyStackException;
import java.util.Stack;

public class FocusPoint_03_JavaUseExceptionSkills {
    public static void main(String[] args) {
        // 关于异常处理机制的使用：一直争论中，推荐一些坑与技巧
        JavaUseExceptionSkills();
    }

    // FocusPoint-03：java use Exception Skills？
    public static void JavaUseExceptionSkills(){
        // Skill-01: 异常处理不能代替简单的测试==》下面例子没理解==》就是为了说明与执行简单的测试相比，捕获异常所花费的时间大大超过了前者==》因此捕获异常的基本规则是：只在异常情况下使用异常机制
        // 对一个空栈就行退栈操作-测试代码 ==> 646毫秒
        Stack<?> s = new Stack<>();
        if (!s.empty()) s.pop();

        // 强行退栈==》用捕获异常EmptyStackException ==> 21 739毫秒
        try{
            s.pop();
        } catch (EmptyStackException e){
            System.out.println(e.toString());
        }

        // Skill-02: 不要过分的细化异常：如果你把每一条异常都分装在一个独立的try语句块中，代码量会急剧膨胀

        // Skill-03: 利用异常层次结构：
        //                      不要只抛出RuntimeException异常==》应该寻找更加适合的子类或创建自己的异常类；
        //                      不要只捕获Throwable异常==》否则会使得程序代码更加的难度、更难维护
        //                      考虑受查异常与非受查异常的区别==》已检查异常本来就很庞大，不要为逻辑错误抛出这些异常
        //                      将一种异常转换成玲一种更加适合的异常时不要犹豫：包含、细化等等


        // Skill-04: 不要压制异常

        // Skill-05: 在检测错误时，"苛刻"要比放任更好：什么意思呢例如Stack.pop是返回一个null还是抛出一个异常？==》当栈空的时候是返回null还是抛出异常EmptyStackException==》认为在出错的地方抛出EmptyStackException要比后面抛出一个NullPointerException异常更好

        // Skill-06: 有时候传递异常比捕获异常更合适：让高层次的方法通知用户发生了错误，或者放弃不成功的命令更合适

        // 5、6的本质就是==》"早抛出、晚捕获"。
    }

}
