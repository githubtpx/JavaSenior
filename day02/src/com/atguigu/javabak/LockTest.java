package com.atguigu.javabak;
import java.util.concurrent.locks.ReentrantLock;

//ctrl + alt + /  看有几个构造器
/**
 * 解决线程安全问题的方式三：Lock锁（接口，我们使用它的实现类）   ---JDK5.0新增
 *
 * 1. 面试题：synchronized 与 Lock方式的异同？（操作共享的代码，即为需要被同步的代码）
 *    同：    两者都可以解决线程安全问题
 *    不同：  synchronized机制在执行完相应的同步的代码以后，自动释放同步监视器
 *           Lock需要手动的启动同步（lock()），同时结束同步也需要手动的实现（unlock()）
 *
 *    ReentrantLock对像lock可以理解为同步监视器，但它不是
 *
 * 2. 优先使用顺序：
 * Lock 同步代码块（已经进入了方法体，分配了相应资源）同步方法（在方法体之外）
 *
 * 3. 面试题：如何解决线程安全问题？有几种方式
 *
 * Lock是一个接口，我们使用它的实现类 ReentrantLock（再进去的），/riː'entrənt/ 的对象
 *
 *
 * @author 田沛勋
 * @create 2020-07-18 10:12
 */
//1.先创建多个线程，才会出现线程安全问题
class Window implements Runnable{
    private int ticket = 100;

    //1.实例化ReentrantLock(再进去的锁)
    private ReentrantLock lock = new ReentrantLock(true);//不写默认为false
    //fair:true，为公平的Lock，每个线程有先后顺序，先进先出，同步资源按顺序执行。

    @Override
    public void run() {

        while(true){

            try {

                //2.调用锁定方法：lock()
                lock.lock();

                if(ticket > 0){

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":售票，票号为：" + ticket);
                    ticket--;
                }else{
                    break;
                }
            }finally {
                //3.调用解锁方法：unlock()
                lock.unlock();
            }

        }

    }
}



public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();

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
