import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * RequestBody与RequestParam测试
 *
 * @author JustryDeng
 * @date 2018年7月6日 上午2:00:48
 */


@RestController
public class RequestTestController {
    @RequestMapping(value = "/mytest0")
    public String myTestController0(@RequestBody String number) throws SQLException {
        if (number != null) {
            ok();
            return "秒杀成功";
        } else
            return "12";


    }
    public void ok() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String updateSql = "update stock set number = number - ?";
        PreparedStatement preparedStatement= connection.prepareStatement(updateSql);;
        System.out.println(preparedStatement);
        preparedStatement.setInt(1,1);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("成功");
    }
}

