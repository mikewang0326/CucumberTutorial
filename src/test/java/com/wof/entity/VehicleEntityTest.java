package com.wof.entity;

import com.wof.utils.TimeUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VehicleEntityTest {
    private VehicleEntity mVe1;

    private VehicleEntity mVe2;
    private VehicleEntity mVe3;

    @Before
    public void setUp() throws Exception {
        mVe1 = new VehicleEntity();
        mVe1.plate = "KPG123";
        mVe1.type = "MA";
        mVe1.make = "Toyoto";
        mVe1.model = "Reiz";
        mVe1.manufacture_date = TimeUtil.convertTimeStringToSqlDate("2010-9-11");
        mVe1.fuel_type = "diesel";
        mVe1.user_id = 1;


        mVe2 = new VehicleEntity();

        mVe3 = new VehicleEntity();
        mVe3.user_id = 1;
        mVe3.type = "MA";
        mVe3.plate = "KPG123";
        mVe3.model = "Reiz";
        mVe3.make = "Toyoto";
        mVe3.fuel_type = "diesel";
        mVe3.manufacture_date = TimeUtil.convertTimeStringToSqlDate("2010-9-11");
    }

    @After
    public void tearDown() throws Exception {
        mVe1 = null;
        mVe1 = null;
        mVe2 = null;
    }

    @Test
    public void isValid() {
        Assert.assertTrue(mVe1.isValid());
        Assert.assertFalse(mVe2.isValid());
        Assert.assertTrue(mVe3.isValid());
    }

    @Test
    public void equals() {
        Assert.assertTrue(mVe1.equals(mVe3));
        Assert.assertFalse(mVe1.equals(mVe2));
    }
}