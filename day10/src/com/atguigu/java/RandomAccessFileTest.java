package com.atguigu.java;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile的使用(随机存/取文件流，它是非处理流。读取使用byte[])
 * 1.RandomAccessFile直接继承与java.lang.Object类，实现了DataInput、DataOutput接口
 *
 * 2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流(的管道)
 *
 * 3.如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建。
 *   如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，文件记录指针为角标为0的位置，从头覆盖）
 *
 * 4.可以通过相关的操作，实现RandomAccessFile作为输出流时，插入数据的效果
 *
 * 5.它有一个非常好的方法：void seek(long pos)：将文件记录指针定位到pos位置
 *
 *
 *
 * RandomAccessFile 类支持“随机访问” 的方式，程序可以直接跳到文件的任意地方来读、写文件
 *      支持只访问文件的部分内容
 *      可以向已存在的文件后追加内容
 *
 * RandomAccessFile 对象包含一个记录指针，用以标示当前读写处的位置。RandomAccessFile类对象可以自由移动记录指针：
 *      long getFilePointer()：获取文件记录指针的当前位置
 *      void seek(long pos)：将文件记录指针定位到pos位置
 *
 * @author 田沛勋
 * @create 2020-08-15 10:43
 */
public class RandomAccessFileTest {

    @Test
    public void test1() throws FileNotFoundException {
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            //1.造随机存/取文件流对象 -- 管道及端点
            raf1 = new RandomAccessFile("勇敢.jpg", "r");
            raf2 = new RandomAccessFile("勇敢1.jpg", "rw");

            //2.读取和写入
            byte[] buffer = new byte[1024];
            int len;
            while((len = raf1.read(buffer)) != -1){
                raf2.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.关闭流资源
            try {
                if(raf1 != null)
                    raf1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(raf2 != null)
                    raf2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test2() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt", "rw");

        //将文件记录指针 定位到"角标是3"的字符位置
        raf1.seek(3);//将文件记录指针调到角标为3的位置
        raf1.seek(new File("hello.txt").length());

        raf1.write("xyz".getBytes());//

        raf1.close();

    }

    /*
    使用RandomAccessFile实现数据的插入效果：
        RandomAccessFile 对象包含一个记录指针，
        用以标示当前读/写处的位置。RandomAccessFile类对象可以自由移动记录指针
     */
    @Test
    public void test3() throws IOException {
        //"rw"，打开文件，既可以读取，又可以写入到该文件中
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt", "rw");

        //将文件记录指针 定位到"角标是3"的字符位置
        raf1.seek(3);//将文件记录指针调到角标为3的位置

        //保存文件记录指针3后面所有数据到StringBuilder中
        StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());
        byte[] buffer = new byte[20];
        int len;
        while((len = raf1.read(buffer)) != -1){
            builder.append(new String(buffer,0,len));
        }

        //调回指针，写入"xyz"
        raf1.seek(3);
        raf1.write("xyz".getBytes());
        //文件记录指针自动在z的后面了

        //将StringBuilder中的数据写入到文件中
        raf1.write(builder.toString().getBytes());

        raf1.close();



        //思考：将StringBuilder替换为ByteArrayOutputStream(字节数组输出流，字节流)

        // 方式三：避免出现乱码
        // write到ByteArrayOutputStream流里面的byte[]里了

        // ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // byte[] buffer = new byte[10];
        // int len;
        // while ((len = fis.read(buffer)) != -1) {
        // baos.write(buffer, 0, len);
        // }
        //
        // return baos.toString();
    }


}
