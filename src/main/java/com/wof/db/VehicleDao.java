package com.wof.db;

import com.wof.entity.UserEntity;
import com.wof.entity.VehicleEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class VehicleDao {
    public VehicleItem selectVehicleByPlate(Connection connection, String plate) throws SQLException {
        assert null != connection;
        PreparedStatement statement = connection.prepareStatement("select * from vehicle WHERE plate = \'" + plate + "\'");
        ResultSet resultSet = statement.executeQuery();

        VehicleItem vehicleItem = new VehicleItem();
        if (resultSet.next()) {
            vehicleItem.plate = resultSet.getString("plate");
            vehicleItem.type = resultSet.getString("type");
            vehicleItem.make = resultSet.getString("make");
            vehicleItem.model = resultSet.getString("model");
            vehicleItem.manufacture_date = resultSet.getDate("manufacture_date");
            vehicleItem.fuel_type = resultSet.getString("fuel_type");
            vehicleItem.user_id = resultSet.getInt("user_id");
        }

        return vehicleItem;
    }

    public int selectVehicleCount(Connection connection) {
        assert null != connection;
        int count = -1;
        try {
            PreparedStatement statement = connection.prepareStatement("select COUNT(*) from vehicle");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException e) {
            count = -1;
        }
        return count;
    }

    public int insertVehicle(Connection connection, VehicleEntity vechile) throws SQLException {
        assert null != connection && null != vechile && vechile.isValid();

        String insert = "insert into vehicle (plate, type, make, model, manufacture_date, fuel_type, user_id) values (?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(insert);
        statement.setString(1, vechile.plate);
        statement.setString(2, vechile.type);
        statement.setString(3, vechile.make);
        statement.setString(4, vechile.model);
        statement.setDate(5, vechile.manufacture_date);
        statement.setString(6, vechile.fuel_type);
        statement.setInt(7, vechile.user_id);

        int insertId = statement.executeUpdate();
        return insertId;

    }

}

