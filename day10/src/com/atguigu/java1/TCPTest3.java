package com.atguigu.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程/网络协议
 * 例题3： 从客户端 发送文件 给服务端 ，服务端保存到本地。
 *        并返回“发送成功”给客户端。并关闭相应的连接。
 *
 * 输入流的read()方法是阻塞式的。NIO非阻塞的。
 *
 * @author 田沛勋
 * @create 2020-08-16 21:18
 */
public class TCPTest3 {

    @Test
    public void client() {//客户端的内存
        Socket socket = null;
        OutputStream os = null;
        FileInputStream fis = null;
        try {
            //1.
            socket = new Socket(InetAddress.getByName("127.0.0.1"),9988);

            //2.
            os = socket.getOutputStream();
            //3.
            fis = new FileInputStream("勇敢.jpg");

            //4.
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis.read(buffer)) != -1){
                os.write(buffer, 0,len);
            }

            //关闭数据的输出
            socket.shutdownOutput();

            //5.接收来自于服务器端的数据，并显示到控制台上
            InputStream is = socket.getInputStream();

            // 将数据存储到该类的数组中：防止读到字节转换为String的内容乱码
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer1 = new byte[20];
            int len1;
            while((len1 = is.read(buffer1)) != -1){
                baos.write(buffer1, 0, len1);
            }

            System.out.println(baos.toString());

            baos.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.
            try {
                if(fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket !=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    /*
    这里涉及到的异常应该使用try-catch-finally处理
     */
    @Test
    public void server() throws IOException {//服务器端的内存
        //服务端保存到本地

        //1.
        ServerSocket ss = new ServerSocket(9988);
        //2.
        Socket socket = ss.accept();
        //3.
        InputStream is = socket.getInputStream();
        //4.
        FileOutputStream fos = new FileOutputStream(new File("勇敢4.jpg"));
        //5.
        byte[] buffer = new byte[20];
        int len;
        while((len = is.read(buffer)) != -1){
            fos.write(buffer, 0, len);
        }

        System.out.println("图片传输完成");

        //服务器端给予客户端反馈：返回“发送成功”给客户端。
        //服务器端的小船又回去了，写出去了。
        OutputStream os = socket.getOutputStream();
        os.write("你好，亲，照片我已收到，非常有意义".getBytes());


        //6.
        fos.close();
        is.close();
        socket.close();
        ss.close();
        os.close();


    }

}
