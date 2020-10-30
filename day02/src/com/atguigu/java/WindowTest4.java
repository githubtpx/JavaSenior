package com.atguigu.java;

/**
 * 使用同步方法处理继承Thread类的方式中的线程安全问题
 *
 *
 * @author 田沛勋
 * @create 2020-07-16 17:52
 */
class Window4 extends Thread {

    //类变量：共享
    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {

            //Class clazz = Window2.class;只会加载一次，即Window2.class是Class类的对象
            //错误的方式：this代表着t1,t2,t3三个对象
            show();

        }
    }

    private static synchronized void show(){//此时同步方法的同步监视器唯一：Window4.class当前类(也是对象)
//    private synchronized void show(){//默认同步监视器为this，此时this不唯一，代表着t1,t2,t3三个对象。错误的方式。
        if (ticket > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ":卖票，票号为" + ticket);//拿对象调用
            ticket--;
        }
    }


}

public class WindowTest4 {
    public static void main(String[] args) {
        Window4 w1 = new Window4();
        Window4 w2 = new Window4();
        Window4 w3 = new Window4();

        w1.setName("窗口一");
        w2.setName("窗口二");
        w3.setName("窗口三");

        w1.start();
        w2.start();
        w3.start();
    }
}
