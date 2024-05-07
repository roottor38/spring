package spring;

import spring.dao.DConnectionMaker;
import spring.dao.DUserDao;
import spring.dao.NUserDao;
import spring.dao.UserDao;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DConnectionMaker c = new DConnectionMaker();

        UserDao dao = new UserDao(c);

    }
}