package com.atguigu.java1;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程/网络协议
 * 例子1：客户端发送信息给服务端，服务端将数据显示在控制台上
 *
 * 注意：直接启动客户端需要去握手，服务器端没启动握手失败了，连接失败了。
 *
 * @author 田沛勋
 * @create 2020-08-16 18:57
 */
public class TCPTest1 {

    //客户端：
    //端口号与IP地址的组合得出一个"网络套接字"：Socket它也是一个资源。
    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        try {
            //1.创建Socket对象，指明服务器端的ip和端口号
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet,8899);

            //2.获取一个输出流，用于输出数据
            os = socket.getOutputStream();

            //3.拿着输出流，进行写出数据操作
            os.write("你好，我是客户端MM".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源的关闭
            try {
                if(os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }
    //服务端
    @Test
    public void server()  {
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建服务器端的Socket对象：ServerSocket对象。指明自己的端口号
            ss = new ServerSocket(8899);

            //2.调用accept()表示接收来自于客户端的Socket对象
            socket = ss.accept();

            //3.获取一个输入流
            is = socket.getInputStream();

            //不建议这样写，可能会有乱码
//        byte[] buffer = new byte[1024];
//        int len;
//        while((len = is.read(buffer)) != -1){
//            String str = new String(buffer,0,len);
//            System.out.print(str);
//        }

            //4.读取输入流中的数据
            //可变byte[],等你全部输出完之后，再使用方法整体上把你转换成字符串
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[5];
            int len;
            while((len = is.read(buffer)) != -1){
                baos.write(buffer, 0, len);
            }

            System.out.println(baos.toString());//把所有字节数组转换成字符串

            System.out.println("收到了来自于：" + socket.getInetAddress().getHostAddress() + "的数据");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            try {
                if(baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(ss != null)
                    ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

}
