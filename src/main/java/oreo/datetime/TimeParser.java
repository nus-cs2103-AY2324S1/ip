package oreo.datetime;

import oreo.exception.IllegalDateTimeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Implements class that handles parsing of time input
 *
 * @author Daniel Loh
 * @version 03/09/2023
 */
public class TimeParser {
    /**
     * Parses date input for display
     *
     * @param input date input
     * @return output date
     * @throws IllegalDateTimeException if invalid format
     */
    private static String parseDateOut(String input) throws IllegalDateTimeException {
        String modifiedInput = input.replace("/", "-");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date;
        try {
            date = LocalDate.parse(modifiedInput, format);
        } catch (DateTimeException e) {
            throw new IllegalDateTimeException("Date or date format is invalid\n"
                    + "try dd/mm/yyyy format instead");
        }
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Parses date for file data
     *
     * @param input date as displayed
     * @return output date for file data
     * @throws IllegalDateTimeException if invalid format
     */
    public static String parseDateForFile(String input) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate date = LocalDate.parse(input, format);
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Parses time input for display
     *
     * @param input time input
     * @return output time
     * @throws IllegalDateTimeException if invalid format
     */
    public static String parseTimeOut(String input) throws IllegalDateTimeException{
        LocalTime time;
        try {
            time = LocalTime.parse(input,
                    DateTimeFormatter.ofPattern("HHmm"));
        } catch (DateTimeException e) {
            throw new IllegalDateTimeException(("Time or time format is invalid\n"
                    + "try HHmm format instead"));
        }
        return time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    /**
     * Parses time for file data
     *
     * @param input time input
     * @return output time
     * @throws IllegalDateTimeException if invalid format
     */
    public static String parseTimeForFile(String input) {
        if (input == null) {
            return "";
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("h:mm a");
            LocalTime time = LocalTime.parse(input, format);
            return " " + time.format(DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    /**
     * Parses input for display
     *
     * @param input date time input by user
     * @return date time format for display
     * @throws IllegalDateTimeException invalid format
     */
    public static String[] parseInputOut(String input) throws IllegalDateTimeException {
        String[] out = new String[2];
        String[] dateTime = input.split("\\s+");
        out[0] = parseDateOut(dateTime[0]);
        if (dateTime.length == 2) {
            out[1] = parseTimeOut(dateTime[1]);
            return out;
        }
        return out;
    }

    /**
     * Checks if to date is after from date
     *
     * @param fromDate start date
     * @param toDate end date
     * @throws IllegalDateTimeException if end date is before start date
     */
    public static void checkValidEventDate(String fromDate, String toDate) throws IllegalDateTimeException {
        LocalDate from = LocalDate.parse(fromDate,
                DateTimeFormatter.ofPattern("MMM d yyyy"));
        LocalDate to = LocalDate.parse(toDate,
                DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (to.isBefore(from)) {
            throw new IllegalDateTimeException("to date cannot be earlier than from date");
        }
    }

    /**
     * Checks if to time is after from time
     *
     * @param fromTime start time
     * @param toTime end time
     * @throws IllegalDateTimeException if end time is before start time in the same day
     */
    public static void checkValidEventTime(String fromTime, String toTime) throws IllegalDateTimeException {
        LocalTime from = LocalTime.parse(fromTime,
                DateTimeFormatter.ofPattern("h:mm a"));
        LocalTime to = LocalTime.parse(toTime,
                DateTimeFormatter.ofPattern("h:mm a"));
        if (to.isBefore(from)) {
            throw new IllegalDateTimeException("to time cannot be before from time");
        }
    }
}
