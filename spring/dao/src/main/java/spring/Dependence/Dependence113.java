package spring.Dependence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.*;

/**
 * <br>
 * main을 이용한 DAO 테스트 코드
 *
 */
public class Dependence113 {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("married");

        try {
            userDao.add(user);
            System.out.println(user.getId() + " 등록 성공");

            User user2 = userDao.get(user.getId());
            System.out.println(user2.getName());
            System.out.println(user2.getPassword());

            System.out.println(user2.getId() + " 조회 성공");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static class UserDao {

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
    public static class User {
        private String id;
        private String name;
        private String password;
    }
}


