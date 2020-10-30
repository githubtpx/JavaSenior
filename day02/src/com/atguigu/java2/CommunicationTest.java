package com.atguigu.java2;

/**
 * 线程通信的例子：使用两个线程打印1-100。线程1, 线程2 交替打印
 *
 * 线程通信涉及到的三个方法：
 * wait():一旦执行此方法，当前线程就进入阻塞状态，并释放该线程的同步监视器
 * notify():一旦执行此方法，就会唤醒被wait()的一个线程。如果多个线程被wait,就唤醒优先级高的那个。
 * notifyAll():一旦执行此方法，就会唤醒所有被wait的线程
 *
 * 说明：
 * 1.wait()，notify()，notifyAll()这三个方法表示线程通信时候，必须使用在同步代码块或同步方法中(Lock不行)
 * 2.wait()，notify()，notifyAll()这三个方法的调用者必须是同步代码块或同步方法中的同步监视器。
 *   否则，会出现IllegalMonitorStateException(不合法的监视器状态异常)
 * 3.wait()，notify()，notifyAll()这三个方法是定义在java.lang.Object类中
 *   因为：
 *       必须保证任何一个对象充当同步监视器时候，能够去调用上面三个通信的方法。
 *
 * 4.notify();//不是静态方法，省略是this。是静态方法，省略是当前类。
 *
 *
 * 面试题：sleep()和wait()的异同？
 * 1.相同点：一旦执行方法，都可以使得当前的线程进入阻塞状态。
 * 2.不同点：1）两个方法声明的位置不一样：Thread类中声明sleep()，Object类中声明的wait()
 *          2）调用的要求不同：sleep()可以在任何需要的场景下调用。wait()必须使用在同步代码块和同步方法中。
 *              (它必须由同步监视器调用)
 *          3) 关于是否释放同步监视器：如果两个方法都使用在同步代码块或者同步方法中，sleep()方法不会释放同步监视器。
 *              wait()方法会释放同步监视器。
 *
 *
 * wait():1) 当一个线程运行时，wait()操作一下就进入阻塞状态。
 *        2) 一旦执行wait()会释放锁/同步监视器
 *        恢复就绪调用notify()唤醒一个,notifyAll()唤醒很多个
 *        线程2把线程1唤醒，称为就绪状态。只唤醒一个notify().
 *        使得调用wait()方法的线程进入阻塞状态。
 *
 * @author 田沛勋
 * @create 2020-07-18 22:07
 */

class Number implements Runnable{
    private int number = 1;//多线程的共享数据
    private Object obj = new Object();

    @Override
    public void run() {

        while(true){

            synchronized (obj) {
                //线程2把线程1唤醒，称为就绪状态。只唤醒一个notify()
                obj.notify();//不是静态方法，省略是this。是静态方法，省略是当前类。

                //操作共享数据代码
                if(number <= 100){

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;

                    try {
                        //使得调用wait()方法的线程进入阻塞状态
                        obj.wait();//当一个线程运行时，wait()操作一下就进入阻塞状态。恢复就绪调用notify(),notifyAll()
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }else{
                    break;
                }

            }

        }

    }
}


public class CommunicationTest {
    public static void main(String[] args) {
        Number num = new Number();
        Thread t1 = new Thread(num);
        Thread t2 = new Thread(num);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }
}
