package com.atguigu.java;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一、说明：Java中的对象，正常情况下，只能进行比较运算符的操作： ==  或  ！= 。和 = 。不能使用 > 或者 < 的
 *          但是在开发的场景中，我们需要对多个对象进行排序，言外之意，就需要比较对象的大小(>  <)
 *          如何实现比较对象的大小？我们使用两个接口中任何一个：Comparable 或者 Comparator
 *
 * 如何实现比较对象的大小？我们使用下面两个接口，比较对象大小。默认认为你和Comparable接口打交道！
 * 二、 Comparable接口的使用 ---自然排序
 * 三、 Comparator接口的使用 ---定制排序
 *
 * 四、Comparable接口与Comparator接口的使用对比：
 *     Comparable接口的方式一旦指定，保证Comparable接口实现类的对象在任何位置都可以比较大小。
 *     Comparator接口属于临时性的比较。(什么时候需要定制排序，你就临时创建一个Comparator接口的实现类去完成比较对象)
 *
 *
 *
 *
 * @author 田沛勋
 * @create 2020-07-26 16:57
 */
public class CompareTest {
    /*
    Comparable接口的使用举例：  自然排序(默认排序考虑Comparable)
    自然排序：要排序的对象所属的类要实现Comparable接口
    1. 像String、包装类、Date类等实现了Comparable接口，重写了comparaTo()方法，给出了比较两个对象大小的方式。
    2. 像String、包装类重写comparaTo()方法以后，Arrays.sort()默认，进行了从小到大的排列
    3. 重写comparaTo(obj)方法的规则：
       如果当前对象this大于形参对象obj，则返回正整数，
       如果当前对象this小于形参对象obj，则返回负整数，
       如果当前对象this等于形参对象obj，则返回零。
+
    4. 对于自定义类来说，如果需要排序，我们可以让自定义类实现Comparable接口，重写comparaTo()方法。
        在comparaTo()方法中指明如何排序。



     */
    @Test
    public void test1(){
        //因为String类实现Comparable接口：重写comparaTo()
        String[] arr = new String[]{"AA","CC","MM","GG","JJ","DD"};

        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));

    }

    @Test
    public void test2(){
        Goods[] arr = new Goods[5];
        arr[0] = new Goods("lenovoMouse", 34);
        arr[1] = new Goods("dellMouse", 43);
        arr[2] = new Goods("xiaomiMouse", 12);
        arr[3] = new Goods("huaweiMouse", 65);
        arr[4] = new Goods("microsoftMouse", 43);

        //相当于Goods类告诉调用sort()方法的结构Arrays，你去按照我商品类重写的比较大小的方法的规则，比较大小就行了！
        Arrays.sort(arr);//sort(),比较对象，默认认为你和Comparable接口打交道！

        System.out.println(Arrays.toString(arr));//com.atguigu.java.Goods cannot be cast to java.lang.Comparable
        //[Goods{name='xiaomiMouse', price=12.0}, Goods{name='lenovoMouse', price=34.0}, Goods{name='microsoftMouse', price=43.0}, Goods{name='dellMouse', price=43.0}, Goods{name='huaweiMouse', price=65.0}]
    }



    /*
    Comparator接口的使用：定制排序(自己临时定义一种排列方式:临时的需要按照某种方式进行对象的排序)
    该实现类是临时用的！
    1.背景：
        当要排序的元素的类型没有实现java.lang.Comparable接口而又不方便修改代码，
        或者实现了java.lang.Comparable接口的排序规则不适合当前的操作，
        那么可以考虑使用 Comparator 的对象来排序

    2.重写compare(Object o1,Object o2) 方法，比较 o1 和 o2 的大小：
      如果方法返回正整数，则表示 o1 大于 o2 ；
      如果返回 0 ，表示相等；返回负整数，表示o1 小于 o2

    使用比较器的对象：
    Arrays.sort(goods,com);//产品goods数组。比较器的对象com，它里边放的是前面产品数组中的元素这些对象。
    Collections.sort(coll,com);
    new TreeSet(com);
     */
    @Test
    public void test3(){
        String[] arr = new String[]{"AA","CC","MM","GG","JJ","DD"};
        Arrays.sort(arr,new Comparator(){

            //String的定制排序：按照字符串从大到小的顺序排列
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof String && o2 instanceof String){
                    String s1 = (String) o1;
                    String s2 = (String) o2;
                    return -s1.compareTo(s2);
                }
                throw new RuntimeException("输入的数据类型不一致！");
            }
        });

        System.out.println(Arrays.toString(arr));

    }

    @Test
    public void test4(){
        Goods[] arr = new Goods[6];
        arr[0] = new Goods("lenovoMouse", 34);
        arr[1] = new Goods("dellMouse", 43);
        arr[2] = new Goods("xiaomiMouse", 12);
        arr[3] = new Goods("huaweiMouse", 65);
        arr[4] = new Goods("huaweiMouse", 224);
        arr[5] = new Goods("microsoftMouse", 43);

//        Arrays.sort(arr);//默认按照自然排序的方式实施的
        Arrays.sort(arr, new Comparator() {

            //指明商品类，比较大小的方式：先，按照产品名称从低到高排序；再，按照价格从高到低排序(二级排序)

            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Goods && o2 instanceof Goods){
                    Goods g1 = (Goods) o1;
                    Goods g2 = (Goods) o2;
                    if(g1.getName().equals(g2.getName())){
                        return -Double.compare(g1.getPrice(), g2.getPrice());
                    }else{
                        return g1.getName().compareTo(g2.getName());
                    }
                }

                throw new RuntimeException("输入的数据类型不一致！");
            }

        });

        System.out.println(Arrays.toString(arr));

    }

}
