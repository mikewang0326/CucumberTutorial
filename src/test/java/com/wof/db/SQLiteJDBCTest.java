package com.wof.db;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class SQLiteJDBCTest {
    private SQLiteJDBC mSQLiteJDBC;

    @Before
    public void setUp() throws Exception {
        mSQLiteJDBC = new SQLiteJDBC();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void reset() {
        boolean isSucceed = mSQLiteJDBC.reset(SQLiteConnectionInstance.getInstance().getConnection());
        Assert.assertTrue(isSucceed);

        Assert.assertTrue(new UserDao().selectUserCount(SQLiteConnectionInstance.getInstance().getConnection()) == 0
                && new VehicleDao().selectVehicleCount(SQLiteConnectionInstance.getInstance().getConnection()) == 0);
    }

    @Test
    public void resample() {
//        mSQLiteJDBC.resample(SQLiteConnectionInstance.getInstance().getConnection());
    }
}