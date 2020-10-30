package com.atguigu.java1;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author 田沛勋
 * @create 2020-08-17 9:52
 */
public class URLTest1 {

    public static void main(String[] args) {

        HttpsURLConnection urlConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            URL url  = new URL("https://image.baidu.com/search/detail?tn=baiduimagedetail&word=%E6%91%84%E5%BD%B1%E5%B8%88%E6%9D%B0%E5%A4%AB%E4%BD%9C%E5%93%81&pn=3&cs=1808288369,2886187605&pi=5139273732223422616&catename=pcindexhot");

            urlConnection = (HttpsURLConnection)url.openConnection();
            //获取到这个连接
            urlConnection.connect();

            is = urlConnection.getInputStream();
            fos = new FileOutputStream("day10\\detail.jpg");

            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }

            System.out.println("下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //资源关闭
            try {
                if(fos != null)
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(urlConnection != null)
                urlConnection.disconnect();
        }




    }
}
