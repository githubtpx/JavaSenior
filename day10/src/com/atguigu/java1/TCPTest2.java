package com.atguigu.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程 -- 网络协议
 * 例题2：客户端发送文件给服务端，服务端将文件保存在本地。
 *
 * @author 田沛勋
 * @create 2020-08-16 20:54
 */
public class TCPTest2 {

    @Test
    public void client() {
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

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
    public void server() throws IOException {
        //1.
        ServerSocket ss = new ServerSocket(9988);
        //2.
        Socket socket = ss.accept();
        //3.
        InputStream is = socket.getInputStream();
        //4.
        FileOutputStream fos = new FileOutputStream(new File("勇敢3.jpg"));
        //5.
        byte[] buffer = new byte[20];
        int len;
        while((len = is.read(buffer)) != -1){
            fos.write(buffer, 0, len);
        }

        //6.
        fos.close();
        is.close();
        socket.close();
        ss.close();


    }

}
