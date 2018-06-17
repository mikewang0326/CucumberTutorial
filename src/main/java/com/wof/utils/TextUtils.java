package com.wof.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

    public static boolean isEmpty(String string) {
        boolean ret = false;
        if (null == string || string.equals("")) {
            ret = true;
        }
        return ret;
    }

    public static boolean isValidEmail(String email) {
        boolean ret = false;
        if (!isEmpty(email)) {
            String reg = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern p;
            Matcher m;
            p = Pattern.compile(reg);
            m = p.matcher(email);
            if (m.matches()) {
                ret = true;
            }
        }
        return ret;
    }
}

