package com.atguigu.javabak;

/**
 * 演示线程的死锁问题（线程的最终目的是死亡）
 *
 * 死锁产生：第一，看锁之间是否存在多个嵌套。  第二，又是多个线程有相互反嵌套。加入阻塞sleep()只是将死锁概率变大
 *
 * 1.死锁的理解：不同的线程分别占用对方需要的同步资源不放弃，
 * 都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
 *
 * 2.说明：
 * 1）出现死锁后，不会出现异常，不会出现提示，只是所有的线程都处于阻塞状态，无法继续
 * 2）我们使用同步时，要避免出现死锁。
 *
 * 3.解决方法
 * 专门的算法、原则（避开这个线程先调A再掉B的同步方法，另一个线程先调B在掉A的同步方法）
 * 尽量减少同步资源的定义（有些时候不需要同步就不要同步了，同步：效率低，可能死锁）
 * 尽量避免嵌套同步（只要嵌套就会有风险）
 *
 * @author 田沛勋
 * @create 2020-07-18 9:20
 */
public class ThreadTest {
    public static void main(String[] args) {

        //创建两个同步监视器/锁
        // 线程一：是嵌套关系，先握锁s1，再握锁s2；     线程二：是嵌套关系，先握锁s2，再握锁s1；
        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();

        //线程一：
        new Thread(){
            @Override
            public void run() {
                synchronized (s1){

                    s1.append("a");
                    s2.append("1");


                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized(s2){
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }

                }
            }
        }.start();


        //线程二：
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2){

                    s1.append("c");
                    s2.append("3");

                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized(s1){
                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }

                }

            }
        }).start();
    }
}
