package spring.Dependence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.*;

/**
 * <br>
 * 공통 코드 추출
 *
 */
public class SeparatorConcerns12 {

    public static void main(String[] args) {

    }

    public static class UserDao {

        public Connection getConnection() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/springbook", "spring", "book");
        }

        public void add(User user) throws SQLException, ClassNotFoundException {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("insert into users values(?, ?, ?)");

            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();

            ps.close();
            c.close();
        }

        public User get(String id) throws SQLException, ClassNotFoundException {
            Connection c = getConnection();
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
    public static class User {
        private String id;
        private String name;
        private String password;
    }
}


