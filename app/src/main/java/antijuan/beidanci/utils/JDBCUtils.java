package antijuan.beidanci.utils;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils {
    static {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConn() {
        Connection conn = null;

            // 反复尝试连接，直到连接成功后退出循环
            try {
                conn = DriverManager.getConnection("jdbc:mysql://10.16.142.208:3306/min","root","123456");
            }catch (Exception exception){
                exception.printStackTrace();
            }
                System.out.println("!!!!!!sus!!!!!!");
        return conn;
    }

    public static void close(Connection conn){
        try {
            System.out.println("!!!!!!fail!!!!!!");
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}


