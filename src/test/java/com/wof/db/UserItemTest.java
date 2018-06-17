package com.wof.db;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class UserItemTest{

    private UserItem ui1;
    private UserItem ui2;

    @Before
    public void setUp() throws Exception {
        ui1 = new UserItem();
        ui1.user_id = 1;
        ui1.firstname = "X";
        ui1.lastname = "Y";
        ui1.email = "hi@gmail.com";
        ui1.password = "123";

        ui2 = new UserItem();
    }

    @After
    public void tearDown() throws Exception {
        ui1 = null;
        ui2 = null;
    }

    @Test
    public void isValid() {
        Assert.assertTrue(ui1.isValid());
        Assert.assertFalse(ui2.isValid());
    }
}