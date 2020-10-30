package com.atguigu.java;

/**
 * 商品类
 * @author 田沛勋
 * @create 2020-07-26 17:57
 */
public class Goods implements Comparable{
    private String name;
    private double price;

    public Goods() {
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }



    /*
    重写comparaTo(obj)方法的规则：
       如果当前对象this大于形参对象obj，则返回正整数，
       如果当前对象this小于形参对象obj，则返回负整数，
       如果当前对象this等于形参对象obj，则返回零。
     */
    //指明商品类，比较大小的方式：先，按照价格从低到高排序；再，按照产品名称从高到低排序(二级排序)
    @Override
    public int compareTo(Object o) {

        System.out.println("******************");
        if(o instanceof  Goods){
            Goods goods = (Goods)o;
            //方式一：
            if(this.price > goods.price){
                return 1;
            }else if(this.price < goods.price){
                return -1;
            }else{
//                return 0;
                return -this.name.compareTo(goods.name);
            }

            //方式二：
//            return Double.compare(this.value, goods.value);
        }
//        return 0;
        throw new RuntimeException("传入的数据类型不一致！");//运行时异常可以不做处理，编译需要处理转换为运行时。
    }
}
