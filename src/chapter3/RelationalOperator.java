package chapter3;

public class RelationalOperator {

    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        // type1: > < = != && || 短路与 短路或
        if (x != 0 && 1 / x > x + y){
            System.out.println("success");
        }
        // type 2: 三元运算符 condition ? expression1 : expression2
        // x < y ? x : y 会返回两者中的最小的

        // type 3： 位运算符：位运算符是对byte、 short、 int、 long和char这5类数据类型进行运算的
        //          与& 或| 异或^ 取反~ 左移<< 待符号右移>> 无符号右移>>>
        byte b_1 = 1;
        byte b_2 = 0;
        System.out.println(b_1 & b_2);
    }
}
