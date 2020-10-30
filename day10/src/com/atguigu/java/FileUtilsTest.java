package com.atguigu.java;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author 田沛勋
 * @create 2020-08-16 11:03
 */
public class FileUtilsTest {
    public static void main(String[] args) {
        File srcFile = new File("day10\\勇敢.jpg");
        File destFile = new File("day10\\勇敢2.jpg");

        try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
