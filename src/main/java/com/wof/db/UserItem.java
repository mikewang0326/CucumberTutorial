package com.wof.db;

import com.wof.entity.UserEntity;
import com.wof.utils.TextUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserItem {
    public int user_id = -1;
    public String firstname;
    public String lastname;
    public String email;
    public String password;

    public boolean isValid() {
        boolean ret = false;
        if (user_id > 0 && !TextUtils.isEmpty(firstname) && !TextUtils.isEmpty(lastname)
                && TextUtils.isValidEmail(email) && !TextUtils.isEmpty(password)) {
            ret = true;
        }

        return ret;
    }


    @Override
    public boolean equals(Object obj) {
        boolean ret = false;
        if (null != obj && obj instanceof UserItem) {
            UserItem outUserItem = (UserItem) obj;
            if (outUserItem.isValid() && outUserItem.user_id == this.user_id && outUserItem.firstname.equals(this.firstname)
                    && outUserItem.lastname.equals(this.lastname)
                    && outUserItem.email.equals(this.email)
                    && outUserItem.password.equals(this.password)) {
                ret = true;
            }
        }
        return ret;
    }

    public static UserEntity convertItemToEntity(UserItem userItem) {
        UserEntity userEntity = new UserEntity();
        userEntity.firstname = userItem.firstname;
        userEntity.lastname = userItem.lastname;
        userEntity.email = userItem.email;
        userEntity.password = userItem.password;
        return userEntity;
    }

    public static UserItem create(ResultSet resultSet) {
        UserItem user = new UserItem();
        try {
            if (resultSet.next()) {
                user.user_id = resultSet.getInt("user_id");
                user.firstname = resultSet.getString("firstname");
                user.lastname = resultSet.getString("lastname");
                user.email = resultSet.getString("email");
                user.password = resultSet.getString("password");

            }
        } catch (SQLException e) {
            user = new UserItem();
        }

        return user;
    }

}

