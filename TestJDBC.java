package java42_0417;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestJDBC {
    public static void main(String[] args) throws SQLException {
        // 1. 创建数据源~(数据源是在决定要访问那种数据库)
        //    不同的数据库的驱动包提供了不同的数据源的类.
        //    这些类都实现了标准库中的 DataSource 接口
        MysqlDataSource dataSource = new MysqlDataSource();
        // 2. 给数据源设置一些必要的参数
        //    数据库的地址, 端口, 用户名, 密码, 要访问的数据库的名字~
        dataSource.setUser("root");
        dataSource.setPassword("2222");
        dataSource.setURL("jdbc:mysql://127.0.0.1:3306/java42?characterEncoding=utf8&useSSL=true");
        // 3. 和数据库建立连接(发送网络请求了)
        //    连接 Connection    链接 Link (HTML a 是一个超链接)
        //    如果连接失败, 就会抛出 SQLException 异常
        //    例如用户名密码不对, 数据库名不对, 数据库ip端口不对~
        //    java.sql.Connection, 别用错了
        Connection connection = dataSource.getConnection();
        // [1, 2, 3] 这三个步骤一个程序启动的时候, 只需要做一次即可
        // 但是有些程序中, 也可能会把 1, 2 只做一次, 3 后面反复执行~
        // 池~~ 备胎池~~  数据库连接池~

        // 4. 就可以构造一个 SQL, 提交给服务器进行执行了
        //    如果构造的 SQL 的信息在变量里, 也可以借助 PreparedStatement 来进行构造
        int id = 101;
        String name = "小李广花荣";
        String sql = "insert into student values(?, '11011', ?, '123@qq.com', 10)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setString(2, name);

        // 5. 执行 SQL, 也就是把 SQL 发送给服务器让服务器进行操作
        //    insert, delete, update 都算 executeUpdate
        int ret = statement.executeUpdate();
        System.out.println("插入结果影响到 " + ret + " 行数据");

        // 6. 收尾工作
        statement.close();
        connection.close();
    }
}
