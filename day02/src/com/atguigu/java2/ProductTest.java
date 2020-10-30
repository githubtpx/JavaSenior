package com.atguigu.java2;

/**
 * 线程通信的应用：经典例题：生产者/消费者问题
 *
 * 生产者(Productor)将产品交给店员(Clerk)，而消费者(Customer)从店员处取走产品，
 * 店员一次只能持有固定数量的产品(比如:20），如果生产者试图生产更多的产品，店员
 * 会叫生产者停一下，如果店中有空位放产品了再通知生产者继续生产；如果店中没有产
 * 品了，店员会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。
 *
 * 这里可能出现两个问题：
 * 生产者比消费者快时，消费者会漏掉一些数据没有取到。
 * 消费者比生产者快时，消费者会取相同的数据。
 *
 *
 *
 *
 * 分析下应用的技术：题目比较大
 * 1. 是否是多线程的问题？是，生产者的线程，消费者的线程
 * 2. 是否有共享数据？是，店员(或产品)
 * 3. 是否线程有安全问题？是
 * 4. 如何解决线程安全问题？同步机制，有三种方法
 * 5. 是否涉及到线程的通信？是，使用三个方法wait(),notify(),notifyAll()
 * @author 田沛勋
 * @create 2020-07-19 8:17
 */


class Clerk{
    private int productCount = 0;

    //生产产品：代表进货
    public synchronized void produceProduct() {

        if(productCount < 20){
            productCount++;
            System.out.println(Thread.currentThread().getName() + ":开始生产第" +productCount + "个产品");

            //生成者只要我们生成一个产品，就能够唤醒消费者
            notify();
        }else{

            //等待
            try {
                wait();//此时使用在同步方法中
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //消费产品：代表卖货
    public synchronized void consumeProduct() {//同步监视器
        if(productCount > 0){
            System.out.println(Thread.currentThread().getName() + ":开始消费第" +productCount + "个产品");
            productCount--;

            //消费者只要消费一个产品了，就把对方生产者唤醒，继续生产
            notify();
        }else{

            //等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}





class Producer extends Thread{//生产者线程：可以有多个的。使用继承的方式，无显式的父类。
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }
    @Override
    public void run() {
        //生产者线程：只做生产产品(没啥限制，一顿生成就完事了)
        System.out.println(getName() + ":开始生产产品......");

        while(true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.produceProduct();
        }
    }
}




class Consumer extends Thread{//消费者线程：可以有多个的。
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        //消费者线程：
        System.out.println(getName() + ":开始消费产品......");

        while(true){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.consumeProduct();
        }
    }
}


public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        //创建两个线程
        Producer p1 = new Producer(clerk);
        p1.setName("生产者1");


        Consumer c1 = new Consumer(clerk);
        c1.setName("消费者1");

        Consumer c2 = new Consumer(clerk);
        c2.setName("消费者2");

        p1.start();
        c1.start();
        c2.start();
    }
}
