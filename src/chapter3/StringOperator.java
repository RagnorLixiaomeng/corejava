package chapter3;

public class StringOperator {
    public static void main(String[] args) {
        // 1、 定义字符串与获取子串
        String greeting = "Hello";
        String s = greeting.substring(0, 3);
        System.out.println(s);

        // 2、拼接
        String auther = "ragnor";
        int price = 18;
        String book = "javacore";
        System.out.println(auther + price + book);

        // 2.1 如果需要把多个字符串放在一起，用一个定界符分割，可以使用静态join方法：
        String all = String.join("/", "r", "a", "g", "n", "o", "r");
        System.out.println(all);

        // 3、不可变字符串：就死不能够修改的字符串String类没有提供用于修改字符串的方法
        /*这里有个故事：java的设计者觉得共享带来的好处高于提取，所以你想String是一个不可变的类型，是存在
        * 公共的存储池中的，字符串变量指向存储池中相同的位置，如果复制一个字符串变量，原始的字符串与复制的字符串共享
        * 相同的内存字符*/
        String cantchange = all.substring(0, 2) + "NOR";  // r/NOR 从结果来看也是取头不取尾
        System.out.println(cantchange);

        // 4、检测字符串是否相等
        boolean Flag = all.equals("r/a/g/n/o/r");


        // attention 千万不要使用 == 比较两个字符串相等
        String reego = "REEGO";

//        jshell> String demo = reego.substring(0, 3) + "GO"
//        demo ==> "REEGO"
//        jshell> demo
//        demo ==> "REEGOf"
//
//        jshell> reego
//        reego ==> "REEGO"
//
//        jshell> demo == reego
//        $10 ==> false
//        这是因为如果虚拟机始终将相同的字符串共享，就可以使用 == 运算符检测是否相等。但是实际上只有字符串常量是共享的，而
//        + 或者 substring 等操作产生的结果并不是共享的。因为千万不能使用 == 测试字符串的相等性

        // 5、空串与 Null串
        // 空串：长度为0的字符串
        // Null串：表示目前没有任何对象与该变量关联
        String kong_string = "";
        if (kong_string.length() == 0 || kong_string.equals("")){
            System.out.println(kong_string);
        }

        String null_string = null;
        if (null_string == null){
            System.out.println(null_string);
        }

        // 有时要检查一个字符串既不是null 也不为空串，首先要检查str不为null,因为length都是0，不能保证null
        String str = "";
        if (str != null && str.length() != 0){
            System.out.println(str + "is not null and kong");
        }


        // 6、码点与代码单元
        // 码点就是在unicode代码空间中的一个值，取值0x0 到 0x10FFFF,代表一个字符。
        //code point :代码点是信息的原子单位。文本是一系列代码点。每个代码点都是一个数字，由 Unicode 标准赋予其含义.
        //code unit : 代码单元是编码代码点的一部分的存储单元;在 UTF-8 中这意味着 8 位，在 UTF-16 中这意味着 16 位;
        // 单个代码单元可以代表一个完整的代码点，或一个代码点的一部分。例如，雪人字形 (☃) 是一个单一的代码点，但有 3 个 UTF-8 代码单元和 1 个 UTF-16 代码单元

        // 目的是获取 String字符串的某个位置的码点与代码单元

        String name = "childhood"; // 分析可以知道，java中的字符串都是由char构成

        // 获取码点数量
        int cpCount = name.codePointCount(0, name.length());

        // 获取位置n处的代码单元
        char first = name.charAt(0);
        char last = name.charAt(name.length() - 1);

        // 获取第i个位置处的码点
        int index = name.offsetByCodePoints(0, 2);
        int cp = name.codePointAt(index);

        System.out.println(cp);  // 105

        int [] codePoints = name.codePoints().toArray();
        System.out.println(codePoints);

        // 7、 String API ： java的String 类包含了50多个方法。而且绝大多数都有高频的使用
        // 就是import java.lang.String.* 而且接口文档中有更信息的使用指导

        // 8、构建字符串
        /*说是有些时候，需要由较短的字符串构建字符串，例如，按键或来自文件中的单词。采用字符串连接的方式达到此目的效率是非常低的。
        * 每次连接字符串，都会构建一个新的String对象，既耗时又浪费空间。==》使用StringBuilder这个类就可以避免这个问题的发生*/

        // step 1: 首先构造一个空的字符串构建器：
        StringBuilder builder = new StringBuilder();

        // step 2:每次需要的时候就添加一部分内容进去
        builder.append("ree");
        builder.append("go");

        // step 3:需要用到字符串的时候就调用toString方法，将可以得到一个String的对象，其中包含了构建器中的字符序列
        String completedString = builder.toString();

        System.out.println(completedString);

        // 9、输入输出




    }
}
