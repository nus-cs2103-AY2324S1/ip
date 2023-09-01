package emiya.datehandler;

import emiya.emiyaexception.InvalidDateException;
import emiya.emiyaexception.WrongDateFormatException;
import emiya.logic.Logic;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import static emiya.parser.Parser.parseForDate;

/**
 * A class defined to determine what LocalDateTime object needs to be instantiated after the input
 * from the user has been parsed.
 */
public class DateHandler {
    /**
     * A static method that builds up the necessary String needed to instantiate the necessary
     * LocalDateTime object.
     * @param strFormat The String input collected from the user
     * @return A LocalDateTime object that contains information about the date and time
     * specified by the user.
     * @throws WrongDateFormatException If the date given by the user is in the wrong format.
     * @throws InvalidDateException If the date given by the user is invalid.
     */
    public static LocalDateTime determineDateTime(String strFormat) throws WrongDateFormatException,
            InvalidDateException {
        String[] parsedDate = parseForDate(strFormat); // strFormat is: YYYY-MM-DD TTTT

        StringBuilder finalDateTimeStr = new StringBuilder(parsedDate[0]);
        finalDateTimeStr.append("T");
        finalDateTimeStr.append(parsedDate[1]);
        finalDateTimeStr.append(":");
        finalDateTimeStr.append(parsedDate[2]);
        finalDateTimeStr.append(":");
        finalDateTimeStr.append("00");

        return LocalDateTime.parse(finalDateTimeStr.toString());
    }

    /**
     * A static method to used when the date and time information of a LocalDateTime object is to be
     * printed out and displayed to the user.
     * @param dateTime Takes in a LocalDateTime object that contains the date and time information
     * @return A String that is formatted in the format MMM DD YYYY HH:MM:SS.
     */
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
