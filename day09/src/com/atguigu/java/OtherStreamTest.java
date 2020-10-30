package com.atguigu.java;

import org.junit.Test;

import java.io.*;

/**
 * 其它流的使用：
 * 1. 标准的输入、输出流
 * 2. 打印流
 * 3. 数据流
 *
 * @author 田沛勋
 * @create 2020-08-12 19:26
 */
public class OtherStreamTest {
    /*
    System.in和System.out分别代表了系统标准的输入和输出设备
    默认输入设备是：键盘，输出设备是：显示器
    重定向：通过System类的setIn，setOut方法对默认设备进行改变。

    1. 标准的输入、输出流
    1.1
    System.in：标准的输入流，默认从键盘输入，默认的端点是键盘
    System.out：标准的输出流，默认从控制台/显示器输出，默认的端点是控制台
    1.2
    System类的setIn(InputStream is) / setOut(PrintStream ps)方法重新指定输入和输出的流。

    1.3 练习：
    从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续
    进行输入操作，直至当输入“ e ”或者 exit ”时，退出程序。

    方法一：使用Scanner实现，调用next()返回一个字符串
    方法二：使用System.in实现。System.in ---> 转换流 --->BufferedReader的readLine()

     */


    /*
    练习：
    从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续
    进行输入操作，直至当输入“ e ”或者 exit ”时，退出程序。
     */
    public static void main(String[] args) {
        //造终点System.in标准输入流(不是具体的File，而是从键盘输入这样的操作)，造流

        //读写操作

        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);

            while (true) {
                System.out.println("请输入字符串：");
                String data = br.readLine();
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {//避免空指针问题
                    System.out.println("程序结束");
                    break;
                }
                String upperCase = data.toUpperCase();
                System.out.println(upperCase);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    /*
    2. 数据流：(用来保存内存中的变量，放到内存中不靠谱可考虑之存放在文件中)，可以先写再读
    2.1 DataInputStream 和 DataOutputStream
    2.2 作用：用于读取或写出，基本数据类型的变量或字符串String或字节数组byte[]，可以做一个持久化

    练习：将内存中的字符串和基本数据类型的变量，写出到data.txt文件中。

    注意：处理异常仍然应该使用try-catch-finally
     */
    @Test
    public void test3() throws IOException {
        //1.造输出数据流 + 造端点
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));

        //2.写出
        dos.writeUTF("田沛勋");
        dos.flush();//刷新缓冲区，刷新操作，将内存中的数据写入文件
        dos.writeInt(27);
        dos.flush();
        dos.writeBoolean(true);
        dos.flush();

        //3.关闭流资源
        dos.close();

    }

    /*
    将文件中存储的基本数据类型和字符串读取到内存中，保存在变量中

    注意点：读取不同类型的数据的顺序，要与当初写入文件时，保存的数据的顺序一致！

     */
    @Test
    public void test4() throws IOException {
        //1.造输入数据流 + 造端点
        DataInputStream dos = new DataInputStream(new FileInputStream("data.txt"));

        //2.
        String name = dos.readUTF();
        int age = dos.readInt();
        boolean isMale = dos.readBoolean();

        System.out.println("name = " + name + ",age = " + age + ",isMale = " + isMale);


        //3.
        dos.close();

    }

    /*
    3. 打印流：PrintStream 和 PrintWriter
    3.1 提供一系列重载的print() 和 println()
    3.2 练习
     */
    @Test
    public void test2() {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File("D:\\IO\\text.txt"));
            // 创建打印输出流,设置为自动刷新模式(写入换行符或字节'\n' 时都会刷新输出缓冲区)
            ps = new PrintStream(fos, true);
            if (ps != null) {// 把标准输出流(控制台输出)改成文件
                System.setOut(ps);
            }

            for (int i = 0; i <= 255; i++) { // 输出ASCII字符
                System.out.print((char) i);
                if (i % 50 == 0) { // 每50个数据一行
                    System.out.println(); // 换行
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }


    }

}
