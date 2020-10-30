package com.atguigu.java3;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * File类的使用
 *
 * 1. File类的一个对象，代表一个文件或一个文件目录(俗称：文件夹)
 * 2. File类在java.io包下
 * 3. File类中涉及到关于文件或文件目录的创建、删除、重命名、判断、获取相关属性等方法
 *     (文件或文件夹相关信息获取和创建、删除、重命名的方法在File类中实现)
 *    并未涉及到写入或读取文件内容的操作。如果需要读取或写入文件内容，必须使用IO流完成。
 * 4. 后续File类的对象常会作为参数传递到流的构造器重，指明读取或写入的“终点/结点”(从哪读或者写到哪)
 *      读写内容，File类的对象主要指的是文件非文件目录
 *
 *
 * @author 田沛勋
 * @create 2020-08-10 16:41
 */
public class FileTest {
    /*
    1.如何实例化一个File类
        File(String filePath)
        File(String paranetPath,String childPath)
        File(File parantFile,String childPath)



    2.
    相对路径：相较于某个路径下，指明的路径
    绝对路径：包含盘符在内的文件或文件目录的路径

    3.路径分隔符
    windows:\\
    unix:/

     */
    @Test
    public void test1(){
        //构造器1：
        File file1 = new File("hello.txt");//相对于当前module的路径
        File file2 = new File("D:\\workspace\\workspace_idea1\\JavaSenior\\day08\\he.txt");//避免java的转义
//        File file2= new File("d:"+ File.separator+ "atguigu"+ File.separator+ "info.txt");//通用写法

        System.out.println(file1);
        System.out.println(file2);

        //构造器2：
        File file3 = new File("D:\\workspace\\workspace_idea1","JavaSenior");
        System.out.println(file3);

        //构造器3：
        File file4 = new File(file3,"hi.ext");
        System.out.println(file4);

    }


    /*

File类的获取功能
public String getAbsolutePath()：获取绝对路径
public String getPath()：获取路径
public String getName()：获取名称
public String getParent()：获取上层文件目录路径。若无，返回null
public long length()：获取文件长度（即：字节数）。不能获取目录的长度。
public long lastModified()：获取最后一次的修改时间，毫秒值

如下的两个方法适用于文件目录：
public String[] list()：获取指定目录下的所有文件或者文件目录的名称数组
public File[] listFiles()：获取指定目录下的所有文件或者文件目录的File数组


     */
    @Test
    public void test2(){
        File file1 = new File("hello.txt");
        File file2 = new File("d:\\io\\hi.txt");

        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(new Date(file1.lastModified()));

        System.out.println();

        System.out.println(file2.getAbsoluteFile());
        System.out.println(file2.getPath());;
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());

    }

    @Test
    public void test3(){
        File file = new File("D:\\workspace\\workspace_idea1\\JavaSenior");

        String[] list = file.list();
        for(String s : list){
            System.out.println(s);
        }

        System.out.println();

        File[] files = file.listFiles();
        for(File f:files){
            System.out.println(f);
        }

    }

    /*
File类的重命名功能
public boolean renameTo(Filedest):把文件重命名为指定的文件
    比如：file1.renameTo(file2)为例
        要想保证返回true，需要file1在硬盘中是存在的，且file2不能在硬盘中存在。
     */
    @Test
    public void test4(){
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\io\\hi.txt");

//        System.out.println(file1.renameTo(file2));
        System.out.println(file2.renameTo(file1));


    }

    /*
File类的判断功能
public boolean isDirectory()：判断是否是文件目录
public boolean isFile()：判断是否是文件(在硬盘中)
public boolean exists()：判断是否存在
public boolean canRead()：判断是否可读
public boolean canWrite()：判断是否可写
public boolean isHidden()：判断是否隐藏
     */
    @Test
    public void test5(){
        File file1 = new File("hello.txt");
        file1 = new File("hello1.txt");

        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.exists());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());

        System.out.println();


        File file2 = new File("d:\\io");
        file2 = new File("d:\\io1");

        System.out.println(file2.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file2.exists());
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());
        System.out.println(file2.isHidden());


    }

    /*
    File类中的，创建硬盘中对应的文件或文件目录的功能：
public boolean createNewFile()：创建文件。若文件存在，则不创建，返回false
public boolean mkdir()：创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
public boolean mkdirs()：创建文件目录。如果上层文件目录不存在，一并创建
注意事项：如果你创建文件或者文件目录没有写盘符路径，那么，默认在项目路径下。

    File类的删除硬盘中对应的文件或文件目录的功能：
public boolean delete()：删除文件或者文件夹
删除注意事项：
Java中的删除不走回收站。
要删除一个文件目录，请注意该文件目录内不能包含文件或者文件目录
     */

    @Test
    public void test6() throws IOException {
        File file1 = new File("hi.txt");
        if(!file1.exists()){
            //文件的创建
            file1.createNewFile();
            System.out.println("创建成功");
        }else{//文件存在
            file1.delete();
            System.out.println("删除成功");
        }

    }

    @Test
    public void test7(){
        //文件目录的创建
        File file1 = new File("d:\\io\\io1\\io3");

        boolean mkdir = file1.mkdir();
        if(mkdir){
            System.out.println("创建成功1");
        }

        System.out.println();

        File file2 = new File("d:\\io\\io1\\io4");

        boolean mkdir1 = file2.mkdirs();
        if(mkdir1){
            System.out.println("创建成功2");
        }


        //文件目录的删除：要想删除成功，io5文件目录下不能有子目录或文件
        File file3 = new File("d:\\io\\io1\\io5");
        System.out.println(file3.delete());



    }


}
