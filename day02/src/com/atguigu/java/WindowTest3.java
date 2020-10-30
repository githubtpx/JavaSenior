package com.atguigu.java;

/**
 * 使用同步方法解决实现Runnable接口的线程安全问题
 *
 *
 * 关于同步方法的总结：
 * 1. 同步方法仍然涉及到同步监视器，只是不需要我们显示的声明。
 * 2. 非静态的同步方法，同步监视器是(对象)：this
 *    静态的同步方法，同步监视器是（对象）：当前类本身
 *
 *    原则：同步监视器唯一，多个线程共用一个同步监视器。
 *
 * @author 田沛勋
 * @create 2020-07-16 17:07
 */
class Window3 implements Runnable{
    private int ticket = 100;


    @Override
    public  void run() {//多个窗口出现：重复票
        while(true){

            show();

        }
    }


    private synchronized void show(){//方法二：同步方法的默认同步监视器：this，this唯一的
//        synchronized(this) {方法一
            if (ticket > 0) {

                try {
                    Thread.sleep(100);//有窗口出现：-1、0号票，错票
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);

//                try {
//                    Thread.sleep(100);//有窗口出现：-1、0号票，错票
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                ticket--;
            }

//        }
    }


}


public class WindowTest3 {
    public static void main(String[] args) {
        Window3 w = new Window3();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();

    }
}