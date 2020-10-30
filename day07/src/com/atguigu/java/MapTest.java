package com.atguigu.java;

import org.junit.Test;

import java.util.*;

/**
 * 一、Map的实现类的结构：            (Map与List为集合中的重点)
 *  |---Map(1.2出现):双列数据(双列集合)，存储key-value对的数据    ---类似于高中的函数：y = f(x)
 *      |---HashMap(1.2)：作为Map的主要实现类；线程不安全的，效率高；存储nul的key和value(重点jdk7，jdk8不同)
 *          |---LinedHashMap(1.4):保证在遍历map元素时，可以按照添加的顺序实现遍历。
 *                  原因：在原有的HashMap底层结构基础上，添加一对引用/指针，指向前一个和后一个元素。
 *                  对于频繁的遍历操作需求，此类执行效率要高于HashMap。
 *
 *      |---TreeMap(1.2):保证按照添加的key-value对进行排序，实现排序遍历。此时考虑key的自然排序或定制排序。
 *                      底层使用红黑树(二叉排序树的一种)
 *      |---Hashtable(1.0):作为古老的实现类；线程安全的，效率低；不能存储nul的key和value
 *          |---Properties:常用来处理配置/属性文件。key和value都是String类型。
 *
 *
 *      HashMap的底层：数组 + 链表          (jdk 7 及之前)
 *                    数组 + 链表 + 红黑树  (jdk 8 )，提升效率
 *
 * 面试题：
 * 1. HashMap的底层实现原理？
 * 2. HashMap 和Hashtable的异同？
 * 3. CurrentHashMap  与 Hashtable的异同？(暂时不讲)
 *    CurrentHashMap实现分段锁技术(数据分好几块，分段锁住，相当于多个线程同一个时间段都在操作这个共享数据：像卖票)
 *
 * 二、Map结构的理解：key-value对
 *      Map中的key是无序的，不可重复的，使用Set存储所有的key。
 *      (到底是什么Set？你就得看你是什么Map了，如你是HashMap，我就用HashMap存储key)
 *              ----> key所在的类要重写equals()和hashCode()（以HashMap为例）
 *              ----> key所在的类要能够自然排序或定制排序（以TreeMap为例）
 *
 *      Map中的value是无序的，可重复的，使用Collection存储所有的value。
 *              ----> value所在的类要重写equals()
 *
 *      一个键值对：key-value构成一个Entry对象。
 *      Map中的Entry：无序的，不可重复的，使用Set存储所有的Entry对象。
 *
 *
 * 2.1 Map中所有key、value、Entry对象的特点分析：(Map最后对比数学中函数)
 *      2.1.1 Map结构纵向分析:
 *      key这个数据有无序的、不可重复这个特点，使用Set存储效率高；因为：Set结构存储无序的、不可重复的数据：
 *      value这个数据无序的、可重复的。无相关结构存储之。所以泛泛可说value使用Collection(单列数据)存储的。
 *
 *      2.1.2 Map结构横向分析:
 *      Map实际添加是一个一个Entry对象，每个entry有连个属性key，value
 *      Map集合中存放的所有Entry对象的特点：
 *          首先，它无序。不可重复。
 *          所以，Map存放Entry对象，可以使用Set结构存储承装。
 *
 * 2.2 Map对比数学中函数y=f(x)：
 *      2.2.1 函数/映射的特性：可以多对一，不能一对多。
 *
 *      HashMap的底层：数组 + 链表          (jdk 7 及之前)
 *                    数组 + 链表 + 红黑树  (jdk 8 )，提升效率
 *
 * 三、HashMap的底层实现原理？以jdk 7为例说明：
 *     HashMap map = new HashMap();
 *     在实例化以后，底层创建了长度是16的一维数组Entry[] table。(一个键值对：key-value构成一个Entry对象。)
 *     ...可能已经执行过多次put...
 *
 *     map.put(key1,value1);
 *     首先，调用key1所在类的hashCode()参与计算key1的哈希值，此哈希值(很大)经过某种算法计算以后，得到在Entry数组中的存放位置。
 *     如果此位置上的数据为空，此时的key1-value1添加成功。                                   --->情况1
 *     如果此位置上的数据不为空，(意味着此位置上存在一个或多个数据(以链表形式存在))，比较key1和已经存在的一个或多个数据的
 *     哈希值：
 *              如果key1的哈希值与已经存在的数据哈希值都不相同，此时key1-value1添加成功。     --->情况2
 *              如果key1的哈希值与已经存在的某一个数据(key2-value2)哈希值相同，继续比较：调用key1所在类的equals(key2)方法，比较：
 *                          如果equals()返回false；此时key1-value1添加成功。               --->情况3
 *                          如果equals()返回true；使用value1替换value2。修改功能！
 *
 *
 *      补充：关于情况2和情况3：此时key1-value1和原来的数据以链表的方式存储。(七上八下)
 *
 *      HashMap在不断添加过程中，会涉及到扩容问题，当超出临界值时(且要存放的位置非null时)，默认扩容方式：扩容为原来容量的2倍，并将原有的数据复制过来。
 *
 *
 *      jdk 8相较于jdk 7在底层实现方面的不同：
 *      1. new HashMap():底层没有创建一个长度为16的数组
 *      2. jdk 8底层的数组是：Node[]，而非Entry[]数组
 *      3. jdk 8，首次调用put()方法时，底层创建长度为16的Node[]
 *      4. jdk 7底层结构只有：数组 + 链表。 jdk 8中底层结构：数组 + 链表 + 红黑树
 *         4.1 形成链表时，七上八下（jdk7:新的元素指向旧的元素。jdk8：旧的元素指向新的元素）
 *         4.2 当数组的某一个索引位置上的元素以链表形式存在的数据个数 > 8，且当前数组的长度 > 64    (<64扩容)，
 *             此时此索引位置上的所有数据改为使用红黑树存储。(Why?遍历快，方便查找，查找效率很高O(n) 到 O(nlogn))

 *
 *      DEFAULT_INITIAL_CAPACITY : HashMap的默认容量，16
 *      DEFAULT_LOAD_FACTOR：HashMap的默认加载因子：0.75f    (兼顾统计学：数组的利用率 + 出现的链表少些)
 *      threshold：扩容的临界值， = 容量 * 加载因子/填充因子 = 16 * 0.75 = 12
 *      TREEIFY_THRESHOLD：Bucket中链表长度大于该默认值，转化为红黑树：8
 *      MIN_TREEIFY_CAPACITY：桶中的Node被树化时最小的hash表容量：64。
 *      （当桶中Node的数量大到需要变红黑树时，若hash表容量小于MIN_TREEIFY_CAPACITY时，此时应执行resize扩容操作这个MIN_TREEIFY_CAPACITY的值至少是TREEIFY_THRESHOLD的4倍。）
 *
 *      HashMap数组中怎么算满呢？无序的放入，可能放了已经16个了，但是有些位置可能始终是空的。
 *      所以扩容时候不是满了才扩容。和ArrayList不同(size() = 10)。提前扩容使得你出现链表的情况还是要少些！
 *
 *
 * 四、LinkedHashMap底层实现原理(了解)   它是HashMap子类，还是它定义数组去做。
 *     源码中：
 *     static class Entry<K,V> extends HashMap.Node<K,V> {
 *         Entry<K,V> before, after;    //能够记录添加的元素的先后顺序。频繁遍历诉求。
 *         Entry(int hash, K key, V value, Node<K,V> next) {
 *             super(hash, key, value, next);
 *         }
 *     }
 *
 *
 *
 * 五、Map接口中定义的方法：
 添加、删除、修改操作：
 Object put(Object key,Object value)：将指定key-value添加到(或修改)当前map对象中
 void putAll(Map m):将m中的所有key-value对存放到当前map中
 Object remove(Object key)：移除指定key的key-value对，并返回value
 void clear()：清空当前map中的所有数据

 元素查询的操作：
 Object get(Object key)：获取指定key对应的value
 boolean containsKey(Object key)：是否包含指定的key
 boolean containsValue(Object value)：是否包含指定的value
 int size()：返回map中key-value对的个数
 boolean isEmpty()：判断当前map是否为空
 boolean equals(Object obj)：判断当前map和参数对象obj是否相等

 元视图操作的方法：
 Set keySet()：返回所有key构成的Set集合
 Collection values()：返回所有value构成的Collection集合
 Set entrySet()：返回所有key-value对构成的Set集合
 *
 *
 * 总结Map接口常用方法：
 * 添加：put(Object key,Object value)
 * 删除：remove(Object key)
 * 修改：put(Object key,Object value)
 * 查询：get(Object key)
 * 长度：size()
 * 遍历：keySet() / values()  / entrySet() + (可以keySet()+get(Object key))
 *
 *
 *
 *
 *
 *
 *
 * 在1.0先有一个Hashtable，存储key-value对数据；类似于Vector
 * 在1.2不想用Hashtable了，此时出现一个接口叫做"Map",用Map接口来规范存储key-value对数据
 *      然后，提供了Map的主要实现类：HashMap 子类LinkedHashMap，TreeMap
 *           如果存储有序key-value对数据，我们使用TreeMap。
 *           如果不需要存储有序key-value对数据，我们通常使用HashMap。
 *                  在1.4，如果我们使用频繁的遍历操作，效率比它HashMap更好的有LinkedHashMap
 *
 *
 * 注意：和ArrayList一样，即使后面会涉及到多线程安全问题，我们也不用Vector和Hashtable实现类，
 *       我们使用工具类Collections相关方法把它(集合)变成安全的
 *
 * @author 田沛勋
 * @create 2020-08-02 8:53
 */
