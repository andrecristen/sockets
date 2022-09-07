package utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String FORMAT_DATE_DEFAULT = "yyyy-MM-dd";
    public static final String FORMAT_DATE_BRAZILIAN = "dd/MM/yyyy";

    public static Date stringToDateTime(String dateString, String format) {
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat formater = new SimpleDateFormat(format);
        return formater.parse(dateString, pos);
    }

    public static String dateTimeToString(Date date, String format) {
        return (new SimpleDateFormat(format)).format(date);
    }

}
