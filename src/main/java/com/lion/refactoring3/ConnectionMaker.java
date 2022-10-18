package com.lion.refactoring3;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    //인터페이스
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
