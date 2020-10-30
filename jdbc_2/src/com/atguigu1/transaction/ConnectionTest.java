package com.atguigu1.transaction;


import com.atguigu1.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author 田沛勋
 * @create 2020-09-04 17:54
 */
public class ConnectionTest {

    @Test
    public void testGetConnection() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);
    }

}














