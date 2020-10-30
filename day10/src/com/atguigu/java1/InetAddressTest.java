package com.atguigu.java1;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 一、实现网络通信需要解决的两个问题
 * 1.如何准确地定位互联网网络上一台或多台主机；定位主机上的特定的应用
 *
 * 2.找到主机后如何可靠高效地进行数据传输
 *
 * 二、网络编程中的两个要素：
 * 1.对应问题一：IP和端口号
 * 2.对应问题二：提供网络通信协议：TCP/IP参考模型(四层：应用层，传输层，网络层，物理+数据链路层，每层上有具体的网络协议了)
 *
 *
 * 三、通信要素一：IP和端口号
 *
 * 1. IP：唯一的标识Internet互联网上的计算机（通信实体），类似一个传输的终端点，类似一个端点
 * 2. 在Java中使用InetAddress类代表IP
 * 3. IP分类： IPV4  和  IPV6 ；公网地址(万维网使用) 和 私有地址(局域网使用)
 *
 *            192.168.开头的就是私有址址，范围即为192.168.0.0--192.168.255.255，专门为组织机构内部使用。
 *            公网地址，它是具体的万维网的一个网址。
 *
 * 4. 域名：   (实际当中表示IP表示抽象，不太容易记忆。域名相较于IP地址比较形象)
 *          www.baidu.com   非具体的IP   www.mi.com   www.sina.com   www.jd.com   www.vip.com
 *
 *    域名相较于IP地址比较形象，用户可以通过域名的方式也来访问具体的某一个IP地址的。
 *    先从本地hosts去找，本地没有发给网络DNS域名解析服务器，解析得到其IP地址。
 *
 * 5. 本地回路地址：127.0.0.1 对应着域名：localhost
 *
 * 6. 如何实例化InetAddress：两个方法：getByName(String host)、getLocalHost()
 *      该对象两个常用方法：getHostName(),getHostAddress()
 *
 * 7. 端口号：标识正在计算机上运行的进程（程序）
 *    要求：不同的进程有不同的端口号。
 *    范围：0 - 65535，规定为一个16为的整数
 *
 * 8. 端口号与IP 地址的组合得出一个网络套接字：Socket。
 *
 *    Socket，相当于构成网络当中的一个结点一样，基于这个Socket结点再考虑网络通信协议就可以实现数据传输了
 *    所以一会写传输时候，要用的是封装成一个Socket，所以网络通信也成为Socket通信，Socket编程
 *
 *
 * 我想要连接这样一台主机(IP：域名表示localhost)，这个3306端口号对应的那个进程。
 *
 * @author 田沛勋
 * @create 2020-08-16 11:53
 */
public class InetAddressTest {
    public static void main(String[] args) {

        try {
            //File file = new File("hello.txt");file是内存中一个对象，对应磁盘中该位置下的文件
            InetAddress inet1 = InetAddress.getByName("192.168.10.14");

            System.out.println(inet1);

            InetAddress inet2 = InetAddress.getByName("www.atguigu.com");//域名
            System.out.println(inet2);

            InetAddress inet3 = InetAddress.getByName("127.0.0.1");
            System.out.println(inet3);

            //获取本地ip
            InetAddress inet4 = InetAddress.getLocalHost();
            System.out.println(inet4);

            //getHostName()
            System.out.println(inet2.getHostName());
            //getHostAddress
            System.out.println(inet2.getHostAddress());


        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

}
