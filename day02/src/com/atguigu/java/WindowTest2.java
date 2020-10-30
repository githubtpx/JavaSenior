package com.atguigu.java;
/**
 * 使用同步代码块解决继承Thread类的方式的线程安全问题。
 *
 * 例子：创建三个窗口卖票，总票数为100张。使用继承Thread类的方式
 *
 * 说明：在继承Thread类创建多线程的方式中，慎用this充当同步监视器(因为this可能不唯一)。
 *      可以考虑当前类Window2.class充当同步监视器。
 *
 *      原则：同步监视器唯一的。多个线程必须要共用同一把锁。
 *
 *
 * @author 田沛勋
 * @create 2020-07-10 10:10
 */


class Window2 extends Thread{

    //类变量：共享
    private static int ticket = 100;
    private static Object obj =new Object();

    @Override
    public void run() {
        while(true){
            //正确的
            //synchronized(obj) {

            //当前类充当同步监视器，类也是对象(一切皆对象)
            synchronized(Window2.class){//Class clazz = Window2.class;只会加载一次，即Window2.class是Class类的对象

                //错误的方式：this代表着t1,t2,t3三个对象
                //synchronized(this){

                if (ticket > 0) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":卖票，票号为" + ticket);
                    ticket--;
                } else {
                    break;
                }

            }
        }
    }
}

public class WindowTest2 {
    public static void main(String[] args) {
        Window2 w1 = new Window2();
        Window2 w2 = new Window2();
        Window2 w3 = new Window2();

        w1.setName("窗口一");
        w2.setName("窗口二");
        w3.setName("窗口三");

        w1.start();
        w2.start();
        w3.start();
    }
}
