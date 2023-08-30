package datehandler;

import emiyaexception.WrongDateFormatException;
import logic.Logic;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateHandler {

    public static boolean testFun() {
        return true;
    }

    // strFormat is: YYYY-MM-DD TTTT
    public static LocalDateTime determineDateTime(String strFormat) throws WrongDateFormatException {
        String[] partsOfDateTime = strFormat.split("\\s+", 2);

        String datePart = partsOfDateTime[0];
        String timePart = partsOfDateTime[1];

        if (!Logic.isNumeric(datePart) || !Logic.isNumeric(timePart)) {
            throw new WrongDateFormatException();
        }

        int hour = Integer.parseInt(timePart.substring(0, 2));
        int min = Integer.parseInt(timePart.substring(2, 4));

        if (hour < 0 || hour > 23 || min < 0 || min > 59) {
            throw new WrongDateFormatException();
        }

        StringBuilder finalDateTimeStr = new StringBuilder(datePart);
        finalDateTimeStr.append("T");
        finalDateTimeStr.append(hour);
        finalDateTimeStr.append(":");
        finalDateTimeStr.append(min);
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
        finalStr.append(hour);
        finalStr.append(":");
        finalStr.append(min);
        finalStr.append(":");
        finalStr.append("00");

        return finalStr.toString();
    }
}
