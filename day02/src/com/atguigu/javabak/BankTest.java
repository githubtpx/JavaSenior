package com.atguigu.javabak;

/**
 *
 * 使用同步机制将单例模式中的懒汉式改写为线程安全的：
 *      （每个线程只使用过该类的唯一一个对象，即只new过一次安全）
 *
 *
 *
 * 锁一定是对象：
 * 1.“概念”可以解释说明一个“词”，但“概念”本身也是一个“词”。
 * 2.“类”本身可以“造对象”，"类"本身也是个"对象"。反射！
 *
 * @author 田沛勋
 * @create 2020-07-16 18:09
 */
public class BankTest {

}



class Bank{
    //1.私化类的构造器
    private Bank(){

    }
    //2.声明当前类对象，没初始化
    //4.此对象也必须声明为static的,逆向思维：因为返回一个对象的方法是static，其操作的属性也是static的。
    private static Bank instance = null;

    //3.声明public、static的返回当前类对象的方法
    public static Bank getInstance(){

//    方式一：synchronized，静态的同步方法的锁是当前类本身(因为类本身充当对象)
//    public static synchronized Bank getInstance(){

        //方式二：效率稍差，同步代码块
//        synchronized (Bank.class) {
//            if(instance == null){
//
//                instance = new Bank();
//            }
//            return instance;
//        }

        //方式三：效率更高，同步代码块
        if(instance == null) {//再外面挂了一个是否存在的牌子。第二次有人来就效率高，不用再进去了判断了。
            synchronized (Bank.class) {
                if (instance == null) {

                    instance = new Bank();
                }
            }
        }
        return instance;//没有操作共享数据
    }

}

