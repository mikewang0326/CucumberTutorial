package com.wof.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TimeUtilTest {

    private String[] mValidTimeStrings = null;
    private String[] mInvalidTimeStrings = null;

    @Before
    public void setUp() throws Exception {
        // valid date string
        mValidTimeStrings = new String[]{"2010-1-10", "2009-01-02", "2010-1-1"};

        // invalid date string
        mInvalidTimeStrings = new String[]{"0012-0-1", "0-19-1", "0010-1-0"};
    }

    @After
    public void tearDown() throws Exception {
        mValidTimeStrings = null;
        mInvalidTimeStrings = null;
    }

    @Test
    public void isValidDate() {
        // valid date string
        for (int i = 0; i < mValidTimeStrings.length; i++) {
            Assert.assertTrue(TimeUtil.isValidDate(mValidTimeStrings[i]));
        }

        // invalid date string
        for (int i = 0; i < mInvalidTimeStrings.length; i++) {
            Assert.assertFalse(TimeUtil.isValidDate(mInvalidTimeStrings[i]));
        }
    }

    @Test
    public void convertTimeStringToSqlDate() {

        // valid date string
        for (int i = 0; i < mValidTimeStrings.length; i++) {
            java.sql.Date date = TimeUtil.convertTimeStringToSqlDate(mValidTimeStrings[i]);
            Assert.assertTrue((mValidTimeStrings[i].replace("-0", "-")).equals(null != date ? date.toString().replace("-0", "-") : ""));
        }

        // invalid date string
        for (int i = 0; i < mInvalidTimeStrings.length; i++) {
            java.sql.Date date = TimeUtil.convertTimeStringToSqlDate(mInvalidTimeStrings[i]);
            Assert.assertFalse((mValidTimeStrings[i].replace("-0", "-")).equals(null != date ? date.toString().replace("-0", "-") : ""));
        }

    }
}