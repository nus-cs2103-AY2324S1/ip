package emiya.datehandler;

import static emiya.parser.Parser.parseForDate;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import emiya.emiyaexception.InvalidDateTimeException;
import emiya.emiyaexception.WrongDateTimeFormatException;


/**
 * A class defined to determine what LocalDateTime object needs to be instantiated after the input
 * from the user has been parsed.
 */
public class DateHandler {
    private static final String EXAMPLE_OF_CORRECT_FORMAT = "Sep 10 2023 20:00:00";

    /**
     * A static method that builds up the necessary String needed to instantiate the necessary
     *     LocalDateTime object.
     * @param strFormat The String input collected from the user
     * @return A LocalDateTime object that contains information about the date and time
     *     specified by the user.
     * @throws WrongDateTimeFormatException If the date given by the user is in the wrong format.
     * @throws InvalidDateTimeException If the date given by the user is invalid.
     */
    public static LocalDateTime determineDateTime(String strFormat)
            throws WrongDateTimeFormatException, InvalidDateTimeException {
        // after parsing, array will be in format: [date, hour, min]
        String[] parsedDate = parseForDate(strFormat);

        assert parsedDate.length == 3: "If program reaches this point, parsedDate should contain the date and time" +
                "components only and with the correct format.";

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
        finalStr.append(day < 10 ? "0" + day : day);
        finalStr.append(" ");
        finalStr.append(year);
        finalStr.append(" ");
        finalStr.append(hour < 10 ? "0" + hour : hour);
        finalStr.append(":");
        finalStr.append(min < 10 ? "0" + min : min);
        finalStr.append(":");
        finalStr.append("00");
        assert finalStr.length() == EXAMPLE_OF_CORRECT_FORMAT.length() : "Final Date to be printed should be " +
                "the same as the example with correct format.";

        return finalStr.toString();
    }
}
