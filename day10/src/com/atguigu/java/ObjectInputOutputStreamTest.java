package com.atguigu.java;

import org.junit.Test;

import java.io.*;

/**
 * 对象流(字节流)的使用：
 * 1. ObjectInputStream  和  ObjectOutputStream
 *
 * 2.作用：
 *       用于存储和读取基本数据类型数据或对象的处理流。
 *       它的强大之处就是可以把Java中的对象写入到数据源中，
 *       也能把对象从数据源中还原回来。
 *
 * 3.要想一个java对象是可序列化的，需要满足相应的要求。见Person.java
 *
 *
 * @author 田沛勋
 * @create 2020-08-14 20:20
 */
public class ObjectInputOutputStreamTest {
    /*
    序列化过程：将内存中的java对象保存到磁盘中，或者通过网络传输出去
    使用流：ObjectOutputStream实现
     */
    @Test
    public void testObjectOutputStream(){
        ObjectOutputStream oos = null;
        try {
            //1.造对象流，在File类的对象
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));

            //2.写入操作
            oos.writeObject(new String("我爱北京天安门！"));
            oos.flush();

            oos.writeObject(new Person("Tom", 22));
            oos.flush();

            oos.writeObject(new Person("Aimy", 23,1001,new Account(5000)));
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.关闭流资源
            try {
                if(oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    反序列化过程：将磁盘文件中的对象，还原为内存中一个java对象
    使用ObjectInputStream实现
     */
    @Test
    public void testObjectInputStream(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));

            //读取和存储是有序的
            Object obj = ois.readObject();
            String str = (String) obj;

            Person p1 = (Person) ois.readObject();
            Person p2 = (Person) ois.readObject();
            System.out.println(str);
            System.out.println(p1);
            System.out.println(p2);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }


}
