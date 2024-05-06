package spring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 템플릿 메소드 패턴
public class NUserDao extends UserDao {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/springbook", "naver", "book");
    }
}
