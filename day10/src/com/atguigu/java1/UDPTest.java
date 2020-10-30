package com.atguigu.java1;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * UDP协议的网络编程：接收端 - 发送端
 *
 * 不论是UDP还是TCP都是通过套接字Socket进行发送与接收
 *
 * @author 田沛勋
 * @create 2020-08-17 8:50
 */
public class UDPTest {

    //发送端
    @Test
    public void sender() throws IOException {
        //UDP数据报通过数据报套接字DatagramSocket进行发送与接收
        DatagramSocket socket = new DatagramSocket();

        //DatagramPacket类封装UDP数据报
        String str = "我是UDO方式发送的导弹";
        byte[] data = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,9090);

        socket.send(packet);

        socket.close();

    }

    //接收端
    @Test
    public void receiver() throws IOException {
        DatagramSocket socket = new DatagramSocket(9090);

        byte[] buffer = new byte[100];
        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);

        socket.receive(packet);

        System.out.println(new String(packet.getData(),0,packet.getLength()));

        socket.close();


    }

}
