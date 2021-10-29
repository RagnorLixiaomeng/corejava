package chapter03_JavaBasicProgramDesignStructure;

import javax.swing.*;
import java.util.Date;
import java.util.Scanner;

public class FocusPoint_08_JavaControlFlow {
    public static void main(String[] args) {
        // Topic-01: 程序三大结构 顺序、分支、循环
        // Topic-02: 分支中最重要的是条件语句； 循环中最终要的是循环语句；最后还有switch语句
        BlockScope();
        ConditionalStatement();
        CycleStructure();
        DetermineCycle();
        MultipleChoiceSwitch();
        InterruptControlProcessStatement();

    }

    // FocusSubPoint-001：What is block scope？
    public static void BlockScope(){
        // Topic-01: 块也就是复合语句：指的是由一对大括号括起来的若干条简单的java语句；块确定了变量的作用域；一个块可以嵌套在另一个块中
        // Topic-02: 不能在嵌套的两个块中声明同名的变量
        // Topic-03: 在 C++中，可以在嵌套的块中重定义一个变量=》在内存定义的变量会覆盖在外层定义的变量=》这样有可能导致程序设计错误，因此在java中不允许这样做。
        int ragnor_01 = 1;
        {
            int ragnor_02 = 2; // ragnor_02 is only defined up to here : scope
//            int ragnor_01 = 1; // Error -- Cant redefined n in inner block
            System.out.println(ragnor_01);
            System.out.println(ragnor_02);
        }
        System.out.println(ragnor_01); // cant call ragnor_02
    }

    // FocusSubPoint-002：What is a conditional statement？
    public static void ConditionalStatement(){
        // Topic-01: 在java中，条件语句的格式为：if (condition) statement =>条件语句必须用括号括起来
        Scanner ragnor_input_01 = new Scanner(System.in);
        System.out.println("please input your name:");
        String input_name = ragnor_input_01.nextLine();
        if (input_name.equals("ragnor")) System.out.println("hello vip ragnor!");

        // Topic-02: java中如果希望在某个条件为真时，执行多条语句，那就使用块语句： if (condition){statement1; statement2}
        System.out.println("please input your job name:");
        String beauty_name = ragnor_input_01.nextLine();
        if (beauty_name.equals("yeqingning")){
            System.out.println("need to exercise your body inner!");
            System.out.println("need to mouse heart outer of me!");
        }

        // Topic-03: java中的分支结构完整形态 if (condition1) {statement1} else if (condition2) {statementw} ...else{finall-statement}
        System.out.println("please input your college name:");
        String college_name = ragnor_input_01.nextLine();
        if (college_name.equals("jiangnan")){
            System.out.println("wire yor t-shirt!");
        }else if (college_name.equals("beida")){
            System.out.println("shape your body!");
        }else{
            System.out.println("go out!");
        }


    }

    // FocusSubPoint-003：What is Cyclic structure？
    public static void CycleStructure(){
        // while循环可以理解为：不确定循环  VS   for循环：确定循环
        // Topic-01: while 循环常用来需要首先检测循环条件的情况=》因此循环体中的代码可能不被执行 while (condition) statement
        Scanner ragnor_scanner_01 = new Scanner(System.in);

        double current_money = 900000;
        double target_money = 3500000;

        while (current_money < target_money){
            System.out.println("please input this month your salary:");
            double story_money_each_month = ragnor_scanner_01.nextDouble();
            current_money += story_money_each_month;
            System.out.printf("you need %f more", (target_money - current_money));
        }


        // Topic-02: 如果希望循环体至少执行一次，则应该将检测条件放在最后=》使用do ..while循环： do statement while (condition)

        do {
            System.out.println("please input this month your salary:");
            double story_money_each_month = ragnor_scanner_01.nextDouble();
            current_money += story_money_each_month;
            System.out.printf("you need %f more", (target_money - current_money));
        } while
        (current_money < target_money);
    }

    // FocusSubPoint-004：What is Determine cycle？
    public static void DetermineCycle(){
        // Topic-01: for 循环是支持迭代的一种通用的结构=》主要利用每次迭代之后更新的计数器或类似的变量来控制迭代次数
        // Topic-02: java 中for循环的结构是：for (初始化计数器；循环条件；更新计数器规则){statement}
        // Topic-03: java与c++一样，尽管允许在for循环的各个部分放置任何表达式，但有一条不成文的规则：for语句的3个部分应该对同一个计数器变量
        //           进行初始化、检测和更新=》否则会导致编写的循环晦涩难懂
        for (int i = 10; i > 0; i--) System.out.println("countting donw :" + i);

        // Topic-04:在循环中，检测两个浮点数是否相等需要格外的小心=》比如0.1 自己加永远都得不到10 =》比如0.1 + 0.3
        // 下面的就是因为0.1无法精确地用二进制表示=》所以x将从 9.99999999999998 跳到 10.09999999999998，那就没有10
        for (double x = 0; x != 10; x += 0.1) System.out.println("i will mot stop cycle");

        // Topic-05: 初始化计数器的作用域为for循环的整个循环体
        // Topic-06: for循环内部定义的变量是不能在循环体之外使用的

        // Topic-07: 你知道吗？for循环只不过是while循环的一种简化形式
        for (int i = 10; i >= 0; i--){
            System.out.println("counting down:" + i);
        }

        /*等价于*/
        int i = 10;
        while (i > 0){
            System.out.println("counting down:" + i);
            i--;
        }


    }

