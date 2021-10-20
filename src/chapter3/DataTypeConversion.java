package chapter3;


/*有一张图，表示了类型转换，由低到高就是自动类型转换，由高到低就是强制类型转换，转换过程中数据精度丢失的有3种情况
*
* 自动类型转换：byte =》 short =》 int =》long
* float double char
*
*
/
 */
public class DataTypeConversion {
    public static void main(String[] args){

        byte a = 1;
        short b = a; // 自动类型转换
        System.out.println(b);

        double x = 9.997;
        int nx = (int)x; // 1、强制类型转换
        int nx2 = (int)Math.round(x); // 2、强制类型转换后取整
        byte nx3 = (byte) 300; // 实际数值就是44，因为200超出了byte数据类型的表示范围

    }
}

