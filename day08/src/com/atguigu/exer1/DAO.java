package com.atguigu.exer1;

import java.util.*;

/**
 * 定义个泛型类DAO<T>，在其中定义一个Map 成员变量，Map 的键为String 类型，值为T 类型。
 * 分别创建以下方法：
 * public void save(String id,T entity)：保存T 类型的对象到Map 成员变量中
 * public T get(String id)：从map 中获取id 对应的对象
 * public void update(String id,T entity)：替换map 中key为id的内容,改为entity 对象
 * public List<T> list()：返回map 中存放的所有T 对象
 * public void delete(String id)：删除指定id 对象
 * 定义一个User 类：
 * 该类包含：private成员变量（int类型）id，age；（String 类型）name。
 * 定义一个测试类：
 * 创建DAO 类的对象，分别调用其save、get、update、list、delete 方法来操作User 对象，
 * 使用Junit 单元测试类进行测试。
 *
 *
 * @author 田沛勋
 * @create 2020-08-10 15:41
 */
public class DAO<T> {

    private Map<String,T> map = new HashMap<String,T>();

    //保存T类型的对象到Map成员变量中
    public void save(String id,T entity){
        map.put(id, entity);
    }

    //从map 中获取id 对应的对象
    public T get(String id){
        return map.get(id);
    }

    //替换map 中key为id的内容,改为entity 对象
    public void update(String id,T entity){
        if(map.containsKey(id)){
            map.put(id, entity);
        }
    }

    //返回map 中存放的所有T 对象
    public List<T> list(){
        //错误的
//        Collection<T> values = map.values();
//        System.out.println(values.getClass());
//        return (List<T>) values;

        //正确的
        Collection<T> values = map.values();
        List<T> list = new ArrayList<>();
        for(T t : values){
            list.add(t);
        }

        return list;


    }

    //删除指定id 对象
    public void delete(String id){
        map.remove(id);
    }



}
