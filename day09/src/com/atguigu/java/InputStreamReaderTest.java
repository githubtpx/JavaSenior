package com.atguigu.java;

import org.junit.Test;

import java.io.*;

/**
 * 处理流之二：转换流的使用
 * 1.转换流：属于字符流                              (IO流的体系结构是哪种？看后边，后缀)
 *   InputStreamReader：将一个字节的输入流转换为字符的输入流
 *   OutputStreamWriter：将一个字符的输出流转换为字节的输出流
 *   转换流读写操作数据是的char型数组，流是一个一个管道有阀门的。
 *
 * 2.作用：提供字节流与字符流之间的转换
 *
 * 3.解码：字节、字节数组  ---> 字符数组、字符串(从看不懂的到看的懂的)      ----InputStreamReader
 *   编码：字符数组、字符串 ---> 字节、字节数组   ----OutputStreamWriter
 *
 * 4.字符集
 ASCII：美国标准信息交换码。
    用一个字节的7位可以表示。(有一位没用，128个情况)
 ISO8859-1：拉丁码表。欧洲码表
    用一个字节的8位表示。

 GB2312：中国的中文编码表。最多两个字节编码所有字符(中文是两个字节，英文还是用的一个字节，不浪费空间 )
 GBK：中国的中文编码表升级，融合了更多的中文文字符号。最多两个字节编码
    最高位：0，则一个字节表示一个字符；     1，则两个字节表示一个字符。
 Unicode：国际标准码，融合了目前人类使用的所有字符。为每个字符分配唯一的字符码。所有的文字都用两个字节来表示。
    如果最高位：0，则一个字节表示一个字符；1，则两个字节表示一个字符。不够用了2^15位
 UTF-8：变长的编码方式，可用1-4个字节(一个汉字使用3个字节存储)。6个字节有。

 Unicode符号范围(十六进制) | UTF 8 编码方式
 0000 0000  0000 007F     | 0xxxxxxx （兼容原来的 ASCII）
 0000 0080  0000 07FF     | 110xxxxx 10xxxxxx
 0000 0800  0000 FFFF     | 1110xxxx 10xxxxxx 10xxxxxx
 0001 0000  0010 FFFF     | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 *
 * 注意：
 * 1. ANSI编码，通常指的是平台的默认编码，例如英文操作系统中是ISO-8859-1，中文系统是GBK
 * 2. Unicode字符集只是定义了字符的集合和唯一编号，Unicode编码，
 *    则是对UTF-8、UCS-2/UTF-16等具体编码方案的统称而已，并不是具体的编码方案。
 *
 * @author 田沛勋
 * @create 2020-08-12 16:47
 */
public class InputStreamReaderTest {

    /*
    此时处理异常的话，仍然应该使用try-catch-finally
    InputStreamReader的使用，实现字节的输入流到字符的输入流的转换
     */
    @Test
    public void test1() throws IOException {
        FileInputStream fis = new FileInputStream("dbcp.txt");
//        InputStreamReader isr = new InputStreamReader(fis);//使用系统默认的字符集

        //参数2指明了(解码使用的)字符集,具体使用哪个字符集，取决于文件dbcp.txt保存时(编码)使用的字符集
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");//使用系统默认的字符集

        char[] cbuf = new char[20];
        int len;
        while((len = isr.read(cbuf)) != -1){
            String str = new String(cbuf,0,len);
            System.out.print(str);//输出到控制台
        }


        isr.close();

    }


    /*
     此时处理异常的话，仍然应该使用try-catch-finally

    综合使用InputStreamReader 和 OutputStreamWriter
     */
    @Test
    public void test2() throws IOException {
        //1.造文件、造流
        File file1 = new File("dbcp.txt");
        File file2 = new File("dbcp_gbk.txt");

        //造节点流
        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        //造处理流：转换流(字符流)：解码-编码流的过程
        //解码流
        InputStreamReader isr = new InputStreamReader(fis,"utf-8");
        //编码流
        OutputStreamWriter osw = new OutputStreamWriter(fos,"gbk");


        //2.读写过程
        char[] cbuf = new char[20];
        int len;
        while((len = isr.read(cbuf)) != -1){
            osw.write(cbuf,0,len);
        }

        //3.关闭流资源
        isr.close();
        osw.close();


    }


}