public class MapTest {
    /*
     元视图操作的方法：
 Set keySet()：返回所有key构成的Set集合
 Collection values()：返回所有value构成的Collection集合
 Set entrySet()：返回所有key-value对构成的Set集合
     */
    @Test
    public void test5(){
        HashMap map = new HashMap();
        map.put(123, "AA");
        map.put(232, "AA");
        map.put(345, "BB");
        map.put(12, "DD");

        //遍历Map所有的key集：keySet()
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("******************************");

        //遍历所有的value集：values()
        Collection values = map.values();
        for(Object obj :values){
            System.out.println(obj);
        }
        System.out.println("******************************");

        //遍历所有的key-value：Map.Entry内部接口
        //方式一：entrySet()
        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while(iterator1.hasNext()){
            Object obj = iterator1.next();
            //entrySet集合中的元素都是Entry对象
            Map.Entry entry  = (Map.Entry)obj;
            System.out.println(entry.getKey() + "---->" + entry.getValue());
        }
        System.out.println("******************************");

        //方式二：
        Set keySet = map.keySet();
        Iterator iterator2 = keySet.iterator();
        while(iterator2.hasNext()){
            Object key = iterator2.next();
            Object value = map.get(key);

            System.out.println(key + "======" + value);
        }

    }


    /*
     元素查询的操作：
Object get(Object key)：获取指定key对应的value
boolean containsKey(Object key)：是否包含指定的key
boolean containsValue(Object value)：是否包含指定的value
int size()：返回map中key-value对的个数
boolean isEmpty()：判断当前map是否为空
boolean equals(Object obj)：判断当前map和参数对象obj是否相等
     */
    @Test
    public void test4(){
        HashMap map = new HashMap();
        //添加
        map.put(123, "AA");
        map.put(232, "AA");
        map.put(345, "BB");
        map.put(12, "DD");

        //Object get(Object key)：获取指定key对应的value
        System.out.println(map.get(45));//Object value = null

        //boolean containsKey(Object key)：是否包含指定的key
        boolean isExist = map.containsKey(123);
        System.out.println(isExist);//true

        // boolean containsValue(Object value)：是否包含指定的value
        isExist = map.containsValue("AA");
        System.out.println(isExist);

        //int size()：返回map中key-value对的个数
        System.out.println(map.size());


        map.clear();
        //boolean isEmpty()：判断当前map是否为空,size() = 0
        System.out.println(map.isEmpty());


    }




