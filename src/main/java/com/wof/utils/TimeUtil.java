package com.wof.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {
    public static boolean isValidDate(String dateString) {
        boolean isValid = false;
        if (!TextUtils.isEmpty(dateString)) {
            String rex = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
            Pattern pattern = Pattern.compile(rex);
            Matcher matcher = pattern.matcher(dateString);
            isValid = matcher.matches();
        }
        return isValid;
    }

    public static java.sql.Date convertTimeStringToSqlDate(String dateString) {
        java.sql.Date sqlDate = null;
        try {
            if (isValidDate(dateString)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date utilDate = simpleDateFormat.parse(dateString);
                sqlDate = new java.sql.Date(utilDate.getTime());
            }
        } catch (ParseException e) {
            sqlDate = null;
        }

        return sqlDate;
    }
}
