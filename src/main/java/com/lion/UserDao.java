package com.lion;

import com.lion.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {

    public void add() throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id,name,password) VALUES(?,?,?)");
        ps.setString(1, "4");
        ps.setString(2,"kun");
        ps.setString(3,"951110");

        int status = ps.executeUpdate();
        System.out.println(status);
        ps.close();
        conn.close();
        System.out.println("DB에 등록이 완료되었습니다.");

    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        PreparedStatement ps = conn.prepareStatement("SELECT id,name,password FROM users WHERE id =?");
        ps.setString(1,id); // id는 파라미터로 받은 id
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"), rs.getString("name"),
                rs.getString("password"));
        rs.close();
        ps.close();
        conn.close();

        return user;
    }

    public User getAll() throws ClassNotFoundException, SQLException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        PreparedStatement ps = conn.prepareStatement("SELECT id,name,password FROM users");
        ResultSet rs = ps.executeQuery();
        rs.next(); // next는 한번 읽는 것이다. 
        User user = new User(rs.getString("id"), rs.getString("name"),
                rs.getString("password"));
        rs.close();
        ps.close();
        conn.close();

        return user;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        userDao.add();
        System.out.println(userDao.get("1"));

    }
}
