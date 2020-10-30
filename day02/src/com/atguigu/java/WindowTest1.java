package com.atguigu.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张。使用实现Runnable接口的方式
 *
 * 1.问题：卖票过程中，出现了重票、错票      --->出现了线程的安全问题
 * 2.问题出现的主要原因：当某个线程操作车票的过程中，尚未操作完成时，其它线程参与进来，也操作车票。
 * 3.如何解决： 当一个线程a在操作共享数据ticket时候，其它线程不能参与进来。直到线程a操作完成共享数据ticket之后，其它
 *             线程才可以操作共享数据ticket。这种情况即使线程a出现了阻塞，也不能被改变。
 *
 * 4.在Java中，同门通过同步机制(同步：加了锁)，来解决线程的安全问题。
 *
 *   多线程不一定会有线程安全问题：取决于是否有共享数据！取决于是否都去操作共享数据！
 *
 *  方式一：同步代码块(/'sɪŋkrənaɪzd/ synchronized)
 *   synchronized(同步监视器){
 *          //需要被同步的代码
 *
 *   }
 *   说明：1.操作共享的代码，即为需要被同步的代码   ---不能包含代码多了，也不能包含代码少了！(多：可能导致一个线程自己玩)
 *        2.共享数据：多个线程共同操作的变量。比如：ticket就是共享数据(多个线程之间没有共享数据，就不会出现线程安全问题)
 *        3.同步监视器，俗称：锁。任何一个类的对象，都可以充当锁。
 *             要求：多个线程必须要共用同一把锁。------这个锁就一个
 *
 *       补充：在实现Runnable接口创建多线程的方式中，我们可以考虑使用this充当同步监视器。
 *       原则：同步监视器唯一的。多个线程必须要共用同一把锁。
 *
 *
 *
 *  方式二：同步方法
 *      如果操作共享数据的代码完整的声明在一个方法中，我们不妨将此方法声明为同步的。
 *
 *      关于同步方法的总结：
 *      1. 同步方法仍然涉及到同步监视器，只是不需要我们显示的声明。
 *      2. 非静态的同步方法，同步监视器是(对象)：this
 *          静态的同步方法，同步监视器是（对象）：当前类本身
 *
 *          原则：同步监视器唯一，多个线程共用一个同步监视器。
 *
 *
 *
 *
 *  5.同步的方式，解决了线程的安全问题。 ---好处
 *    操作同步代码时，只能有一个线程参与，其它线程等待。相当于是一个单线程的过程，效率低。还有死锁 ---局限性
 *
   第一想有无共享数据？是谁？第二想操作共享的代码有哪些？
   多个线程去抢(状态为未锁的)同步代码块的同步监视器/锁，谁抢到了，(修改锁的状态锁住)并执行同步代码块内容，
   等执行完同步代码块的代码出来。此时需要释放同步监视器/锁(状态为未锁的)。多个线程再去抢，循环往复！
 *
 *
 * @author 田沛勋
 * @create 2020-07-10 11:22
 */
class Window1 implements Runnable{
    private int ticket = 100;

//    Object obj = new Object();
    Dog dog = new Dog();

    @Override
    public void run() {//多个窗口出现：重复票
//        Object obj = new Object();//此时多个线程没有共用同一把锁。一个线程一个锁
        while(true){
            synchronized(this){//此时的this：唯一的Window1的对象  //方式二：synchronized(dog) {//obj锁，同步监视器
//                while(true){ //同步代码块中包含代码多了，可能导致一个线程自己玩

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
                } else {
                    break;
                }
            }
        }

    }
}


public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w = new Window1();

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


class Dog{

}