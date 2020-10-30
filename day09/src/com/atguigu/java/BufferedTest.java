package com.atguigu.java;

import org.junit.Test;

import java.io.*;

/**
 * 处理流之一：缓冲流的使用(它不能直接作用在文件上的，作用在节点流或其它流为处理流)
 *
 * 1.缓冲流：
 *   处理字节：BufferedInputStream
 *            BufferedOutputStream
 *
 *   处理字符：BufferedReader
 *            BufferedWriter
 *
 * 2.作用：提高流的读取、写入的速度
 *   提高读写速度原因：内部提供了一个缓冲区：8192 (8192/1024 = 8)
 *
 * 3. 处理流，就是“套接”在已有的流的基础上
 *
 *
 *
 * @author 田沛勋
 * @create 2020-08-12 9:52
 */
public class BufferedTest {

    /*
    实现非文本文件(图片)的复制：使用缓冲流
     */
    @Test
    public void BufferedStreamTest() {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1.造文件
            File srcFile = new File("勇敢.jpg");
            File destFile = new File("勇敢3.jpg");

            //2.造流
            //2.1 造节点流
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);

            //2.2 造缓冲流：处理流之一
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //3.复制的细节：读取、写入
            byte[] buffer = new byte[10];//图片缓冲小点
            int len;
            while((len = bis.read(buffer)) != -1){
                bos.write(buffer,0,len);

//                bos.flush();//刷新缓冲区

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流资源关闭
            //要求1：先关闭外层的流，再关闭内层的流(脱衣服)
            try {
                if(bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //说明：关闭外层流的同时，内层流也会自动的进行关闭。关于内层流的关闭，可以省略。
//        fos.close();
//        fis.close();
        }

    }

    //实现文件复制的方法
    public void copyFileWithBuffered(String srcPath,String destPath){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1.造文件
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            //2.造流
            //2.1 造节点流
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);

            //2.2 造缓冲流：处理流之一
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //3.复制的细节：读取、写入
            byte[] buffer = new byte[1024];//视频/图片缓冲小点
            int len;
            while((len = bis.read(buffer)) != -1){
                bos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流资源关闭
            //要求1：先关闭外层的流，再关闭内层的流(脱衣服)
            try {
                if(bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //说明：关闭外层流的同时，内层流也会自动的进行关闭。关于内层流的关闭，可以省略。
//        fos.close();
//        fis.close();
        }
    }

    @Test
    public void testCopyFileWithBuffered(){

        long start = System.currentTimeMillis();

        String srcPath = "C:\\Users\\home\\Desktop\\01-视频.avi";
        String destPath = "C:\\Users\\home\\Desktop\\02-视频.avi";

//        String srcPath = "hello.txt";
//        String destPath = "hello3.txt";

        copyFileWithBuffered(srcPath,destPath);

        long end = System.currentTimeMillis();

        System.out.println("复制操作花费的时间为：" + (end - start));//1358，现在使用缓冲流为505

    }


    /*
    使用BufferedReader 和 BufferedWriter实现文本文件的复制

    1.当BufferedReader达到缓冲的8192个字符的大小，它自动的flush()了，刷新缓冲区

     */
    @Test
    public void testBufferedReaderBufferedWriter(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            //1.匿名的创建File文件和相应的流
            //匿名的将，造File，造流放在一起。因为只是使用一次
            br = new BufferedReader(new FileReader(new File("dbcp.txt")));
            bw = new BufferedWriter(new FileWriter(new File("dbcp1.txt")));

            //2.读写操作：复制过程

            //方式一：使用char[]数组
//            char[] cbuf = new char[1024];
//            int len;
//            while((len = br.read(cbuf)) != -1){
//
//                bw.write(cbuf,0,len);
////                bw.flush();//刷新缓冲区8192个字符
//
//            }

            //方式二：使用String(每次读可以不用char[],使用String)
            String data;
            while((data = br.readLine()) != null){

                //方法一：
//                bw.write(data + "\n");//data中不包含换行符
                //方法二：
                bw.write(data);//data中不包含换行符
                bw.newLine();//需要自己提供换行的操作

            };

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.关闭资源
            try {
                if(bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }


}
