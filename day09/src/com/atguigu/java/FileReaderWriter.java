package com.atguigu.java;

import org.junit.Test;

import java.io.*;

/**
 *
 * 一、流的分类
 * 1. 按照 操作数据单位  不同分为：字节流、字符流
 * 2. 按照 数据的流向  不同分为：输入流、输出流
 * 3. 按照 流的角色  不同分为：节点流(直接操作File的流，或成为文件流)、处理流
 *
 * 二、流的体系结构
 * 抽象基类(IO流中最基本的抽象类) 节点流(或文件流)效率差                    缓冲流(处理流的一种)
 * InputStream                  FileInputStream (read(byte[] buffer))   BufferedInputStream (read(byte[] buffer))
 * OutputStream                 FileOutStream   (write(buffer,0,len))   BufferedOutputSream (write(buffer,0,len)) / bo.flush();刷新缓冲区操作
 * Reader                       FileReader  (read(char[] cbuf))         BufferedReader (read(char[] cbuf) / readLine())
 * Writer                       FileWriter  (write(cbuf,0,len))         BufferedWriter (write(cbuf,0,len)) / bw.flush();刷新缓冲区操作(内存中的数据都会写出去了)
 *
 * 缓冲流（处理流）：作用：提高文件读写效率
 * flush();刷新缓冲区操作(内存中的数据都会写出去了)
 *
 *
 * @author 田沛勋
 * @create 2020-08-11 18:07
 */
public class FileReaderWriter {
    public static void main(String[] args) {
        File file = new File("hello.txt");//Main相对路径是相对于当前Project下的
        System.out.println(file.getAbsolutePath());

        File file1 = new File("day09\\hello.txt");
        System.out.println(file1.getAbsolutePath());
    }


    /*
    将day09下的hello.txt文件内容读入程序/内存，并输出到控制台
            我们占位在程序/内存的角度。去看待这个输入、输出的问题。

    说明点：
    1. read()的理解：返回读入的一个字符。如果达到文件末尾，返回-1
    2. 异常的处理，为了保证流资源一定可以执行关闭操作，需要使用try-catch-finnally处理
    3. 读入的文件一定要存在，否则就会报FileNotFoundException。

     */
    @Test
    public void testFileReader(){
        FileReader fr = null;
        try {
            //1.实例化File类的对象，指明要操作的文件：整了一个要操作的文件
            File file = new File("hello.txt");//JUnit相对路径是相对于当前module下的
            //2.提供具体的流：整了一个操作File文件到程序/内存的管道
            fr = new FileReader(file);//实例化流的对象，防止出现异常出去，调用时NullPointerException

            //3.数据的读入：管道的开关
            //read()：返回读入的一个字符。如果达到文件末尾，返回-1
            //方式一：
//        int data = fr.read();
//        while(data != -1){
//            System.out.print((char)data);
//            data = fr.read();
//        }

            //方式二：语法上针对于方式一的修改
            int data;
            while((data = fr.read()) != -1){
                System.out.println((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流的关闭操作
//            try {
//                if(fr != null)
//                    fr.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            //或者
            if(fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }


    //对read()操作升级：使用read的重载方法
    @Test
    public void testFileReader1() {
        FileReader fr = null;
        try {
            //1. File类的实例化
            File file = new File("hello.txt");

            //2. 流的实例化
            fr = new FileReader(file);

            //3. 读入的操作
            //read(char[] cbuf)：返回每次读入cbuf数组中的字符个数。如果达到文件末尾，返回-1.
            char[] cbuf = new char[5];
            int len;
            while((len = fr.read(cbuf)) != -1){
                //方式一：
                //错误的写法：输出：helloworld123ld
//                for (int i = 0; i < cbuf.length; i++) {
//                    System.out.println(cbuf[i]);
//                }
                //正确的写法：
//                for (int i = 0; i < len; i++) {
//                    System.out.print(cbuf[i]);
//                }

                //方式二：
                //错误的写法：对应着方式一错误的写法。输出：helloworld123ld
//                String str = new String(cbuf);
//                System.out.println(str);

                //正确的写法
                String str = new String(cbuf,0,len);
                System.out.print(str);


            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4. 流资源的关闭
            try {
                if(fr != null)
                    fr.close();//防止fr没有实例化
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
    从内存中写出数据到硬盘的文件里:
        我们占位在程序/内存的角度。去看待这个输入、输出的问题。

    说明：
    1. 输出操作，对应的File可以不存在的。并不会报异常
    2.
       File对应的硬盘中的文件，如果不存在，在输出的过程会自动创建此文件
       File对应的硬盘中的文件，如果存在，
                如果流使用的构造器是：FileWriter(file,false) /FileWriter(file)：对原有文件的覆盖
                如果流使用的构造器是：FileWriter(file,true) ：不会对原有文件的覆盖，而是在原有文件基础上追加内容



     */
    @Test
    public void testFileWriter()  {
        FileWriter fw = null;//append:false,如果文件存在，不再原有文件后追加，覆盖源文件
        try {
            //1.提供File类的对象，指明写出到的文件
            File file = new File("hello1.txt");

            //2.提供输出流对象：FileWriter，用于数据的写出
            fw = new FileWriter(file,false);

            //3.写出的操作
            fw.write("I hava a dream!\n".toCharArray());
            fw.write("you need to hava a dream!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流资源的关闭
            try {
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }


    /*
    实现文本文件的复制：使用IO流的字符流知识
    实现图片的复制：使用IO流的字节流知识，不能使用字符流处理图片等字节数据

    FileReader、FileWriter操作数据的单位是字符，称为字符流 派

     */
    @Test
    public void testFileReaderFileWriter()  {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            //1.创建File类的对象，指明要读入和写出的文件
            File srcFile = new File("hello.txt");
            File destFile = new File("hello2.txt");


            //不能使用字符流处理图片等字节数据
//            File srcFile = new File("勇敢.jpg");
//            File destFile = new File("勇敢1.jpg");

            //2.创建输入流和输出流的对象
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile, false);

            //3.数据读取和输入操作
            char[] cbuf = new char[5];
            int len;//记录每次读入到cbuf数组中的字符的个数
            while((len = fr.read(cbuf)) != -1){

                //每次写出len个字符，即读进去几个，就写进去几个
                fw.write(cbuf,0,len);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流资源的关闭

            //方式一：
//            try {
//                if(fw != null)
//                    fw.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally {
//                try {
//                    if(fr != null)
//                        fr.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

            //方式二：
            try {
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //后面代码会执行
            try {
                if(fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }


    }


    /*
    实现图片/视频的复制：使用IO流的字节流知识，不能使用字符流处理图片等字节数据

     */
    @Test
    public void testInputStreamOutputSream()  {

        FileInputStream fi = null;
        FileOutputStream fo = null;
        try {
            //1.创建File类的对象，指明要读入和写出的文件

            File srcFile = new File("勇敢.jpg");
            File destFile = new File("勇敢1.jpg");

            //2.创建输入流和输出流的对象
            fi = new FileInputStream(srcFile);
            fo = new FileOutputStream(destFile, false);

            //3.数据读取和输入操作
            byte[] bbuf = new byte[5];
            int len;//记录每次读入到cbuf数组中的字符的个数
            while((len = fi.read(bbuf)) != -1){

                //每次写出len个字符，即读进去几个，就写进去几个
                fo.write(bbuf,0,len);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流资源的关闭
            try {
                if(fi != null)
                    fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //后面代码会执行
            try {
                if(fo != null)
                    fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }

    }



}
