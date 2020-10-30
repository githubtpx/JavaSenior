package com.atguigu.exer;

import org.junit.Test;

/**
 * 利用String类相关方法完成下面String相关算法题目:
 * @author 田沛勋
 * @create 2020-07-25 11:12
 */
public class StringDemo {
    //利用String类相关方法完成下面String相关算法题目：
    /*
    将一个字符串进行反转。将字符串中指定部分进行反转。比如将“abcdefg”反转为”abfedcg”
     */
    //方式一：将String转换为char[],利用char[]的反转方法。(利用String与char[]之间的转换)
    public String reverse(String str,int startIndex,int endIndex){

        //思路：多做多写就有了
        if(str != null ){//&& str.length() != 0     (str != "";)
            char[] arr = str.toCharArray();//对象调方法，需要处理空指针异常的问题
            for(int x = startIndex,y = endIndex;x < y;x++,y--){
                char temp = arr[x];
                arr[x] = arr[y];
                arr[y] = temp;
            }

            return new String(arr);
        }
        return null;
    }

    //方式二：使用String的拼接(直接拼接，分为三部分)
    public String reverse1(String str,int startIndex,int endIndex){
        if(str != null){
            //reverseStr的第一部分拼接：使用str.substring()方法
            String reverseStr = str.substring(0,startIndex);//左闭右开
            //reverseStr的第二部分拼接：倒序遍历
            for(int i = endIndex;i >= startIndex;i--){
                reverseStr += str.charAt(i);
            }
            //reverseStr的第三部分拼接：使用str.substring()方法。
            reverseStr += str.substring(endIndex + 1);

            return reverseStr;
        }

        return null;
    }

    //方式三(方式二的迭代)：使用StringBuffer/StringBuilder替换String类型的变量reverse1
    public String reverse2(String str,int startIndex,int endIndex){
        if(str != null){
            StringBuilder builder = new StringBuilder(str.length());//str特别长

            //第一部分：
            builder.append(str.substring(0, startIndex));
            //第二部分：
            for(int i = endIndex;i >= startIndex;i--){
                builder.append(str.charAt(i));
            }
            //第三部分：
            builder.append(str.substring(endIndex + 1));

            return new String(builder);
        }

        return null;
    }



    @Test
    public void testReverse(){
        String str = "abcdefg";
        String reverse = reverse(str, 2, 5);
        String reverse1 = reverse1(str, 2, 5);
        String reverse2 = reverse2(str, 2, 5);
        System.out.println(reverse);//abfedcg
        System.out.println(reverse1);//abfedcg
        System.out.println(reverse2);//abfedcg

    }






}
