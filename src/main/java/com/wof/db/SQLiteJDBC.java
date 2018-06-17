package com.wof.db;

import com.wof.entity.UserEntity;
import com.wof.entity.VehicleEntity;
import com.wof.utils.FileUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLiteJDBC {
    public static final int DELETE_TABLE_SUCCEED_STATUS = 0;
    public static final int CREATE_TABLE_SUCCEED_STATUS = 0;

    public UserItem selectUserByEmail(Connection connection, String email) throws SQLException {
        return new UserDao().selectUserByEmail(connection,email);
    }

    public int insertUser(Connection connection, UserEntity user) throws SQLException {
        return new UserDao().insertUser(connection, user);

    }

    public UserItem selectUserByEmailAndPassword(Connection connection, String email, String password) throws SQLException {
       return new UserDao().selectUserByEmailAndPassword(connection, email, password);
    }

    public VehicleItem selectVehicleByPlate(Connection connection, String plate) throws SQLException {
        return new VehicleDao().selectVehicleByPlate(connection, plate);
    }

    public int insertVehicle(Connection connection, VehicleEntity vechile) throws SQLException {
        return new VehicleDao().insertVehicle(connection, vechile);

    }

    public boolean reset(Connection connection) {
        return deleteTables(connection) && createTables(connection);
    }

    private boolean deleteTables(Connection connection) {
        boolean ret = false;
        assert null != connection;
        try {
            PreparedStatement statementVehicle = connection.prepareStatement("DROP TABLE IF EXISTS vehicle");
            int isDeleteVehicleSucceed = statementVehicle.executeUpdate();

            if (isDeleteVehicleSucceed == DELETE_TABLE_SUCCEED_STATUS) {
                PreparedStatement statement = connection.prepareStatement("DROP TABLE IF EXISTS user");
                int isDeleteUserSucceed = statement.executeUpdate();
                if (isDeleteUserSucceed == DELETE_TABLE_SUCCEED_STATUS) {
                    ret = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ret = false;
        }

        return ret;
    }

    private boolean createTables(Connection connection) {
        boolean ret = false;
        assert null != connection;
        try {

            String createUserSql = FileUtil.readFile("./sql/user.create.sql");
            PreparedStatement statement = connection.prepareStatement(createUserSql);
            int isCreateUserSucceed = statement.executeUpdate();

            if (isCreateUserSucceed == CREATE_TABLE_SUCCEED_STATUS) {

                String createVehicleSql = FileUtil.readFile("./sql/vehicle.create.sql");
                PreparedStatement statementVehicle = connection.prepareStatement(createVehicleSql);
                int isCreateVehicleSucceed = statementVehicle.executeUpdate();
                if (isCreateVehicleSucceed == CREATE_TABLE_SUCCEED_STATUS) {
                    ret = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            ret = false;
        } catch (SQLException e) {
            e.printStackTrace();
            ret = false;
        }

        return ret;
    }


    public void resample(Connection connection) {

    }

}

