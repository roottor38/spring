package spring.Dependence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.*;

/**
 * add, get 메소드를 가지고 있는 UserDao 클래스
 * <br>
 * 모르는것 : Class.forName, PreparedStatement
 *
 */
public class Dependence111 {

    public class UserDao {

        public void add(User user) throws SQLException, ClassNotFoundException {

            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/springbook", "spring", "book");

            PreparedStatement ps = c.prepareStatement("insert into users values(?, ?, ?)");
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();

            ps.close();
            c.close();
        }

        public User get(String id) throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/springbook", "spring", "book");
            PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            rs.close();
            ps.close();
            c.close();

            return user;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public class User {
        private String id;
        private String name;
        private String password;
    }
}


