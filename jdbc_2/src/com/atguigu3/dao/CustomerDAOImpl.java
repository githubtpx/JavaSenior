package com.atguigu3.dao;

import com.atguigu2.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @author 田沛勋
 * @create 2020-09-07 9:32
 */
public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO {
    //先，在父类BaseDAO中加上一个泛型参数T（具体的ORM思想中表对应的java对应的类的）。
    //然后，再具体的子类中，指明父类的泛型参数，指明你要操作的是哪个表对应过来的类
//    {
//        //获取带泛型的父类（返回一个多态的Type）
//        Type genericSuperclass = this.getClass().getGenericSuperclass();
//        //带泛型参数的Type（强转多态，为Type的子类）
//        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
//        //获取了父类的泛型参数
//        Type[] typeArguments = paramType.getActualTypeArguments();
//        clazz = typeArguments[0];//获取了泛型的第一个参数
//
//    }

    @Override
    public void insert(Connection conn, Customer cust) {
        String sql = "insert into customers(name,email,birth) values(?,?,?)";
        update(conn, sql,cust.getName(),cust.getEmail(),cust.getBirth());
    }

    @Override
    public void deleteById(Connection conn, int id) {
        String sql = "delete from customers where id = ?";
        update(conn,sql,id);
    }

    @Override
    public void update(Connection conn, Customer cust) {
        String sql = "update customers set name = ?,email = ?,birth = ? where id = ?";
        update(conn,sql,cust.getName(),cust.getEmail(),cust.getBirth(),cust.getId());
    }

    @Override
    public Customer getCustomerById(Connection conn, int id) {
        String sql = "select id,name,email,birth from customers where id = ?";
        Customer customer = getInstance(conn, sql, id);
        return customer;
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        String sql = "select id,name,email,birth from customers";
        List<Customer> list = getForList(conn, sql);
        return list;
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        return getValue(conn, sql);
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql = "select max(birth) from customers";
        return getValue(conn,sql);
    }
}
