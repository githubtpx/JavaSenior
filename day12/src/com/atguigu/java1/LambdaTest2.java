package com.atguigu.java1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * java内置的4大核心函数式接口
 *
 * 1. 消费型接口 Consumer<T>     void accept(T t)
 * 2. 供给型接口 Supplier<T>     t get()
 * 3. 函数型接口 Function<T,R>   R aaply(T t)
 * 4. 断定型接口 Predicate<T>    boolean test(T t)
 *
 *
 * @author 田沛勋
 * @create 2020-08-20 15:20
 */
public class LambdaTest2 {
    @Test
    public void test1(){
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("学习太累了，去天上人间买了一瓶矿泉水，价格为：" + aDouble);
            }
        });

        System.out.println("**********************************************");
        happyTime(400,money -> System.out.println("学习太累了，去天上人间喝了一口水，价格为：" + money));



    }

    public void happyTime(double money, Consumer<Double> con){
        con.accept(money);
    }


    @Test
    public void test2(){
        //目标：找到list中含"京"的字符串
        List<String> list = Arrays.asList("北京","南京","天津","东京","西京","普京");
        List<String> filterStrs = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });

        System.out.println(filterStrs);

        System.out.println("**********************************************");

        List<String> filterStrs1 = filterString(list, s -> s.contains("京"));

        System.out.println(filterStrs1);


    }


    //根据给定的规则，过滤集合中的字符串，此规则有Predicate预测型函数式接口的方法决定的。
    public List<String> filterString(List<String> list, Predicate<String> pre){

        ArrayList<String> filterList = new ArrayList<>();
        for(String s : list){
            if(pre.test(s)){
                filterList.add(s);
            }
        }
        return filterList;
    }




}
