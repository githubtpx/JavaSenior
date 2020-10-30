package com.atguigu.exer;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author 田沛勋
 * @create 2020-07-25 17:01
 */
public class StringDemo2_1 {

    /*
    获取两个字符串中最大相同子串。比如：
    str1 = "abcwerthelloyuiodef";     str2 = "cvhellobnm" //10
    提示：将短的那个串进行长度依次递减的子串与较长的串比较。
     */
    //前提：两个字符串中只有一个相同子串
    public String getMaxSameSubString(String str1,String str2){
        if(str1 != null && str2 != null){
            String maxStr = (str1.length() >= str2.length())?str1:str2;
            String minStr = (str1.length() < str2.length())?str1:str2;

            //str1 = "abcwerthelloyuiodef";     str2 = "cvhellobnm" //10
            int length = minStr.length();
            for(int i= 0;i < length;i++) {//此层循环决定要去几个字符

                for(int x = 0,y = length - i;y <= length;x++,y++){
                    String subStr = minStr.substring(x,y);//左闭右开
                    if(maxStr.contains(subStr)){
                        return subStr;
                    }
                }
            }


        }
        return null;
    }




    // 第4题扩展
    // 如果两个字符串中，存在多个长度相同的最大相同子串
    // 此时先返回String[]，后面可以用集合中的ArrayList替换，较方便
    public String[] getMaxSameSubString1(String str1, String str2) {
        if(str1 != null && str2 != null){
            StringBuilder builder = new StringBuilder();
            String maxStr = (str1.length() >= str2.length())?str1:str2;
            String minStr = (str1.length() < str2.length())?str1:str2;

            //str1 = "abcwerthelloyuiodef";     str2 = "cvhellobnm" //10
            int length = minStr.length();
            for(int i= 0;i < length;i++) {//此层循环决定要去几个字符

                for(int x = 0,y = length - i; y <= length; x++,y++){
                    String subStr = minStr.substring(x,y);//左闭右开
                    if(maxStr.contains(subStr)){
                        builder.append(subStr + ",");
                    }
                }

                if(builder.length() != 0){
                    break;
                }
            }

            String[] split = builder.toString().replaceAll(",$", "").split("\\,");
            return split;

        }
        return null;
    }


    @Test
    public void testMaxSameSubString(){
        String str1 = "abcwerthello1yuiodefabcdef";
        String str2 = "cvhello1bnmabcdef";

        String[] maxSameSubString1 = getMaxSameSubString1(str1, str2);
        System.out.println(Arrays.toString(maxSameSubString1));//[hello1, abcdef]
    }
}
