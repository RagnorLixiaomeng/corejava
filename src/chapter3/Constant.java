package chapter3;

class Constant {
    // 1、如果希望某个常量可以在一个类中的多个方法中使用：类常量
    public static final double RAGNOR_LENGHT = 18.88;

    public static void main(String[] args){
        // 2、常量
        final double RAGNOR_HEIGHT = 17.00;

        String info = "length is:" + RAGNOR_LENGHT + "\n" +  "height is:" + RAGNOR_HEIGHT;
        System.out.println(info);

    }
}