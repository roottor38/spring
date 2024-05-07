package spring.Dependence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.*;

/**
 * <br>
 * 추상화를 사용해서 템플릿 메소드 패턴과 팩트로 메소드 패턴을 적용한 UserDao 클래스
 *
 */
public class SeparatorConcerns123 {

    public static void main(String[] args) {

    }

    public abstract static class UserDao {

        public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

        public class NUserDao extends UserDao {
            @Override
            public Connection getConnection() throws ClassNotFoundException, SQLException {
                Class.forName("com.mysql.jdbc.Driver");
                return DriverManager.getConnection("jdbc:mysql://localhost/springbook", "spring", "book");
            }
        }

        public class DUserDao extends UserDao {
            @Override
            public Connection getConnection() throws ClassNotFoundException, SQLException {
                Class.forName("com.oracle.jdbc.Driver");
                return DriverManager.getConnection("jdbc:oracle://localhost/springbook", "spring", "book");
            }
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


