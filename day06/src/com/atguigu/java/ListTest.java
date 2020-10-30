package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 1. List接口框架
 *    |-----Collection接口：单列集合，用来存储一个一个的对象
 *       |----List接口：存储有序的、可重复的数据。  --->称为“动态”数组(像Python中的列表),替换原有的数组
 *              |---ArrayList：作为List接口的主要实现类；线程不安全的，效率高；底层使用Object[] elementData存储(顺序存储)，扩容1.5倍
 *              |---LinkedList：对于频繁的非头尾部插入、删除操作，使用此类比ArrayList效率高；底层使用双向链表存储
 *              |---Vector：作为List接口的古老的实现类；线程安全的，效率低；底层使用Object[] elementData存储。这哥们扩容长度为原来2倍
 *
 *    2. ArrayList源码分析：选择了顺序表中的数组
 * (如果不是频繁的插入、删除，仅仅通过角标有序放进去，然后在某一个角标位置统一的取出来，使用它。
 *  因为LinkedList还需要维护指针，添加末尾，查找末尾元素等操作费事慢)
 *
 *      2.1 jdk 7 的情况下
 *          ArrayList list = new ArrayList();//底层创建了一个长度是10的Object类型的数组elementData
 *          list.add(123);//elementData[0] = new Integer(123);
 *          ...
 *          list.add(11);//如果此次的添加，导致底层elementData数组容量不够，则扩容。
 *          默认情况下，扩容为原来容量的1.5倍，同时需要将原有数组中的数据复制到新的数组中。
 *
 *          结论：建议开发中使用带参的构造器：ArrayList list = new  ArrayList(int initialCapacity)
 *
 *      2.2  jdk 8 的情况下ArrayList的变化
 *           ArrayList list = new ArrayList();//底层Object类型的数组elementData初始化为{}，并没有创建长度为10的数组。
 *
 *           list.add(123);//第一次调用添加add()操作时，底层才先创建了长度为10的Object类型的数组，并将数据123添加到elementData[0]
 *           ...
 *           后续的添加和扩容操作和jdk 7 无异。
 *
 *      2.3 小结：jdk 7 中的ArrayList的对象的创建似于单例的饿汉式 ，jdk 8 中的ArrayList的对象的创建似于单例的懒汉式
 *                延迟了数组的创建，节省内存。
 *
 *    3. LinkedList源码分析：选择了链表中的双向链表
 *          LinkedList list = new LinkedList(); 内部声明了Node类型的first和last属性，默认值为null
 *          list.add(123);//将123封装到Node中，创建了Node对象。
 *
 *          其中LinkedList内部类Node定义为：体现了LinkedList的双向链表的说法
 *          private static class Node<E> {
 *              E item;
 *              Node<E> next;
 *              Node<E> prev;
 *
 *              Node(Node<E> prev, E element, Node<E> next) {
 *                  this.item = element;
 *                  this.next = next;
 *                  this.prev = prev;
 *              }
 *          }
 *
 *     4. Vector源码分析：
 *          jdk 7 和 jdk 8中通过Vector()创建对象时，底层都创建了长度为10的数组。
 *          在扩容方面，默认扩容为原来的数组长度的2倍
 *
 *
 * 面试题：比较ArraysList、LinkList、Vector三者的异同？
 * 同：三个类都是实现了List接口，存储的数据特点相同：存储有序的，可重复的数据
 * 不同：见上
 *
 *
 *
 *
 *
 * 5. List接口中的常用方法
 *
 * @author 田沛勋
 * @create 2020-07-31 11:06
 */
public class ListTest {
    /*Collection接口定义的14种方法 + 其下方法：
void add(int  index, Object ele):在index位置插入ele元素
boolean addAll(int  index, Collection eles):从index位置开始将eles中的所有元素添加进来
Object get(int  index):获取指定index位置的元素
int indexOf(Object obj):返回obj在集合中首次出现的位置
int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置
Object remove(int  index):移除指定index位置的元素，并返回此元素
Object set(int  index, Object ele):设置指定index位置的元素为ele
List subList(int  fromIndex, int  toIndex):返回从fromIndex到toIndex位置的子集合


总结：常用方法
增(末尾插入)：add(Object obj)
删：remove(int index)  / remove(Object obj)，先考虑使用int index形参，不去装箱
改：set(int index,Object ele)
查：get(int index)                            / indexOf(Object obj) / lastIndexOf(Object obj)
插：add(int index,Object obj)
长度：size()
遍历：① iterator() 返回Iterator迭代器方式
      ② 增强for循环foreach
      ③ 普通的循环(有索引)

remove(int index) 移除指定index位置的元素，并返回此元素(删除的元素)
     */

    @Test
    public void test3(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");

        //方式一：Iterator迭代器方式
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("*****************************");

        //方式二：增强for循环foreach
        for(Object obj :list ){
            System.out.println(obj);
        }
        System.out.println("*****************************");

        //方式三：③ 普通的循环(有索引)
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

    @Test
    public void test2(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom", 20));
        list.add(456);

        //int indexOf(Object obj):返回obj在集合中首次出现的位置。如果不存在，返回-1
        int index = list.indexOf(455);
        System.out.println(index);//-1没有

        //int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置。如果不存在，返回-1
        System.out.println(list.lastIndexOf(456));//4

        //Object remove(int  index):移除指定index位置的元素，并返回此元素(删除的元素)
        Object obj = list.remove(0);
        System.out.println(obj);//123
        System.out.println(list);

        //Object set(int  index, Object ele):设置指定index位置的元素为ele
        list.set(1, "CC");
        System.out.println(list);

        //List subList(int  fromIndex, int  toIndex):返回从fromIndex到toIndex位置的左闭右开的子集合
        List subList = list.subList(2, 4);
        System.out.println(subList);
        System.out.println(list);

    }


    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom", 20));
        list.add(456);

        System.out.println(list);

        //void add(int  index, Object ele):在index位置插入ele元素
        list.add(1, "BB");
        System.out.println(list);

        //boolean addAll(int  index, Collection eles):从index位置开始将eles中的所有元素添加进来
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
//        list.add(list1);//把整体list1集合当成一个元素
        System.out.println(list.size());//9
        System.out.println(list);

        //Object get(int  index):获取指定index位置的元素
        System.out.println(list.get(0));

    }



}
