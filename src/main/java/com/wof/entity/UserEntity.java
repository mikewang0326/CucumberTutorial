package com.wof.entity;

import com.wof.utils.TextUtils;

public class UserEntity {
    public String firstname;
    public String lastname;
    public String email;
    public String password;

    public boolean isValid() {
        boolean ret = false;
        if (!TextUtils.isEmpty(firstname) && !TextUtils.isEmpty(lastname)
                && TextUtils.isValidEmail(email) && !TextUtils.isEmpty(password)) {
            ret = true;
        }

        return ret;
    }


    @Override
    public boolean equals(Object obj) {
        boolean ret = false;
        if (null != obj && obj instanceof UserEntity) {
            UserEntity outUserEntity = (UserEntity) obj;
            if (outUserEntity.isValid() && outUserEntity.firstname.equals(this.firstname)
                    && outUserEntity.lastname.equals(this.lastname)
                    && outUserEntity.email.equals(this.email)
                    && outUserEntity.password.equals(this.password)) {
                ret = true;
            }
        }
        return ret;
    }
}

