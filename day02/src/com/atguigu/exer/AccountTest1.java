package com.atguigu.exer;

/**
 * 多线程不一定会有线程安全问题：取决于是否有共享数据！取决于是否都去操作共享数据！
 *
 * 例题：银行有一个账户。
 * 有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。
 * 问题：该程序是否有安全问题，如果有，如何解决？
 *
 *
 * @author 田沛勋
 * @create 2020-07-18 19:21
 */

class Account1{
    private double balance;

    public synchronized void deposit(double amt){
        //继承的多线程，使用的同步方法处理安全问题的锁是this(可能不唯一)
        // (此时多个线程共用一个Account对象this锁唯一),详见：java包下WindowTest2.java说明
        if(amt > 0){
            balance += amt;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ":存钱成功。余额为：" + balance);
        }
    }

}
class Customer1 extends Thread{
    private Account1 acct;

    public Customer1(Account1 acct) {
        this.acct = acct;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acct.deposit(1000);
        }
    }
}

public class AccountTest1 {
    public static void main(String[] args) {
        Account1 acct1 = new Account1();
        Customer1 c1 = new Customer1(acct1);
        Customer1 c2 = new Customer1(acct1);

        c1.setName("甲");
        c2.setName("乙");

        c1.start();
        c2.start();
    }
}
