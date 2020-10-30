package com.atguigu.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *      |---Hashtable(1.0):作为古老的实现类；线程安全的，效率低；不能存储nul的key和value
 *          |---Properties:常用来处理配置/属性文件。key和value都是String类型。
 *          配置文件，物理存在的文件，该Properties对象操作：将文件里边的数据读取到内存中
 *
 * @author 田沛勋
 * @create 2020-08-03 7:35
 */
public class PropertiesTest {
        //Properties:常用来处理配置/属性文件。key和value都是String类型。
        public static void main(String[] args)  {
            FileInputStream fis = null;
            try {

                Properties pros = new Properties();

                fis = new FileInputStream("jdbc.properties");
                pros.load(fis);//加载流对应的属性/配置文件

                String name = pros.getProperty("name");
                String password = pros.getProperty("password");

                System.out.println("name = " + name);
                System.out.println("password = " + password);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(fis != null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }



        }
}
