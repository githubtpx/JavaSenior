package com.atguigu.java1;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 1. Set接口的框架(Linked底层是链表结构)
 *    使用Set接口充当过滤重复数据的工具
 * -----Collection接口：单列集合，用来存储一个一个的对象
 *      |----Set接口：存储无序的、不可重复的数据   --->高中讲的“集合”(无序性，确定性，互异性(不可重复性))
 *            |---HashSet：作为Set接口的主要实现类；线程不安全的；可以存储null值
 *                  |---LinkedHashSet：作为HashSet的子类；遍历其内部数据时，可以按照添加的顺序遍历
 *                                     对于频繁的遍历操作准备的类LinkedHashSet，效率高于HashSet(可能从数组中找，从链表中找)
 *            |---TreeSet：可以按照添加对象的指定属性，进行排序。底层使用红黑树存储。
 *
 *
 * 1. Set接口中没有额外定义新的方法，使用的都是Colllection中声明过的方法。(不像List有序，有操作索引的新方法)
 *
 * 2. 要求：向Set中添加的数据/对象，其所在的类一定要重写hashCode()和equals()
 *    要求：重写的hashCode()和equals()尽可能保持一致性。相等的对象必须具有相等的散列码(哈希值)
 *    重写equals()、hashCode()技巧：
            对象中用作equals() 方法比较的Field属性，都应该用来计算hashCode 值。
 *
 *
 *
 * @author 田沛勋
 * @create 2020-07-31 18:59
 */
public class SetTest {
    /*
    一、Set：存储的无序、不可重复的数据
    以HashSet为例说明：
    1.无序性：不等于随机性。存储的数据在底层数组中并非按照数组索引的顺序添加，而是根据数据的哈希值决定在数组中添加位置。
      (遍历每次都是这个顺序，不一定是添加的顺序)

    2.不可重复性：保证添加的元素/对象按照equals()方法判断时，不能返回true。即，相同的元素只能添加一个。


    二、添加元素的过程：以HashSet为例(底层是：数组 + 链表)
        我们向HashSet容器中添加元素a，首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，
        此哈希值接着通过某种算法计算出在HashSet容器底层数组中的存放位置(即为：索引位置)，判断
        数组此位置上是否已经有元素(?null):
            如果此位置上没有其它元素，则元素a添加成功。   --->情况1
            如果此位置上有其它元素b(或者以链表形式存在的多个元素)，则比较元素a与元素b的hash值：
                如果hash值不同，则元素a添加成功。        --->情况2
                如果hash值相同，进而需要调用元素a所在类的equals()方法：
                    equals()返回true，元素a添加失败
                    equals()返回false，元素a添加成功。  --->情况3

        对于添加成功的情况2和情况3而言：元素a 与已经存在指定索引位置上的数据以链表的方式存储。
        jdk 7：元素a放到数组中，指向原来的元素。
        jdk 8：原来的元素在数组中，指向元素a
        总结：七上八下     (针对于新添加元素a)

        HashSet底层：数组 + 链表




重写hashCode()方法注意事项：
方式二low：结果可能出现问题：24 + 20 = 20 + 24，本来这两个对象不是equals()返回true，可以存放到数组中，但hash值此时一样，我们通过链表存储不建议。
下面使用name.hashCode() * 31 + age；让对象之间hash值相等概率低好多(减少冲突)！ (为什么选择31这个系数？因为31 = 2 << 5 - 1)
系数31选择：
1）要大 2）不能溢出 3）这个数尽可能通过位移运算计算更好，故优先考虑 2^(n) - 1/ + 1非偶数考虑素数情况(减少冲突)，因为偶数有约数 ,n = 1,2,3,4,5
4)考虑位运算奇数：7，9，15，17，31，33，63，65  ；干掉非素数的：63-65-9
31是一个素数，素数作用就是如果我用一个数字来乘以这个素数，那么最终出来的结果只能被素数本身和被乘数还有1来整除！(减少冲突)
     */

    @Test
    public void test1(){
        Set set = new HashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("CC");
        set.add(new User("Tom", 12));
        set.add(new User("Tom", 12));
        set.add(129);

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //LinkedHashSet的使用：
    //LinkedHashSet作为HashSet的子类，在添加数据/对象的同时，每个数据还维护了两个指针/引用，
    //记录此数据前一个数据和后一个数据的地址。
    //优点：对于频繁的遍历操作，LinkedHashSet效率高于HashSet(可能从数组中找，从链表中找)

    @Test
    public void test2(){
        Set set = new LinkedHashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("CC");
        set.add(new User("Tom", 12));
        set.add(new User("Tom", 12));
        set.add(129);

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
