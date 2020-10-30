package com.atguigu.exer;

import org.junit.Test;

/**
 * 算法问题：先考虑怎么实现，有几种实现方式？再考虑效率问题
 * @author 田沛勋
 * @create 2020-07-25 11:58
 */
public class StringDemo1 {
    /*
      获取一个字符串在另一个字符串中出现的次数。
      比如：获取“ab”在 “cdabkkcadkabkebfkabkskab”中出现的次数
     */

    /**
     * 获取subStr在mainStr中出现的次数：
     * @param mainStr
     * @param subStr
     * @return
     */
    public int getCount(String mainStr,String subStr){
        int mainLength = mainStr.length();
        int subLength = subStr.length();
        int count = 0;
        int index = 0;
        if(mainLength > subLength){
            //方式一：效率差，每次循环都操作修改mainStr
//            while((index = mainStr.indexOf(subStr)) != -1){
//                count++;
//                mainStr = mainStr.substring(index + subStr.length());
//            }

            //方式二：对于方式一的改进
//            int indexOf(String str, int fromIndex)
            while((index = mainStr.indexOf(subStr,index)) != -1){
                count++;
                index += subLength;
            }

            return count;

        }else{
            return 0;
        }

    }

    @Test
    public void testGetCount(){
        String mainStr = "cdabkkcadkabkebfkabkskab";
        String subStr = "ab";
        int count = getCount(mainStr, subStr);
        System.out.println(count);
    }








}
