package com.atguigu.exer;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程不一定会有线程安全问题：取决于是否有共享数据！取决于是否都去操作共享数据！
 *
 * 例题：银行有一个账户。
 * 有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。
 * 问题：该程序是否有安全问题，如果有，如何解决？
 *
 *
 *
 * 分析：
 * 1.是否是多线程的问题？是，两个储户线程
 * 2.是否有共享数据？有，账户（账户的余额）
 * 3.是否有线程安全问题？有
 * 4.需要考虑如何解决线程安全问题？同步机制：有三种方式
 *
 * @author 田沛勋
 * @create 2020-07-18 11:35
 */

class Account{
    private double balance;//共享数据

    public Account(double balance) {
        this.balance = balance;
    }

    private ReentrantLock lock = new ReentrantLock();//实现Lock接口
    //存钱
    public void deposit(double amt){//方法一：synchronized同步方法
        //继承的多线程，使用的同步方法处理安全问题的锁是this(可能不唯一)
        // (此时多个线程共用一个Account对象this锁唯一),详见：java包下WindowTest2.java说明

        //方法二：Lock方式
        try {
            lock.lock();

            if(amt > 0 ){
                balance += amt;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + ":存钱成功。余额为：" + balance);
            }
        }finally {
            lock.unlock();
        }
    }
}




class Customer extends Thread{
    private Account acct;

    public Customer(Account acct) {
        this.acct = acct;
    }

    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {
            acct.deposit(1000);
        }

    }
}




public class AccountTest {
    public static void main(String[] args) {
        Account acct = new Account(0);

        //此时两个线程共用一个账户(巧妙)
        Customer c1 = new Customer(acct);
        Customer c2 = new Customer(acct);

        c1.setName("甲");
        c2.setName("乙");

        c1.start();
        c2.start();


    }
}













