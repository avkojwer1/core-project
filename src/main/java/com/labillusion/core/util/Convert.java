package com.labillusion.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by greg.chen on 14-10-10.
 */
public class Convert {

    public static final String DATE_FORMAT_DATE = "MM/dd/yyyy";
    public static final String DATE_FORMAT_DATETIME = "MM/dd/yyyy'T'HH:mm:ss";

    public static Integer toInt(String text, Integer defaultValue) {
        if (!StringUtils.hasText(text))
            return defaultValue;
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static Long toLong(String text, Long defaultValue) {
        if (!StringUtils.hasText(text))
            return defaultValue;
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static Double toDouble(String text, Double defaultValue) {
        if (!StringUtils.hasText(text))
            return defaultValue;
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // since DateFormat is not thread safe, we create for each parsing
    public static Date toDate(String date, String formatPattern, Date defaultValue) {
        if (!StringUtils.hasText(date)) {
            return defaultValue;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatPattern);
            return format.parse(date);
        } catch (ParseException e) {
            return defaultValue;
        }
    }

    public static Date toDate(String date, String formatPattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatPattern);
            return format.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Date toDate(String date, Date defaultValue) {
        return toDate(date, DATE_FORMAT_DATE, defaultValue);
    }

    public static Date toDateTime(String date, Date defaultValue) {
        return toDate(date, DATE_FORMAT_DATETIME, defaultValue);
    }

    public static String toString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String toString(Date date, String format, TimeZone timeZone) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }

}
