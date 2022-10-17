package com.dbexcercis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class UserDao {

    public void add() throws SQLException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id,name,password) VALUES(?,?,?)");
        ps.setString(1, "3");
        ps.setString(2,"LEE");
        ps.setString(3,"3213");

        int status = ps.executeUpdate();
        System.out.println(status);
        ps.close();
        conn.close();

    }

    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDao();
        userDao.add();
    }
}
