package com.wof.db;

import com.wof.utils.DBConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnectionInstance {
    private static SQLiteConnectionInstance instance;
    private static Connection mConnection;

    private SQLiteConnectionInstance() { }

    public static SQLiteConnectionInstance getInstance() {
        if (null == instance) {
            instance = new SQLiteConnectionInstance();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        if (null == mConnection) {
            try {
                mConnection = DriverManager.getConnection(DBConstant.SQLITE_CONNECTION_URL);
            } catch (SQLException e) {
                mConnection = null;
            }
        }

        return mConnection;
    }
}
