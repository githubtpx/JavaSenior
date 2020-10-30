package com.atguigu3.bean;

import java.sql.Date;

/**
 * ORM编程思想（Object relational mapping,对象关系映射）
 *
 * 数据库：             java:
 * 一个数据表对应       一个java类
 * 表中的一条记录对应    java类的一个对象
 * 表中的一个字段对应    java类的一个属性
 *
 * @author 田沛勋
 * @create 2020-09-03 11:20
 */
public class Order {
    private int orderId;
    private String orderName;
    private Date orderDate;

    public Order() {
    }

    public Order(int orderId, String orderName, Date orderDate) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
