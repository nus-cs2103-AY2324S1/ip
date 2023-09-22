package com.nyanbot.dukeparsers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.nyanbot.dukeexceptionhandlers.DukeExceptionHandlers;

/**
 * This class contains the fields and methods needed to parse DateTime objects.
 * Handles issues with the dates.
 * @author Tan Kerway
 */
public class DukeTimeParser {
    protected static final LocalDateTime DEFAULT_DATE = LocalDateTime.of(1970, 1, 1, 0, 0);
    protected static String[] dateFormats = new String[] {
        "0",
        // time string with day and time
        "E HHmm",
        "E h'.'mma",
        "E ha",
        // time string with day only(e.g. tue, wed, etc)
        "E",

        "1",
        // time with all required information: year, month, day of month, time
        "MMM d HH:mm yyyy",
        "y-MM-dd HHmm",
        "yyyy-MM-dd HHmm",
        "yyyy-MM-dd HH'.'mma",
        "EEE MMM d HH:mm:ss zzz yyyy",
        "EEE MMM d HH:mm yyyy",
        "d/M/yy HHmm",
        "d/M/yyyy HHmm",
        "d/M/y ha",

        "2",
        // time with these information: year, month, day of month
        "y-MM-dd",
        "d/M/yy",
        "d/M/yyyy",
        "d/M/y",

        "3",
        // time with these information: month, day of month, time
        "MMM dd HH'.'mma",
        "dd MMM HH'.'mma",
        "MMM dd HHmm",
        "dd MMM HHmm",
        "d/M HHmm",
        "MMM dd ha",
        "dd MMM ha",
        "d/M ha",

        "4",
        // time with these information: month and day
        "MMM dd",
        "dd MMM",
        "d/M"
    };

    /**
     * Converts the string date into a Datetime object.
     *
     * @author Tan Kerway
     * @param time the String that contains the data for the date
     * @param error the specific string to modify the DukeExceptions.InvalidTimeException
     * @return datetime object that represents the string
     */
    public LocalDateTime parseDate(String time, String error) {
        int currentFormatID = -1;
        for (String formatString : dateFormats) { // find a date format string that matches the user pattern
            try {
                if (isNumeric(formatString)) {
                    currentFormatID = Integer.parseInt(formatString);
                    continue;
                }
                LocalDateTime temp = new SimpleDateFormat(formatString)
                        .parse(time)
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                return addMissingDateFields(temp, currentFormatID, error);
            } catch (ParseException ignored) {
                String s = "";
            }
        }
        DukeExceptionHandlers.handleNoDate(error);
        return null;
    }

    /**
     * Appends missing date fields depending on what the user typed in.
     * @author Tan Kerway
     * @param temp the LocalDateTime object to modify
     * @param currentFormatID the given type of date object
     * @param error the error String(if any)
     * @return a LocalDateTime object that has all the required information
     */
    private LocalDateTime addMissingDateFields(LocalDateTime temp, int currentFormatID, String error) {
        if (currentFormatID == 0) { // case where the current format is a day(index 0)
            // add the year, month, day, and time(default to 2359)
            temp = addDay(temp);
        } else if (currentFormatID == 1) {
            // case where the current format is not missing anything(index 1 to 7)
            // keep the final date as is
        } else if (currentFormatID == 2) { // case where the current format is missing time(index 8 to 11)
            // add 2359 to the final date
            temp = addTime(temp);
        } else if (currentFormatID == 3) { // case where the current format is missing year(index 12 to 19)
            // add the year to the final date
            temp = addYear(temp);
        } else { // case where the current format is missing year and time(index 20 to 22)
            // add the year and time to the final date
            temp = addYear(temp);
            temp = addTime(temp);
            if (temp.isBefore(LocalDateTime.now())) {
                temp = temp.plusYears(1);
            }
        }
        // gc: the given date is before today's date even after parsing
        if (temp.isBefore(LocalDateTime.now())) {
            DukeExceptionHandlers.handleNoDate(error);
        }
        return temp;
    }

    /**
     * Checks if the given String is numeric or not.
     *
     * @author Tan Kerway
     * @param str the string to check
     * @return true if the string is numeric, false otherwise
     */
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ignored) {
            String s = "";
        }
        return false;
    }

    /**
     * Adds the year to the date object.
     *
     * @author Tan Kerway
     * @param time the LocalDateTime object that represents start dates, or deadlines
     * @return the modified LocalDateTime object
     */
    private LocalDateTime addYear(LocalDateTime time) {
        assert time != null : "time should be not null";
        LocalDateTime now = LocalDateTime.now();
        int yearsElapsed = now.getYear() - DEFAULT_DATE.getYear();
        // update the year by adding to the current year
        // why: this method is called when the input date does not have a year
        return time.plusYears(yearsElapsed);
    }

    /**
     * Adds the day to the date object.
     *
     * @author Tan Kerway
     * @param time the LocalDateTime object that represents start dates, or deadlines
     * @return the modified LocalDateTime object
     */
    private LocalDateTime addDay(LocalDateTime time) {
        assert time != null : "time should be not null";
        LocalDateTime now = LocalDateTime.now();
        int defaultDay = DEFAULT_DATE.getDayOfWeek().getValue();
        int timeDay = time.getDayOfWeek().getValue();
        int daysElapsed = timeDay >= defaultDay ? timeDay - defaultDay : 7 - defaultDay + timeDay;
        // update the current date by the number of days
        LocalDateTime res = now.plusDays(daysElapsed);
        if (time.getHour() == 0 && time.getMinute() == 0) {
            return addTime(res);
        }
        return res.withHour(time.getHour()).withMinute(time.getMinute());
    }

    /**
     * Updates the time fields of the LocalDateTime object.
     *
     * @author Tan Kerway
     * @param time the given LocalDateTime object
     * @return a new LocalDateTime object, with the same parameters as time, but with hour field
     *         set to 23 and minute field set to 59
     */
    private LocalDateTime addTime(LocalDateTime time) {
        assert time != null : "time should be not null";
        return time.withHour(23).withMinute(59);
    }

    /**
     * Formats the time String.
     *
     * @author Tan Kerway
     * @param time the given LocalDateTime object to format
     * @return the string form of the LocalDateTime object
     */
    public String formatDate(LocalDateTime time) {
        assert time != null : "time should be not null";
        return time.format(DateTimeFormatter.ofPattern("MMM dd yyyy',' H.mma"));
    }
}
