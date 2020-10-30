package com.atguigu.java;

import org.junit.Test;

import java.io.*;

/**
 * 测试FileInputStream和FileOutputStream节点流-字节流-输入输出流的使用
 *
 * 结论：
 * 1. 对于文本文件(.txt,java,.c,.cpp)，使用字符流处理
 * 2. 对于非文本文件(图片，音频，视频：.jpg,.map3,.map4,.avi,.doc,.ppt,...)，需要使用字节流处理
 *
 *
 *
 *
 *    英文使用一个字节存储的，中文使用三个字节存储的。UTF-8
 *    而byte[]是一个位置是一个字节存储的
 *
 * @author 田沛勋
 * @create 2020-08-11 21:31
 */
public class FileInputOutputStream {


    //使用字节流FileInputStream处理文本文件，可能出现乱码
    @Test
    public void testFileInputStream() {
        FileInputStream fis = null;
        try {
            //1.造文件
            File file = new File("hello.txt");

            //2.造流
            fis = new FileInputStream(file);

            //3.读取数据操作
            byte[] buffer = new byte[5];
            // 英文使用一个字节存储的，中文使用三个字节存储的。UTF-8
            // 而byte[]是一个位置是一个字节存储的
            int len;
            while((len = fis.read(buffer)) != -1){
                String str = new String(buffer,0,len);
                System.out.print(str);//helloworld123��国���
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流资源
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /*
    实现图片的复制功能
     */
    @Test
    public void testFileInputOutputStream() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //
            File srcFile = new File("勇敢.jpg");
            File destFile = new File("勇敢2.jpg");

            //
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //复制的过程：读取和写入的过程
            byte[] buffer = new byte[5];
            int len;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }
            System.out.println("复制成功");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //
            try {
                if(fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /*
    指定路径下文件的复制功能:(针对于操作数据单位：字节,字符也可以)

注意：
    此时字节流复制文本文件或者非文本文件（它们底层都是0101二进制），就像一个搬运工，不想在内存层面转换下可能会出现乱码。
    此时字符流复制文本文件，但是不能复制非文本文件(底层不是字符)。

     */
    //变化的放在方法的形参，不变的东西放到方法体当中
    public void copyFile(String srcPath,String destPath){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            //
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile,false);

            //复制的过程：读取和写入的过程
            byte[] buffer = new byte[1024];//2^10 = 1024（1000）
            int len;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }
            System.out.println("复制成功");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //
            try {
                if(fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCopyFile(){
        long start = System.currentTimeMillis();

        String srcPath = "C:\\Users\\home\\Desktop\\01-视频.avi";
        String destPath = "C:\\Users\\home\\Desktop\\02-视频.avi";

//        String srcPath = "hello.txt";
//        String destPath = "hello3.txt";

        copyFile(srcPath,destPath);

        long end = System.currentTimeMillis();

        System.out.println("复制操作花费的时间为：" + (end - start));//1358

    }


}
