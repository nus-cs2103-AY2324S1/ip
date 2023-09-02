package emiya.datehandler;

import static emiya.parser.Parser.parseForDate;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import emiya.emiyaexception.InvalidDateException;
import emiya.emiyaexception.WrongDateFormatException;


public class DateHandler {

    public static LocalDateTime determineDateTime(String strFormat)
            throws WrongDateFormatException, InvalidDateException {
        String[] parsedDate = parseForDate(strFormat);

        StringBuilder finalDateTimeStr = new StringBuilder(parsedDate[0]);
        finalDateTimeStr.append("T");
        finalDateTimeStr.append(parsedDate[1]);
        finalDateTimeStr.append(":");
        finalDateTimeStr.append(parsedDate[2]);
        finalDateTimeStr.append(":");
        finalDateTimeStr.append("00");

        return LocalDateTime.parse(finalDateTimeStr.toString());
    }

    public static String correctDateTimeFormat(LocalDateTime dateTime) {
        int year = dateTime.getYear();
        Month month = dateTime.getMonth();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();
        int min = dateTime.getMinute();

        StringBuilder finalStr = new StringBuilder();
        finalStr.append(month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        finalStr.append(" ");
        finalStr.append(day);
        finalStr.append(" ");
        finalStr.append(year);
        finalStr.append(" ");
        finalStr.append(hour < 10 ? "0" + hour : hour);
        finalStr.append(":");
        finalStr.append(min < 10 ? "0" + min : min);
        finalStr.append(":");
        finalStr.append("00");

        return finalStr.toString();
    }
}
