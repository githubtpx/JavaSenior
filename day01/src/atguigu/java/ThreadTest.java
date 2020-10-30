package atguigu.java;

/**
 * 多线程的创建，方式一：继承于Thread类
 * 1. 创建一个继承于Thread类的子类
 * 2. 重写Thread类的run() --> 将此线程执行的操作声明在run()中
 * 3. 创建Thread类的子类的对象
 * 4. 通过此对象调用Thread父类中start()
 * <p>
 * 例子：遍历100以内的所有的偶数
 *
 * @author 田沛勋
 * @create 2020-07-09 15:42
 */

//1. 创建一个继承于Thread类的子类
class MyThread extends Thread {
    //2. 重写Thread类的run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName() +":" + i);
            }
        }

    }
}


public class ThreadTest {
    public static void main(String[] args) {//如下操作仍然是在main主线程中执行的：
        //3. 创建Thread类的子类的对象
        MyThread t1 = new MyThread();//alt + enter替换eclipse中的ctrl + 1
        //4. 通过此对象调用start()方法：① 启动/执行当前线程 ② 调用当前线程(父类)的run()
        //Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.
        t1.start();


        //问题一：我们不能通过直接使用，Thread类的子类的对象调用run()的方式启动线程
//        t1.run();//此时根本没有启动当前线程。就没有多分出来一个线程开始启动。


        //问题二：再启动一个线程，遍历100以内的偶数。不可以还让已经start()的线程去执行。会报IllegalThreadStateException
//        t1.start();
        //我们需要重新创建一个线程的对象
        MyThread t2 = new MyThread();
        t2.start();


        //如下操作仍然是在main主线程中执行的。
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName() +":" + i + "**********************");
            }
        }
    }

}