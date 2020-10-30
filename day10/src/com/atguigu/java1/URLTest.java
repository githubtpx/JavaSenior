package com.atguigu.java1;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL网络编程
 * 1.URL：统一资源定位符，对应着互联网上的某一资源地址
 * 2.格式：
 *  http://localhost:8080/examples/beauty.jpg?username=TOM
 *  协议   主机名    端口号  资源地址           参数列表(比如小说的具体的哪一页哪一章通过参数告诉我)
 *
 *
 *
 *
 * <传输协议>://<主机名>:<端口号>/<文件名>#片段名?参数列表
 * 例如: http://192.168.1.100:8080/helloworld/index.jsp#a?username=shkstart&password=123
 * #片段名：即锚点，例如看小说，直接定位到章节
 * 参数列表格式：参数名=参数值&参数名=参数值....
 * 应用层协议:http/https
 *
 * @author 田沛勋
 * @create 2020-08-17 9:36
 */
public class URLTest {
    public static void main(String[] args) {

        try {

            URL url = new URL("https://www.baidu.com/?tn=98012088_3_dg&ch=6");


//            public String getProtocol(  )     获取该URL的协议名
            System.out.println(url.getProtocol());

//            public String getHost(  )        获取该URL的主机名
            System.out.println(url.getHost());

//            public String getPort(  )        获取该URL的端口号
            System.out.println(url.getPort());

//            public String getPath(  )        获取该URL的文件路径
            System.out.println(url.getPath());

//            public String getFile(  )         获取该URL的文件名(文件路径 + 参数)
            System.out.println(url.getFile());

//            public String getQuery(   )      获取该URL的查询名,参数列表
            System.out.println(url.getQuery());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
