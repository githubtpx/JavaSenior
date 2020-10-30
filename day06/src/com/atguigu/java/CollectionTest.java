package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Collection接口中声明的方法的测试
 *
 * 结论：
 * 向Collection接口的实现类的对象中，添加数据/对象obj时，要求obj所在类要重写equals()
 *
 * @author 田沛勋
 * @create 2020-07-30 20:03
 */
public class CollectionTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();//子接口List的具体实现类ArrayList测试
        coll.add(123);//自动装箱
        coll.add(456);
//        Person p = new Person("Jerry", 20);
//        coll.add(p);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);//自动装箱：Boolean

        //6. contains(Object obj)：判断当前集合中是否包含obj
        //我们在调用时候，会调用obj对象所在类的equals()。
        boolean contains = coll.contains(123);
        System.out.println(contains);//true

        System.out.println(coll.contains(new String("Tom")));//true,结果倒推，发现它调用equals()方法！
//        System.out.println(coll.contains(p));//true
        System.out.println(coll.contains(new Person("Jerry", 20)));//false --->true,Person类没有重写equals()方法

        //因为ArrayList有序的，先放谁有序。所以比较的时候。三次返回true
        // Person的equals().....
        //Person的equals().....
        //Person的equals().....
        //true


        //7. containsAll(Collection coll1)：判断形参coll1中的所有元素是否都存在于当前集合中。
        Collection coll1 = Arrays.asList(123,456);//返回List接口
        System.out.println(coll.containsAll(coll1));//true


    }

    @Test
    public void test2(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        //8. remove(Object obj):从当前集合中删除obj元素。(移除数据/对象obj时，要求obj所在类要重写equals())
        boolean remove = coll.remove(1234);
        System.out.println(coll);//[456, Person{name='Jerry', age=20}, Tom, false]

        coll.remove(new Person("Jerry", 20));
        System.out.println(coll);//[123, 456, Tom, false]

        //9. removeAll(Collection coll1)：差集：从当前集合中移除coll1中所有的元素
        Collection coll1 = Arrays.asList(123,4567);
        coll.removeAll(coll1);
        System.out.println(coll);//[456, Tom, false]

    }

    @Test
    public void test3(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        //10.retainAll(Collection coll1)：交集：并修改当前的集合。获取当前集合和coll1集合的交集，并修改当前集合
//        Collection coll1 = Arrays.asList(123,456,789);
//        coll.retainAll(coll1);
//        System.out.println(coll);//[123, 456]

        //11. equals(Object obj)：要想返回true,需要当前集合和形参集合的元素都相同。
        Collection coll1 = new ArrayList();
        coll1.add(123);
        coll1.add(456);
        coll1.add(new Person("Jerry", 20));
        coll1.add(new String("Tom"));
        coll1.add(false);
        System.out.println(coll.equals(coll1));

    }

    @Test
    public void test4(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        //12. hashCode()：返回当前对象的哈希值
        System.out.println(coll.hashCode());

        //13. 集合 ---> 数组：toArray()
        Object[] arr = coll.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        //拓展：数组  ---> 集合(List - 动态数组)：调用Arrays工具类的静态方法asList(可变形参)
        List<String> list = Arrays.asList(new String[]{"AA", "BB", "CC"});//形参是可变形参，底层是数组
        System.out.println(list);

        List arr1 = Arrays.asList(new int[]{123, 456});//把整个当成一个元素
//        List arr1 = Arrays.asList(123,456);
        System.out.println(arr1.size());//1

        List arr2 = Arrays.asList(new Integer[]{123, 456});//把整个当成一个元素
//        List arr1 = Arrays.asList(123,456);
        System.out.println(arr2.size());//2


        //14. iterator()：返回Iterator接口的实例，用于遍历集合元素。放在IteratorTest.java中测试

    }
}
