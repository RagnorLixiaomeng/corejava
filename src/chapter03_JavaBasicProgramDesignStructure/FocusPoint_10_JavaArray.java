package chapter03_JavaBasicProgramDesignStructure;

import javax.print.DocFlavor;
import java.util.*;

public class FocusPoint_10_JavaArray {
    public static void main(String[] args) {
        // Core: java中的数组

        // Topic-01: 数组是一种数据结构==》用来存储同一类型值的集合==》通过一个整型下标可以访问数组中的每一个值

//        DefineArray();
//        ForEachLoop();
//        ArrayCopy();
//        String[] test_para = {"-g", "ragnor"}; CommandLineParas(test_para);
//        ArraySort();
//        MultidimensionalArray();
        IrregularArray();
    }
    // FocusSubPoint-002：how to init array and define array : 4 styles!
    public static void DefineArray(){
        // Topic-01: 定义数组的格式：int[] a = new int[100]; ==》推荐 因为它将类型int[] （整型数组）与 变量名 分开了。
        //                    或者 int a[] = new int[100]; ==》不推荐
        //                    或者 int[] a = {1, 2, 3}; ==>在使用这种语句时，不需要调用new
        //                    或者 new int [] {17, 19, 13}; ==>这个叫做匿名数组: 可以在不创建新变量的情况下重新初始化一个数组
        //           new 是对数组对象进行初始化的关键字

        // Topic-02: 数组的下标是从 0 - 99开始的

        // Topic-03: 切记创建一个数字数组时，所有元素都初始化为0；
        //              boolean数组的元素会初始化为false；
        //              对象数组的元素则初始化为一个特殊值null，表示这些元素（还）未存放任何对象。

        String[] names_$_1 = new String[10];  // 方式1
        String name_$_2[] = new String[10];  // 方式2
        String[] name_$_3 = {"li", "zhao", "hu"}; // 方式3
        String[] name_lambda_$_4 = new String[3]; name_lambda_$_4 = new String[] {"Cat", "Dog", "Monkey"}; // 方式4

        System.out.println(name_$_2[0]); // null default

        for (int i = 0; i < names_$_1.length; i++){
            names_$_1[i] = "String" + i;
            System.out.println(names_$_1[i]);
        }

        // Topic-04: 一旦创建了数组，就不能再改变它的大小（尽管可以改变每一个数组元素） =》 如果经常需要在运行过程中扩展数组的大小=》应该使用ArrayList数组列表
        ArrayList ages = new ArrayList(); // 空间满了自动2倍扩充
        System.out.println(ages); // []

    }

    // FocusSubPoint-001：What is the for each loop？
    public static void ForEachLoop(){
        // Topic-01: for each 循环的目的是：自动遍历数组中的每个元素，而不必为下标的起始值和终止值而操心
        // Topic-02: for each 结构：for (variable: collection) statement
        // Topic-03: for each 结构当然可以用for循环来替代，但是你要操心遍历的长度也就是下标的起始与终止
        // Topic-04：有个更加优雅的方式获取到数组中的所有值： Arrays.toString(数组);

        // step-01: 定义
        int[] array_score = new int[10];
        Random random_ele = new Random();

        // step-02: 赋值
        for (int index = 0; index < array_score.length; index++){
            array_score[index] = random_ele.nextInt(10);;
        }

        // step-03: 遍历取值
        for (int element: array_score) {  // 本质
            System.out.println(element);
        }

        // step-04: 获取整个数组值
        System.out.println(Arrays.toString(array_score)); //

    }

    // FocusSubPoint-003：What is array copy?
    public static void ArrayCopy(){
        // Topic-01: java中允许将一个数组变量拷贝给另一个数组变量=》这样两个变量将引用同一个数组 ：浅拷贝，拷贝的是引用
        // step-01: define variables
        int[] array_agent_01 = new int[10];
        int[] array_lightcopy_01 = array_agent_01;
        // step-02: value array[index=0]
        array_lightcopy_01[0] = 10; // now array_agent_01[0] is also 10

        // Topic-01: java中当然可以将一个数组中的值拷贝到一个新数组中去 ==》 两个变量对应各自的数组空间：深拷贝，拷贝的是内存中的数据
        int[] array_deep_copy = Arrays.copyOf(array_agent_01, 3 * array_agent_01.length); // 深拷贝并扩充新数组的长度

    }

