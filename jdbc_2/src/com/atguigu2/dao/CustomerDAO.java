package com.atguigu2.dao;

import com.atguigu2.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 此接口用于规范针对于customers表的常用操作
 *
 * @author 田沛勋
 * @create 2020-09-07 9:15
 */
public interface CustomerDAO {
    /**
     * @Description 将cust对象添加到数据库中
     * @param conn 事务操作
     * @param cust
     */
    void insert(Connection conn, Customer cust);

    /**
     * @Description 根据指定的id，删除表中的一条记录
     * @param conn 事务操作
     * @param id
     */
    void deleteById(Connection conn,int id);

    /**
     * @Description 针对于内存中的cust对象的属性id，去修改数据表中的记录
     * @param conn  事务操作
     * @param cust
     */
    void update(Connection conn,Customer cust);

    /**
     * @Description 根据指定的id，查询得到对应的Customer对象
     * @param conn
     * @param id
     */
    Customer getCustomerById(Connection conn,int id);

    /**
     * @Description 查询表中的所有记录构成的集合
     * @param conn
     * @return
     */
    List<Customer> getAll(Connection conn);

    /**
     * @Description 返回数据表中的数据条目数
     * @param conn
     * @return
     */
    Long getCount(Connection conn);

    //根据不同的诉求提供不同的功能

    /**
     * @Description 返回数据表中最大的生日
     * @param conn
     * @return
     */
    Date getMaxBirth(Connection conn);


}
