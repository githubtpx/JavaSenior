package com.atguigu.exer2;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author 田沛勋
 * @create 2020-08-10 18:32
 */
public class FileDemo {

    @Test
    public void test1() throws IOException {
        File file = new File("D:\\io\\io1\\io4\\hello.txt");
        //创建一个与file同目录下的另外一个文件，文件名为：haha.txt
        File destFile = new File(file.getParent(),"haha.txt");
        if(!destFile.exists()){
            destFile.createNewFile();
            System.out.println("创建成功");
        }

    }


}
