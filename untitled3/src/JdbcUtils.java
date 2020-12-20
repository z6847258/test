/*
 * Copyright (c) 2020-2021 白云电气 All Rights Reserved.
 * FileName : JdbcUtils.java
 * @Author : wangyp
 * @Date : 2020/10/22 下午4:24
 * @Version  1.0
 */




import java.sql.*;

/**
 * @author wangyp
 * @version 1.0
 * @className JdbcUtils
 * @description
 * @date 2020/10/19 17:20
 */

public class JdbcUtils {

    private static final String USER = "root";
    private static final String PWD = "123456";
    private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8" +
            "&serverTimezone=Asia/Shanghai&useOldAliasMetadataBehavior=true&useSSL=false";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * 注册驱动(可以省略)
     */
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    /**
     * 得到数据库的连接
     */
    public static Connection getConnection() {
        Connection connection = null ;
        try {
            connection = DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * 关闭所打开的资源
     *
     * @param conn 连接对象
     * @param stmt SQL语句对象
     */
    public static void close(Connection conn, Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 关闭所有打开的资源
     *
     * @param conn 连接对象
     * @param stmt SQL语句对象
     * @param rs   结果集
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(conn, stmt);
    }

}