    /*
    添加、删除、修改操作：
Object put(Object key,Object value)：将指定key-value添加到(或修改)当前map对象中
void putAll(Map m):将m中的所有key-value对存放到当前map中
Object remove(Object key)：移除指定key的key-value对，并返回value
void clear()：清空当前map中的所有数据，不清除底层map数组
     */
    @Test
    public void test3(){
        HashMap map = new HashMap();
        //添加
        map.put(123, "AA");
        map.put(345, "BB");
        map.put(12, "DD");
        //修改
        map.put(345, "CC");
        System.out.println(map);

        HashMap map1 = new HashMap();

        map1.put(24, "AA");
        map1.put(23, "AA");
        map.putAll(map1);
        System.out.println(map);

        //remove(Object key)：按照指定key去移除指定的键值对,返回指定key的value值
        Object value = map.remove(24);
        Object value1 = map.remove(24222);
        System.out.println(value);//AA
        System.out.println(value1);//不存在返回Object对象 = null

        System.out.println(map);

        //clear()
        map.clear();//与map = null操作不同
        System.out.println(map.size());//0
        System.out.println(map);//{}

    }


    /*
    LinkedHashMap底层实现原理
     */
    @Test
    public void test2(){
        HashMap map = new HashMap();
        map = new LinkedHashMap();

        map.put(123, "AA");
        map.put(345, "BB");
        map.put(345, "CC");
        map.put(12, "DD");

        System.out.println(map);//{123=AA, 345=CC, 12=DD}
    }

    @Test
    public void test1(){
        Map map = new HashMap();
//        map = new Hashtable();//不能存储null的key和value
        map.put(null, null);



    }
}
