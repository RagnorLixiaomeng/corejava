package chapter3;

public class DataCategory {
    public static enum Size {SMALL, MEDIUM, LARGE};  // type five : enum

    public static void main(String[] args){
        // 8 = 4 + 2 + 1 + 1
//        /** First Type: 整型 4 ==》 byte short int long
//         * 通常情况下，int 类型最常用。
//         * 但是如果想表示星球上居住的人数就是要用到long类型
//         * byte 和 short 类型主要用于特定的应用场合：底层文件处理、 控制占用存储空间量的大数组。
//         * */
        byte a = 127;
        short b = 32767;
        int c = 2147483647;
        long d = 9223372036854775807L;

//        /** Second Type: 浮点型
//         * float 浮点型结尾带个F/f
//         * double 的数值精度是float的两倍，所以也成为双精度浮点型
//         */
        float e = 1.2F;
        double f = 1.234;

//        /** Three Type: char类型 */
        char g = 'c';

//        /** Four Type: boolean类型* /
        boolean Flag = true;
        System.out.println(Flag);
        System.out.println(Size.MEDIUM);
    }

}





