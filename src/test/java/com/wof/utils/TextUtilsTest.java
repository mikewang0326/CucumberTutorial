package com.wof.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextUtilsTest {
    private String normalString;
    private String nullString;
    private String emptyString;

    @Before
    public void setUp() throws Exception {
        normalString = "xxxxxxx";
        nullString = null;
        emptyString = "";
    }

    @After
    public void tearDown() throws Exception {
        normalString = null;
        nullString = null;
        emptyString = null;
    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(!TextUtils.isEmpty(normalString));
        Assert.assertTrue(TextUtils.isEmpty(nullString));
        Assert.assertTrue(TextUtils.isEmpty(emptyString));
    }

    @Test
    public void isValidEmail() {
        Assert.assertTrue(TextUtils.isValidEmail("hi@gmail.com"));
        Assert.assertTrue(TextUtils.isValidEmail("hi123@gmail.com"));
        Assert.assertTrue(TextUtils.isValidEmail("123@gmail.com"));

        Assert.assertFalse(TextUtils.isValidEmail("123"));
        Assert.assertFalse(TextUtils.isValidEmail("123gmail.com"));
        Assert.assertFalse(TextUtils.isValidEmail("@gmail.com"));
        Assert.assertFalse(TextUtils.isValidEmail("123@gmail"));
        Assert.assertFalse(TextUtils.isValidEmail("@"));
    }



}