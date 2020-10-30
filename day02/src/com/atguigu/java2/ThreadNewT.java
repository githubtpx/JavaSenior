package com.atguigu.java2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建多线程的方式三：实现Callable接口  ----JDK5.0新增
 *
 * 如何理解实现Callable接口的方式创建多线程  比  实现Runnable接口方式创建多线程的方式强大？
 * 1. call()方法可以有返回值的。
 * 2. call()方法可以抛出异常，被外面的操作捕获，获取异常的信息。
 * 3. Callable是支持泛型的
 *
 * @author 田沛勋
 * @create 2020-07-19 9:31
 */

//1.创建实现Callable接口的实现类
class NumThread1 implements Callable<Integer>{

    //2.实现回调call()方法，将此线程需要执行的操作声明在call()方法中，且有返回值的！
    @Override
    public Integer call() throws Exception {//call()回调方法

        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if(i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;//int转换为Integer，int自动装箱，多态
    }
}


public class ThreadNewT {
    public static void main(String[] args) {
        //3.创建Callable接口实现类的对象。
        NumThread1 NumThread1 = new NumThread1();

        //4.将此Callable接口实现类对象作为参数，传递到FutureTask构造器中，创建FutureTask类的对象。
        FutureTask<Integer> futureTask = new FutureTask<Integer>(NumThread1);

        //5.将FutureTask类的对象作为参数传递到Thread类的构造其中，创建Thread类对象，并调用start()。
        new Thread(futureTask).start();

        //启动线程:FutureTask类实现了Runnable、Future接口



        try {
            //6.获取Callable中的call()方法的返回值。
            //get()返回值：即为FutureTask构造器参数Callable接口实现类对象，重写的回调方法call()的返回值。
            //get()方法只是为了得到call()方法的返回值
            Integer sum = futureTask.get();//得到Callable实现类中的call()方法的返回值
            System.out.println("总和为" +sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
