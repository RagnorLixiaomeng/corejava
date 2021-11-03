package chapter03_JavaBasicProgramDesignStructure;

import javax.print.DocFlavor;

public class FocusPoint_06_JavaStringOperate {
    public static void main(String[] args) {
        // Core: java字符串

        // Topic-01: java没有内置的字符串类型，而是在标准java类库中提供一个预定义类，叫做String，每个使用双引号括起来
        //           的字符串都是String类的一个实例
        // Topic-02: 从概念上来讲java字符串就是unicode字符序列
        SubstringMethod();
        SplicingMethod();
        UnChangedString();
        JudgementWhetherTheStringAreEqual();
        EmptyStringAndNullString();
        CodePointAndCodeUnits();
        StringApi();
        BuildString();
    }

    // FocusSubPoint-001: substring
    public static void SubstringMethod(){
        // Topic-01: String类的substring方法可以从一个较大的字符串提取出一个子串。
        String myName = "ragnor";
        String result_substring = myName.substring(0, 2); // 取头不取尾，好处是便于计算子串长度：2-0=2
        System.out.println(result_substring);

    }

    // FocusSubPoint-002: Splicing
    public static void SplicingMethod(){
        // Topic-01: 使用 + 拼接字符串
        String ye = "ragnor_01";
        String qing = "ragnor_02";
        String splicing_01 = ye + qing;
        System.out.println(splicing_01);
        // Topic-02: 使用String的静态join方法，拼接分隔符与其它字符
        String splicing_02 = String.join("-", ye, qing);
        System.out.println(splicing_02);

    }

    // FocusSubPoint-003: un-changed String
    public static void UnChangedString(){
        // Topic-01: java的String类中没有提供用于修改字符串的方法，所以如果希望将字符串的内容修改：需要提取需要的字符串 + 拼接上替换的字符串
        // Topic-02: 由于不能修改jva字符串中的字符，所以在java文档中将String类对象成为不可变字符串：如同数字3永远是数字3一样，
        //           字符串"Hello"永远都包含H e l l o的代码单元序列，而不能修改其中任何一个字符
        // Topic-03: 通过拼接"he"和"llo"来创建一个新字符串的效率的确不高。但是不可变字符串最大的优点：编译器可以让字符串共享
        //           java设计者认为共享带来的效率远远胜过于提取、拼接字符串带来的低效率；
        //           这么去理解：想象下将各种字符串存放在公共的存储池中，字符串变量执行存储池中响应的位置。如果复制一个字符串就是复制的引用，本质内存中就只保存一份相同的字符串
        String unchangedstring_01 = "ragnor";
        // need rognar
        String target_01 = unchangedstring_01.substring(0,1) + "o" +
                unchangedstring_01.substring(2,4) + "a" +
                unchangedstring_01.substring(unchangedstring_01.length() - 1);
        System.out.println(target_01);
    }

    // FocusSubPoint-004：Judgment of whether the strings are equal
    public static void JudgementWhetherTheStringAreEqual(){
        // Topic-01： 在java中检测字符串是否相等不能用 == ， 使用s.equals(t) 方法
        // Topic-02:  == 运算符只能够确定两个字符串是否放置在同一位置上==》当然，如果字符串放在同一位置上，必然相等==》但是最常
        //            见的就是将内容相同的多个字符串的拷贝放置在不同的位置上
        // Topic-03:  如果虚拟机始终将相同的字符串共享，就可以使用 == 运算符检测是否相等。但实际上只有字符串常量是共享的，而+或者
        //            substring等操作产生的结果并不是共享的。== 因此，千万不要使用 == 运算符测试字符串的相等性，以免在程序中出现
        //            糟糕的bug。

        String equal_01 = "ragnor_001";
        String equal_02 = "ragnor_001";
        String equal_03 = equal_01.substring(0, 6) + "_001"; // "ragnor_001"

        /* case-01*/
        if (equal_01 == equal_02){
            System.out.println("equal_01 == equal_02"); // equal_01 == equal_02
        }else if (equal_01 != equal_02){
            System.out.println("equal_01 != equal_02");
        }

        /* case-02 */
        if (equal_01 == equal_03){
            System.out.println("equal_01 == equal_03");
        }else if (equal_01 != equal_03){
            System.out.println("equal_01 != equal_03"); // equal_01 != equal_03
        }

        /* case-03 */
        if (equal_01.equals(equal_03)){
            System.out.println("equal_01 equals equal_03"); // equal_01 equals equal_03
        }else{
            System.out.println("equal_01 not equals equal_03");
        }



    }

