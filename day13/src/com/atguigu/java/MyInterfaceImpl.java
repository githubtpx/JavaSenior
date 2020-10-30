package com.atguigu.java;

/**
 * @author 田沛勋
 * @create 2020-08-24 9:36
 */
public class MyInterfaceImpl implements MyInterface {
    @Override
    public void methodAbstract() {
    }

//    @Override
//    public void methodDefault(){
//        System.out.println("实现类重写了接口中的默认方法");
//
//    }

    public static void main(String[] args) {
        //接口中的静态方法只能由接口自己调用(即，接口中静态方法是自己要用的！)
        MyInterface.methodStatic();

        //接口的实现类不能调用接口中的静态方法的
//        MyInterfaceImpl.methodStatic();

        MyInterfaceImpl imp1 = new MyInterfaceImpl();
        imp1.methodDefault();

        //接口中私有方法不能在接口外部调用
//        imp1.methodPrivate();

    }

}
