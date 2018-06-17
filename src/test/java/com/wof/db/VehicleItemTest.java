package com.wof.db;

import com.wof.entity.VehicleEntity;
import com.wof.utils.TextUtils;
import com.wof.utils.TimeUtil;
import cucumber.api.java.cs.A;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class VehicleItemTest {
    private VehicleItem mVi1;
    private VehicleEntity mVe1;

    private VehicleItem mVi2;
    private VehicleItem mVi3;

    @Before
    public void setUp() throws Exception {
        mVi1 = new VehicleItem();
        mVi1.plate = "KPG123";
        mVi1.type = "MA";
        mVi1.make = "Toyoto";
        mVi1.model = "Reiz";
        mVi1.manufacture_date = TimeUtil.convertTimeStringToSqlDate("2010-9-11");
        mVi1.fuel_type = "diesel";
        mVi1.user_id = 1;

        mVe1 = new VehicleEntity();
        mVe1.user_id = 1;
        mVe1.plate = "KPG123";
        mVe1.type = "MA";
        mVe1.make = "Toyoto";
        mVe1.model = "Reiz";
        mVe1.manufacture_date = TimeUtil.convertTimeStringToSqlDate("2010-9-11");
        mVe1.fuel_type = "diesel";

        mVi2 = new VehicleItem();

        mVi3 = new VehicleItem();
        mVi3.user_id = 1;
        mVi3.type = "MA";
        mVi3.plate = "KPG123";
        mVi3.model = "Reiz";
        mVi3.make = "Toyoto";
        mVi3.fuel_type = "diesel";
        mVi3.manufacture_date = TimeUtil.convertTimeStringToSqlDate("2010-9-11");
    }

    @After
    public void tearDown() throws Exception {
        mVi1 = null;
        mVe1 = null;
        mVi2 = null;
    }

    @Test
    public void isValid() {
        Assert.assertTrue(mVi1.isValid());
        Assert.assertFalse(mVi2.isValid());
    }

    @Test
    public void equals() {
        Assert.assertTrue(mVi1.equals(mVi3));
        Assert.assertFalse(mVi1.equals(mVi2));
    }

    @Test
    public void convertItemToEntity() {
        Assert.assertTrue(mVe1.equals(VehicleItem.convertItemToEntity(mVi1)));
    }
}