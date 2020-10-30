package atguigu.java;

/**
 * 测试Thread类中的常用方法
 * 1. start():启动当前线程；调用当前线程的run()
 * 2. run():  通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法体中。(你这个线程到底干什么)
 * 3. currentThread(): 静态方法，返回执行当前代码的线程
 * 4. getName(): 获取当前线程的名字
 * 5. setName():设置当前线程的名字
 * 6. yield(): 一旦线程执行此方法，即释放当前CPU的线程的执行权。暂停当前正在执行的线程，把执行机会让给优先级相同或更高的线程。（yield to 屈服）
 * 7. join(): 在线程a中调用线程b的join()方法，此时线程a进入阻塞状态，直到线程b完全执行完以后，
 *            线程a才结束阻塞状态。   接下来看CPU什么时候去给你分配资源，分配到资源后，你就接着往后去执行！
 * 8. stop(): 已过时，当执行此方法时，强制结束当前线程。
 * 9. sleep(long milliseconds): 1秒 = 1000毫秒。让当前线程"睡眠"(阻塞)指定的milliseconds毫秒。
 *    在指定的milliseconds毫秒时间内当前线程是阻塞状态。
 *    接下来看CPU什么时候去给你分配资源，分配到资源后，你就接着往后去执行！
 * 10. isAlive(): 判断当前线程是否存活。
 *
 *
 * 线程的优先级：
 * 1.
 * MAX_PRIORITY：10
 * MIN _PRIORITY：1
 * NORM_PRIORITY：5  --->默认的优先级
 * 2. 如何设置当前线程的优先级：
 *      getPriority()：获取线程的优先级
 *      setPriority(int p)：设置线程的优先级
 *
 *      说明：高优先级的线程要 抢占 低优先级的线程CPU的执行权。但是只是从概率上讲，高优先级的线程高概率的情况下
 *            被执行。并不意味着只有当高优先级的线程执行完以后，低优先级的线程才执行。
 *
 * @author 田沛勋
 * @create 2020-07-09 17:57
 */
class HelloThread extends Thread{
    @Override
    public void run() {
        //子类重写的方法抛出的异常不能大于父类抛出的异常，否则参数为多态父类就处理不了子类的异常了。
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){

//                try {
//                    sleep(10);//静态方法
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                System.out.println(getName() + ":" + getPriority() + ":"+i );
            }

//            if(i % 20 == 0){
//                this.yield();//Thread.currentThread().yield()
//            }
        }

    }

    public  HelloThread(String name){
        super(name);
    }


}


public class ThreadMethodTest {
    public static void main(String[] args) {

        HelloThread h1 = new HelloThread("Thread：1");
//        h1.setName("线程一");
        //设置分线程的优先级
        h1.setPriority(Thread.MAX_PRIORITY);//Thread.MAX_PRIORITY = 10

        h1.start();

        //给主线程命名
        Thread.currentThread().setName("主线程");
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" +i);
            }

//            if(i  == 20){
//                try {
//                    h1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

        }


        System.out.println(h1.isAlive());

    }
}
