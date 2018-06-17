package com.wof.db;

import com.wof.entity.UserEntity;
import com.wof.entity.VehicleEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao {

    public int insertUser(Connection connection, UserEntity user) throws SQLException {
        assert null != connection && null != user && user.isValid();

        String insert = "insert into user (firstname, lastname, email, password) values (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(insert);

        statement.setString(1, user.firstname);
        statement.setString(2, user.lastname);
        statement.setString(3, user.email);
        statement.setString(4, user.password);

        int insertId = statement.executeUpdate();
        return insertId;

    }

    public int selectUserCount(Connection connection) {
        assert null != connection;
        int count = -1;
        try {
            PreparedStatement statement = connection.prepareStatement("select COUNT(*) from user");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException e) {
           count = -1;
        }
        return count;
    }

    public UserItem selectUserByEmail(Connection connection, String email) throws SQLException {
        assert null != connection;
        PreparedStatement statement = connection.prepareStatement("select * from user WHERE email = \'" + email + "\'");
        ResultSet resultSet = statement.executeQuery();
        UserItem user = UserItem.create(resultSet);
        return user;
    }

    public UserItem selectUserByEmailAndPassword(Connection connection, String email, String password) throws SQLException {
        assert null != connection;
        PreparedStatement statement = connection.prepareStatement("select * from user WHERE email = \'" + email + "\' AND password = \'" + password + "\'");
        ResultSet resultSet = statement.executeQuery();
        UserItem user = UserItem.create(resultSet);
        return user;
    }

}

