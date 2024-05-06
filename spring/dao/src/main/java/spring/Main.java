package spring;

import spring.dao.DUserDao;
import spring.dao.NUserDao;
import spring.dao.UserDao;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dUserDao = new DUserDao();
        UserDao nUserDao = new NUserDao();

        System.out.println(dUserDao.get("1"));

    }
}