package emiya.datehandler;

import emiya.emiyaexception.InvalidDateException;
import emiya.emiyaexception.WrongDateFormatException;
import emiya.logic.Logic;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateHandler {


    // strFormat is: YYYY-MM-DD TTTT
    public static LocalDateTime determineDateTime(String strFormat) throws WrongDateFormatException, InvalidDateException {
        String[] partsOfDateTime = strFormat.split("\\s+", 2);

        // word/no whitespace used
        if (partsOfDateTime.length <= 1) {
            throw new WrongDateFormatException();
        }

        String datePart = partsOfDateTime[0];
        String timePart = partsOfDateTime[1];
        String[] partsOfDate = datePart.split("-", 3);

        // if second part is not the time in 24h format/date not given in correct format
        if (timePart.length() != 4 || !Logic.isNumeric(timePart.substring(0, 2)) || !Logic.isNumeric(timePart.substring(2, 4)) ||
                partsOfDate.length < 3 || !Logic.isNumeric(partsOfDate[0]) || !Logic.isNumeric(partsOfDate[1])
                || !Logic.isNumeric(partsOfDate[2])) {
            throw new WrongDateFormatException();
        }

        int year = Integer.parseInt(partsOfDate[0]);
        int month = Integer.parseInt(partsOfDate[1]);
        int day = Integer.parseInt(partsOfDate[2]);
        int hour = Integer.parseInt(timePart.substring(0, 2));
        int min = Integer.parseInt(timePart.substring(2, 4));

        // if given date is invalid
        if (!Logic.isValidYear(year) || !Logic.isValidMonth(month) || !Logic.isValidDay(day) || !Logic.isValidHour(hour) || !Logic.isValidMinute(min)) {
            throw new InvalidDateException();
        }

        StringBuilder finalDateTimeStr = new StringBuilder(datePart);
        finalDateTimeStr.append("T");
        finalDateTimeStr.append(hour < 10 ? "0" + hour : hour);
        finalDateTimeStr.append(":");
        finalDateTimeStr.append(min < 10 ? "0" + min : min);
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
