package com.wof.entity;

import com.wof.db.UserItem;
import cucumber.api.java.cs.A;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserEntityTest {

    private UserEntity mUe1;
    private UserEntity mUe2;

    private UserEntity mUe3;

    @Before
    public void setUp() throws Exception {
        mUe1 = new UserEntity();
        mUe1.firstname = "X";
        mUe1.lastname = "Y";
        mUe1.email = "hi@gmail.com";
        mUe1.password = "123";

        mUe2 = new UserEntity();
        mUe2.firstname = "X";
        mUe2.lastname = "Y";

        mUe3 = new UserEntity();
        mUe3.lastname = "Y";
        mUe3.firstname = "X";
        mUe3.password = "123";
        mUe3.email = "hi@gmail.com";
    }

    @Test
    public void isValid() {
        Assert.assertTrue(mUe1.isValid());
        Assert.assertFalse(mUe2.isValid());
    }

    @Test
    public void equals() {
        Assert.assertTrue(mUe1.equals(mUe3));
        Assert.assertFalse(mUe1.equals(mUe2));
    }
}