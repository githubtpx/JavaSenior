package com.atguigu.java2;
import java.util.concurrent.*;

/**
 * 创建多线程的方式四：使用线程池
 *
 * 好处：
 * 1.提高响应速度（减少了创建新线程的时间）
 * 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 * 3.便于线程管理（调用ThreadPoolExecutor对象的相关set + 下面属性）
 *     corePoolSize：核心池的大小
 *     maximumPoolSize：最大线程数
 *     keepAliveTime：线程没有任务时最多保持多长时间后会终止
 *
 * 面试题：创建多线程有几种方式？四种
 *
 *
 *
 *一、获取此对象service哪个类造的？
 * service.getClass();
 *
 * @author 田沛勋
 * @create 2020-07-19 11:05
 */

class NumberThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class NumberThread1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}


class NumSum implements Callable {

    //2.实现回调call()方法，将此线程需要执行的操作声明在call()方法中，且有返回值的！
    @Override
    public Object call() throws Exception {//call()回调方法

        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" +i);
                sum += i;
            }
        }
        return sum;//int转换为Integer，int自动装箱，多态
    }
}

public class ThreadPool {
    public static void main(String[] args) {

        //1.提供指定线程数量的线程池：ExecutorService真正的线程池接口，该工具类的方法返回的是一个接口的实现类对象(多态)
        ExecutorService service = Executors.newFixedThreadPool(100);//多态

        ThreadPoolExecutor service1 = (ThreadPoolExecutor)service;


        //4.设置线程池的属性
//        System.out.println(service.getClass());//获取这个service对象是哪个类造的
        service1.setCorePoolSize(15);
//        service1.setKeepAliveTime();


        //2.执行指定的线程的操作。提供一个实现Runnable接口或Callable接口实现类的对象
        service1.execute(new NumberThread());//适合使用于Runnable
        service1.execute(new NumberThread1());//适合使用于Runnable
//        service1.submit(new NumSum());//适合使用于Callable


        //3.关闭线程池
        service1.shutdown();

    }
}
