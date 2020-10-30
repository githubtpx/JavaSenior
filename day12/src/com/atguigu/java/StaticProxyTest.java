package com.atguigu.java;

/**
 * 静态代理举例
 *
 * 代理类和被代理类在编译期间，就确定下来了。
 *
 * @author 田沛勋
 * @create 2020-08-19 17:34
 */
interface ClothFactory{
    void produceCloth();
}

//代理类
class ProxyClothFactory implements ClothFactory {

    private ClothFactory factory;//用被代理类对象进行初始化

    public ProxyClothFactory(ClothFactory factory){
        this.factory = factory;
    }

    //
    @Override
    public void produceCloth() {
        System.out.println("代理工厂做一些准备工作");

        factory.produceCloth();

        System.out.println("代理工作做一些后续的收尾工作");
    }

}

//被代理类
class NikeClothFactory implements ClothFactory{

    @Override
    public void produceCloth() {
        System.out.println("Nike工厂生产一批运动服");
    }
    
}


public class StaticProxyTest {
    public static void main(String[] args) {
        //创建被代理类的对象
        ClothFactory nike = new NikeClothFactory();
        //创建代理类对象
        ClothFactory proxyClothFactory = new ProxyClothFactory(nike);

        proxyClothFactory.produceCloth();

    }

}
