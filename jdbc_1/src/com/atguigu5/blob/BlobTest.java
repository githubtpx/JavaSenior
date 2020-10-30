package com.atguigu5.blob;

import com.atguigu3.bean.Customer;
import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.io.*;
import java.sql.*;

/**
 * @Description 测试使用PreparedStatement来操作Blob类型的数据
 * @author 田沛勋
 * @create 2020-09-04 11:07
 */
public class BlobTest {

    //向数据表customers中插入/修改Blob类型的字段
    @Test
    public void testInsert() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into customers(name,email,birth,photo) values(?,?,?,?)";

        //对于变量：我们可以直接设置
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1, "田沛宸");
        ps.setObject(2, "tianpeichen@126.com");
        ps.setObject(3, "2011-04-23");

        //对于大型类型的操作：对于文件的内容，我们都是通过流的方式传输设置的。
        FileInputStream fis = new FileInputStream(new File("girl.jpg"));
        //com.mysql.jdbc.PacketTo oBigException:
        //Packet for query is too large (1305548 > 1048576).
        //You can change this value on the server by setting the max_allowed_packet' variable.
        ps.setBlob(4, fis);

        ps.execute();

        JDBCUtils.closeResourse(conn,ps);
    }


    //查询数据表customers中Blob类型的字段
    @Test
    public void testQuery() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select id,name,email,birth,photo from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, 21);
            rs = ps.executeQuery();

            if(rs.next()){
                //前四个字段封装在Customer类的对象：id,name,email,birth
                //方式一：通过索引访问每个列的值
    //            int id = rs.getInt(1);
    //            String name = rs.getString(2);
    //            String email = rs.getString(3);
    //            Date birth = rs.getDate(4);

                //方式二：通过列的别名访问每个列的值
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date birth = rs.getDate("birth");

                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);

                //最后Blob类型的字段：还是以流的方式，将数据读取下来。
                //Blob类型的属性，它不像我们简单的变量占几个字节。
                //赋一个值就完事，它是比较大的数据。需要以流的方式获取与设置。
                Blob photo = rs.getBlob("photo");
                //将Blob类型字段下载下来，以文件的方式保存在本地！
                is = photo.getBinaryStream();
                fos = new FileOutputStream(new File("tpx.jpg"));
                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer)) != -1){
                    fos.write(buffer, 0,len);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fos != null)
                 fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JDBCUtils.closeResourse(conn, ps, rs);

    }

}
