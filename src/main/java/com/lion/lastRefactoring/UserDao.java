package com.lion.lastRefactoring;

import com.lion.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.*;
import java.util.Map;

public class UserDao {

    private ConnectionMaker cm;

    public UserDao() {
        this.cm = new AwsConnectionMaker();
    }

    public UserDao(ConnectionMaker cm) {
        this.cm = cm;
    }

    public void add(User user) {
        Map<String, String> env = System.getenv();
        try {
            // DB접속 (ex sql workbeanch실행)
            Connection c = cm.makeConnection();

            // Query문 작성
            PreparedStatement pstmt = c.prepareStatement("INSERT INTO users(id, name, password) VALUES(?,?,?);");
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());

            // Query문 실행
            pstmt.executeUpdate();

            pstmt.close();
            c.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(String id) {
        Map<String, String> env = System.getenv();
        Connection c;
        try {
            // DB접속 (ex sql workbeanch실행)
            c = cm.makeConnection();

            // Query문 작성
            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setString(1, id);

            // Query문 실행
            User user = null;
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("id"), rs.getString("name"),
                        rs.getString("password"));
            }; //여기가 null일 수 있다}


                rs.close();
                pstmt.close();
                c.close();

            if (user == null) {
                throw new EmptyResultDataAccessException(1);
            }

                return user;

            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        Connection conn = cm.makeConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id = ?");
        ps.setString(1,id);
        int status = ps.executeUpdate();
        System.out.println(status);
        ps.close();
        conn.close();
        System.out.println(id+"번의 USER가 삭제 되었습니다");
    }

    //deleteAll 메소드 (모두 지우는)
    public void deleteAllBefore() throws ClassNotFoundException, SQLException{
        Connection conn = cm.makeConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM users");
        int status = ps.executeUpdate();
        System.out.println(status);
        //이전에 예외가 터져서 connection이 colse()되지 않으면 엄청 심각한 문제로 번질 수있다.
        ps.close();
        conn.close();
        System.out.println("USER 모두가 삭제 되었습니다");
    }

    public void deleteAll() throws ClassNotFoundException, SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        //ctrl + alt + t -> 예외처리
        try {
            conn = cm.makeConnection();
            ps = conn.prepareStatement("DELETE FROM users");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {//이전에 예외가 터져서 connection이 colse()되지 않으면 엄청 심각한 문제로 번질 수있다.
            //finally는 반드시 실행시켜주는 구역이다.
            if (ps != null) { //ps
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        System.out.println("USER 모두가 삭제 되었습니다");
    }


    //getCount 메소드 (user개수)
    public int getCount() throws ClassNotFoundException, SQLException {
        Connection conn = cm.makeConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT count(*) as cnt FROM users");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        ps.close();
        conn.close();
        return count;//리턴 int로 수정
    }

    public int getCount2() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        int count = 0;
        try {
            conn = cm.makeConnection();
            ps = conn.prepareStatement("SELECT count(*) as cnt FROM users");
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (ps != null) { //ps
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        
        return count;//리턴 int로 수정
    }


    public static void main(String[] args) {
        UserDao userDao = new UserDao();
//        userDao.add();
        User user = userDao.findById("6");
        System.out.println(user.getName());
    }
}
