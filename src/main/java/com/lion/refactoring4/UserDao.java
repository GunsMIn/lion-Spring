package com.lion.refactoring4;

import com.lion.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {
    //팩토리 패턴의 주 목적은 userDao의 클라이언트인 userDaoTest에서 구현클래스를 결정하게 하는 것

    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id,name,password) VALUES(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2,user.getName());
        ps.setString(3,user.getPassword());

        int status = ps.executeUpdate();
        System.out.println(status);
        ps.close();
        conn.close();
        System.out.println("DB에 등록이 완료되었습니다.");

    }

    public User findById(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();
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

    //id로 delete 해주는 메소드 (매개변수 :id)
    public void deleteById(String id) throws ClassNotFoundException, SQLException{
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id = ?");
        ps.setString(1,id);
        int status = ps.executeUpdate();
        System.out.println(status);
        ps.close();
        conn.close();
        System.out.println(id+"번의 USER가 삭제 되었습니다");
    }

    //deleteAll 메소드 (모두 지우는)
    public void deleteAll() throws ClassNotFoundException, SQLException{
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM users");
        int status = ps.executeUpdate();
        System.out.println(status);
        ps.close();
        conn.close();
        System.out.println("USER 모두가 삭제 되었습니다");
    }

    //getCount 메소드 (user개수)
    public void getCount() throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT count(*) as cnt FROM users");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("유저수 : "+rs.getInt("cnt"));
        }
        rs.close();
        ps.close();
        conn.close();
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

       /* User user = new User("13", "인터", "1234");
        UserDao u = new UserDao();
        u.add(user);*/


    }

}
