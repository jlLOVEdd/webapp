package com.wdd.test.comm.jdbc;

import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.xdevapi.RowResult;
import org.apache.commons.dbcp.PoolablePreparedStatement;

import javax.transaction.Transaction;
import java.sql.*;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 10:37 2018/8/17
 */
public class JdbcTest {
    private static final  String URL="jdbc:mysql://106.14.178.69:3306/test?useSSL=FALSE&serverTimezone=UTC";
    private static final String name ="admin";
    private static final String passwd ="Wdd842655.";
    public static void main(String[] args) {
        try {
            //加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //C

            Connection con = DriverManager.getConnection(URL,name,passwd);
            //
            new ClientPreparedStatement(ConnectionImpl.getInstance(new HostInfo()),"select * from f_resources",null);
            Statement s =con.createStatement();

            ResultSet rs = s.executeQuery("select * from f_resources");
            while (rs.next()){
                System.out.println(rs.getString("id")+" -----------" +rs.getString("name")
                +"-------------"+rs.getString("url"));
            }
            rs.close();
            s.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