    // FocusSubPoint-004：What is command-line paras?
    public static void CommandLineParas(String[] args){
        // Topic-01: 原来这里的命令行参数指的是 在定义java的main方法时：public static void main(String[] args){} 中的args不就是数组吗？
        if (args.length == 0 || args[0].equals("-h")){
            System.out.println("Hello,");
        }else if (args[0].equals("-g")){
            System.out.println("goodbye,");
        }
        // print the other command-line arguments
        for (int i = 1; i < args.length; i++){  // 把 i=0 会有一个有趣的现象 for each哈哈
            System.out.println(" " + args[i]);
        }
        System.out.println("!");
        System.out.printf("args 数组中包含 %s", Arrays.toString(args)); // args 数组中包含 [-g, ragnor]
    }

    // FocusSubPoint-005：What is array sort？
    public static void ArraySort(){
        // Topic-01: 如果想要对数值型的数组进行排序，可以使用Arrays类中的sort方法
        // Topic-02: sort方法使用的是优化的快排算法
        // Topic-03: 快排算法对于大多数的数据集合来说是效率都是比较高的
        // step-01: define
        Scanner my_scanner = new Scanner(System.in);
        System.out.println("please input your target max-boundary value：");
        int n = my_scanner.nextInt();
        double[] score = new double[n];

        // step-02: value
        for (int i = 0; i < score.length; i++){
            score[i] = Math.random();
        }

        // step-03: sort
        Arrays.sort(score);

        // step-04: traverse
        for (double ele: score){
            System.out.println(ele);
        }

    }

    // FocusSubPoint-006：What are Multidimensional array?
    public static void MultidimensionalArray(){
        // Topic-01: 多维数组将使用多个下标访问数组元素，它适用于表示表格或者更加复杂的排列形式
        // Topic-02: 二维数组也叫做矩阵：
        //           double[][] coordinate = new double[x_value][y_value]; 方式1
        //
        //           int[][] magicSquire = {
        //              {1, 2, 3, 3},
        //              {5, 6, 7, 8},
        //              {9, 10, 11, 12},
        //              {13, 14, 15, 16}
        //           }; 方式2，如果已经知道数组元素
        // Topic-03: 一旦数组被初始化，就可以利用两个方括号访问每个元素：coordinate[i][j]
        // Topic-03: for each循环语句不能自动处理二维数组的每一个元素=》因为它是按照行，也就是一堆数组处理的=》二维数组需要用循环嵌套
        // step-01: define
        int[][] mul_array = new int[3][3];

        // step-02: value
        for(int i = 0; i < mul_array.length; i++){
            for (int j = 0; j < mul_array[i].length; j++){
                mul_array[i][j] = i * j;
            }
        }

        // step-03: view by for-each-twice
        for (int[] row: mul_array){
            for (int column: row){
                System.out.print(column + "\t");
            }
            System.out.println();
        }

        // Topic-04: 调用Arrays.deepToString()可以快速的打印一个二维数组的数据元素列表。
        System.out.println(Arrays.deepToString(mul_array));


    }

    // FocusSubPoint-007：What is an irregular array?
    public static void IrregularArray(){
        // Topic-01: 尽管我们看java中的数组与其他程序设计语言中提供的数组没有多大的区别，但是实际上存在一些细微的差异：
        //           java实际上没有多维数组，只有一维数组 =》 多维数组本质上是：数组的数组
        //           从上一个MultidimensionalArray for循环你应该也能感受到端倪了

        // Topic-02: 不规则数组就是：数组的每一行都有不同的长度
        /*
        * 1
        * 2 2
        * 3 3 3
        * 4 4 4 4
        * 5 5 5 5 5
        * 6 6 6 6 6 6
        * */
        Scanner ragnor_scanner = new Scanner(System.in);
        System.out.println("please input row :");
        int row = ragnor_scanner.nextInt();
        System.out.println("please input col:");
        int colmn = ragnor_scanner.nextInt();

        int[][] irregular_array = new int[row][colmn];

        for (int i = 0; i < irregular_array.length; i++){
            for (int j = 0; j <= i; j++){   // j <= i 这个条件是关键
                irregular_array[i][j] = i;

            }
        }

        System.out.println(Arrays.deepToString(irregular_array));

    }

}