    // FocusSubPoint-005：What is multiple choice: switch statement!

    public enum Size {
        SMALL(1000, "小码"),
        MEDIUM(2000, "中码"),
        LARGE(3000, "大码"),
        EATER_LARGE(4000, "超大码");

        private final int code; // 因为code是静态的，msg写了个setMsg去更改，code没有
        private String msg;

        // 为了更好的返回代号和说明，必须呀重写构造方法==》不写这个构造方法没办法写上面的 ()
        private Size(int code, String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode(){
            return code;
        }

        public String getMsg(){
            return msg;
        }

        public void setMsg(String msg){
            this.msg = msg;
        }

        // 根据value返回枚举类型，主要在switch中使用
        public static Size getByValue(int value){  // 你看这里就知道返回值已经要求是个Size
            for (Size size: values()){
                if (size.getCode() == value){
                    return size;
                }
            }
            return null;
        }

    }

    public static void MultipleChoiceSwitch(){
        // Topic-01: Java中保留了C/C++ 中完全一样的switch语句=》因为在处理多个选项时使用if/else显得有些笨拙
        Scanner in = new Scanner(System.in);
        System.out.println("Select an option (1, 2, 3, 4): ");
        int choice = in.nextInt();
        switch (choice){
            case 1:
                System.out.println("this choice is 1");
                break;  // break的意义就是不再向下执行了
            case 2:
                System.out.println("this choice is 2");
                break;
            case 3:
                System.out.println("this choice is 3");
                break;
            case 4:
                System.out.println("this choice is 4");
                break;
            default:
                System.out.println("bad input");
                break;
        }

        // Topic-02: java中的枚举常量在switch中不必在每个标签中指定枚举名，可以由switch表达式确定
        // step-01: 获取代码
        int code = Size.SMALL.getCode();
        // step-02: 获取代码对应的信息
        String msg = Size.SMALL.getMsg();
        // step-03: 在switch中使用通常需要先获取枚举类型才判断，因为case中是常量或者int、byte、short、char，写其他代码编译是不通过的
        switch (Size.getByValue(code)){
            case SMALL:
                System.out.println("small");
                break;
            case LARGE:
                System.out.println("large");
                break;
            default:
                System.out.println("wrong");
        }

    }

    // FocusSubPoint-006：What is an interrupt control process statement?
    public static void InterruptControlProcessStatement(){
        // Topic-01: java中断控制流程语句跟python一样：break 与 continue但是break有两种类型
        // Topic-02: 不带标签的break语句：用于退出循环、switch
        for (int j = 1; j < 10; j++){
            System.out.printf("this is %d time\n", j);
            if (j == 5) break;
        }


        // Topic-03: 还记得 C30的 label goto语句吗？ ==》java带标签的break语句==》用于跳出多重嵌套的循环语句
        //           标签必须必须放在希望跳出的最外层循环之前， 而且必须紧跟一个冒号;
        // Topic-04: 对于任何使用break语句的代码都需要检测循环是正常结束，还是break跳出。
        // Topic-05: 据说在RocketMQ的Client中是类似这样使用的： 不难看出，这其实就是满足条件跳出给定label的代码块。
        //           不再执行。和单独的break其实类似，单独的break作用于循环，带label的break作用于你定义的代码块。
        //           但是有一点，break label的位置一定要在label代码块的内部。否则无效，语法错误。


        label1:
        for (int i = 0; i < 10; i++) {
            label2:
            if (i > 0) {
                if (i > 8) {
                    break label1;
                }
                System.out.print(i + " ");
                if (i > 3) {
                    break label2;
                }
                System.out.print(" if end ");

            }

            label3: {
                if (i > 5) {
                    break label3;
                }
                System.out.print(", label3 block");
            }
            System.out.println(", for end ");
        }

        // Topic-06: continue 语句： 与break语句一样，它将中断正常的控制流程=》continue语句将控制转移到最内层循环的首部
        Scanner new_scanner = new Scanner(System.in);

        int score = 0;
        while (score < 10){
            System.out.println("plase input n value: ");
            int n = new_scanner.nextInt();
            if (n < 0) continue;
            score ++; // won't execute if n < 0, insure ecery cycle score will be larger
            System.out.printf("hello %d\n", score);
        }


    }

}


