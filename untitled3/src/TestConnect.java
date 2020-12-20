/*
 * Copyright (c) 2020-2021 白云电气 All Rights Reserved.
 * FileName : TestConnect.java
 * @Author : wangyp
 * @Date : 2020/11/4 上午8:34
 * @Version  1.0
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 * @author wangyp
 * @version 1.0
 * @className TestConnect
 * @description 使用JDBC方式操作数据库
 * @date 2020/11/4 8:34
 */
public class TestConnect {
    private static Logger logger = Logger.getLogger(TestConnect.class);
    public static void main(String[] args) throws Exception{
        Connection connection = JdbcUtils.getConnection();

        // 查询操作SQL
        String sql = "select * from student where 1=1";

        // 插入操作SQL

        String insertSql = "insert into student(name, age, sex, addr, phone) values(?,?,?,?,?)";
        String insertSql2 = "insert into test(x,y,sum) values(?,?,?)";

        // 修改操作SQL(根据主键ID更新student的name和age属性)
        String updateSql = "update student set name = ?, age = ? where id = ?";

        // 删除操作SQL
        String deleteSql = "delete from student where id = ?";


        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);


           // preparedStatement = connection.prepareStatement(sql);

            // 插入操作设置参
            Thread thread = new Thread(new Runnable(){
                public void run() {
                        for (int i=0;i< 50;i++) {
                            String[] firsname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王"};
                            String[] namelist = {"伟", "芳", "秀英", "娜", "刚", "杰", "桂英"};
                            int a = (int) Math.abs(firsname.length * Math.random());
                            int b = (int) Math.abs(namelist.length * Math.random());
                            String name = firsname[a] + namelist[b];
                            try {
                                preparedStatement.setString(1, name);
                                preparedStatement.setInt(2, 20);
                                preparedStatement.setInt(3, 1);
                                preparedStatement.setObject(4, "广州市天河区");
                                preparedStatement.setObject(5, "1234567890");
                        } catch (SQLException e) {
                        e.printStackTrace();
                    }
                            logger.debug(preparedStatement);
                            try {
                                preparedStatement.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                }
            });thread.start();


            PreparedStatement  preparedStatement2 = connection.prepareStatement(insertSql2);
            Thread thread2 = new Thread(new Runnable(){
                public void run() {
                    int x = 1;
                    int y = 1;
                    int sum = 1;
                    for (int i = 0; i < 50; i++) {
                        if (i >= 2) {
                            sum = x + y;
                            x = y;
                            y = sum;
                        }
                        try {
                            preparedStatement2.setInt(1, x);
                            preparedStatement2.setInt(2, y);
                            preparedStatement2.setInt(3, sum);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        logger.debug(preparedStatement2);
                        try {
                            preparedStatement2.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    }
            });thread2.start();

        preparedStatement2.executeUpdate();

        /*    // 修改SQL设置参数(修改id为1的学生信息，设置姓名为“lisi”，年龄为“22”)
            preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1,"lisi");
            preparedStatement.setInt(2,22);
            preparedStatement.setInt(3,1);
            preparedStatement.executeUpdate();*/


      /*      // 删除操作设置参数(删除id为5的学生信息)
            preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1,5L);
            preparedStatement.executeUpdate();*/





            preparedStatement.close();
            preparedStatement2.close();



    }

}