    // FocusSubPoint-005：Empty and Null in string
    public static void EmptyStringAndNullString(){
        // Topic-01: 空串 "" 是长度为0的字符串：可以使用 if（str.length() == 0）或者 if （str.equals("")）来检测
        //           空串是一个java对象，有自己的长度 （0） 和内容 （空）。
        // Topic-02: String变量还可以存放一个特殊的值，就是null：表示目前没有任何对象与该变量关联： 使用 str == null来判断

        String empty_type = "";
        String null_type = null;

        if (empty_type.equals("")){
            System.out.println("empty_type is empty");
        }else if(null_type == null){
            System.out.println("null_type is null");
        }
    }

    // FocusSubPoint-006：What are code points and code units?
    public static void CodePointAndCodeUnits(){
        // Topic-01: 在设计Java时决定采用16位的Unicode字符集 BUT 现在16位的Char类型已经不能满足描述所有Unicode字符的需要了。
        //           Java为了解决这个问题的方法是使用码点和代码单元
        // Topic-02: java中的字符串是由char值序列组成 ==》 char数据类型是一个采用UTF-16编码表示Unicode码点的代码单元
        // Topic-03: 码点：就是某个任意字符在Unicode编码表中对应的代码值
        // Topic-04: 代码单元：是在计算机中用来表示码点的，大部分码点只需要一个代码单元表示，但是有一些是需要两个代码单元表示的。

        String world_1 = "\uD835\uDD46"; // 就是𝕆
        String world_1_code_point = Integer.toHexString(world_1.codePointAt(0)); // 码点
        String world_1_first_codeUnit = Integer.toHexString(world_1.charAt(0)); // 代码单元1
        String world_1_second_codeUnit = Integer.toHexString(world_1.charAt(1)); // 代码单元2
        System.out.println(world_1.length()); // 这就是我知道为什么有俩代码单元

        System.out.println(world_1_code_point);
        System.out.println(world_1_first_codeUnit);
        System.out.println(world_1_second_codeUnit);


    }

    // FocusSubPoint-007：String's Api Document
    public static void StringApi(){
        // Topic-01: java中的Sting类包含了50多个方法，而且这50多个方法都很有用，使用频率非常高
        System.out.println(" which java");
        System.out.println("cd ${which java}/java11_doc/docs_jdk_11.0.12_all/");
        System.out.println("open index.html");
        System.out.println("then search your required Api");
    }

    // FoucusSubPoint-008：Build string
    public static void BuildString(){
        // Topic-01: 有时候，需要由较短的字符串构建字符串，采用字符串连接的方式效率比较低，每次连接字符串都会构建一个新的String对象
        //           既耗时、又浪费时间。使用StingBuilder类来避免这个问题的发生

        // step-01: new empty StringBuilder
        StringBuilder builder_01 = new StringBuilder();

        // step-02: call builder method to append
        builder_01.append("abc");
        builder_01.append("def");

        // step-03: if you need to get String object
        String completedString = builder_01.toString();

        System.out.println(completedString);

        // 上面的效果等价于,如果是这种需求，肯定是由各自的适合的应用场景我暂时没领悟到

        String completedString_2 = "abc" + "def";
        System.out.println(completedString_2);
    }


}
