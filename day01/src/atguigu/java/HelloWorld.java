package atguigu.java;

import java.util.Arrays;

public class HelloWorld {

    static int num = 10;
    public static final int NUMBER = 1;


    public static void main(String[] args) {
        System.out.println(123);
        System.out.println(123);

        System.out.println("HelloWorld.main");//soutm,打印字符串
        System.out.println("args = " + Arrays.deepToString(args));//soutp,打印参数

        System.out.println("args = " + args);//静态的方法不能够调用非静态的属性。
        System.out.println("num = " + num);//soutv,打印变量
    }


}




