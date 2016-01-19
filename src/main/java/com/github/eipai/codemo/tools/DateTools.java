package com.github.eipai.codemo.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTools {
    private static final String datePattern = "yyyy-MM-dd";
    private static final String timePattern = "HH:mm:ss";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(datePattern + " " + timePattern);

    public static String getDateString(Date date) {
        return (null == date) ? null : dateFormat.format(date);
    }

    public static String getDatetimeString(Date date) {
        return (null == date) ? null : datetimeFormat.format(date);
    }

    public static Date getDate(String date) throws ParseException {
        date = StringTools.trim(date);
        if (date.length() < 1) {
            return null;
        }
        return dateFormat.parse(date);
    }

    public static Date getDatetime(String date) throws ParseException {
        date = StringTools.trim(date);
        if (date.length() < 1) {
            return null;
        }
        return datetimeFormat.parse(date);
    }

    public static String getDateString(Date date, String format) {
        return (null == date) ? null : new SimpleDateFormat(format).format(date);
    }

    public static String getDatetimeString(Date date, String format) {
        return (null == date) ? null : new SimpleDateFormat(format).format(date);
    }

    public static Date getDate(String date, String format) throws ParseException {
        date = StringTools.trim(date);
        if (date.length() < 1) {
            return null;
        }
        return new SimpleDateFormat(format).parse(date);
    }

    public static Date getDatetime(String datetime, String format) throws ParseException {
        datetime = StringTools.trim(datetime);
        if (datetime.length() < 1) {
            return null;
        }
        return new SimpleDateFormat(format).parse(datetime);
    }

    public static Date getLocalDatetime(String local) throws ParseException {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone(local));
        cal.setTimeInMillis(Calendar.getInstance().getTimeInMillis());
        return getDatetime(getLocalDatetimeString(local));
    }

    public static String getLocalDateString(String local) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone(local));
        cal.setTimeInMillis(Calendar.getInstance().getTimeInMillis());
        String date = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
        return date;
    }

    public static String getLocalTimeString(String local) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone(local));
        cal.setTimeInMillis(Calendar.getInstance().getTimeInMillis());
        String time = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        return time;
    }

    public static String getLocalDatetimeString(String local) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone(local));
        cal.setTimeInMillis(Calendar.getInstance().getTimeInMillis());
        String date = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
        String time = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        return date + " " + time;
    }

    public static int getYear(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(date.getTime());
        return cal.get(Calendar.YEAR);
    }

    public static int getYear(Date date, String local) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone(local));
        cal.setTimeInMillis(date.getTime());
        return cal.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(date.getTime());
        return cal.get(Calendar.MONTH);
    }

    public static int getMonth(Date date, String local) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone(local));
        cal.setTimeInMillis(date.getTime());
        return cal.get(Calendar.MONTH);
    }

    public static int getDay(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(date.getTime());
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDay(Date date, String local) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone(local));
        cal.setTimeInMillis(date.getTime());
        return cal.get(Calendar.DAY_OF_MONTH);
    }
}
