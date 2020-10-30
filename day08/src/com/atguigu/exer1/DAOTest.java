package com.atguigu.exer1;

import java.util.List;

/**
 * 定义一个测试类：
 * 创建DAO 类的对象，分别调用其save、get、update、list、delete 方法来操作User 对象，
 * 使用Junit 单元测试类进行测试。
 *
 * @author 田沛勋
 * @create 2020-08-10 16:04
 */
public class DAOTest {
    public static void main(String[] args) {
        DAO<User> dao = new DAO<>();//类型推断

        dao.save("1001",new User("周杰伦", 35, 1001));
        dao.save("1002",new User("昆凌", 20, 1002));
        dao.save("1003",new User("蔡依林", 23, 1003));
        dao.update("1003", new User("方文山", 23, 1003));
        dao.delete("1002");


        List<User> list = dao.list();
//        System.out.println(list);
        list.forEach(System.out::println);//java 8新特性



    }

}
