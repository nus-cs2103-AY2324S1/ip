package oreo.datetime;

import oreo.exception.IllegalDateTimeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * Implements class that handles parsing of time input
 *
 * @author Daniel Loh
 * @version 03/09/2023
 */
public class TimeParser {
    public static LocalDate today = LocalDate.now();

    public static LocalTime now = LocalTime.now();

    /**
     * Parses date input for display
     *
     * @param input date input
     * @return output date
     * @throws IllegalDateTimeException if invalid format
     */
    private static String parseDateOut(String input) throws IllegalDateTimeException {
        String[] formatsWithYear = {"d-M-yyyy", "d-M-yy", "d/M/yyyy", "d/M/yy",
                "MMM d yyyy", "MMM d yy", "d MMM yyyy", "d MMM yy"
        };
        String[] formatsWithoutYear = {"dd/MM", "dd/M", "dd-M", "dd-MM", "MMM d", "d MMM"};

        for (String format : formatsWithYear) {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
                LocalDate date = LocalDate.parse(input, dtf);
                return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeException e) {
                // try other formats
            }
        }

        for (String format : formatsWithoutYear) {
            try {
                DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                        .appendPattern(format)
                        .parseDefaulting(ChronoField.YEAR, today.getYear())
                        .toFormatter(Locale.ENGLISH);
                LocalDate date = LocalDate.parse(input, dtf);
                if (date.isBefore(today)) {
                    date = date.plusYears(1);
                }
                return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeException e) {
                // try other formats
            }
        }
        // if failed all formats throw exception
        throw new IllegalDateTimeException("Date or date format is invalid");
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
        String[] formats = {
                "HHmm", "h a", "ha", "HH:mm", "h:mm a", "h:mma", "HH.mm", "h.mm a", "h.mma"
        };

        input = input.toUpperCase();

        for (String format : formats) {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
                LocalTime date = LocalTime.parse(input, dtf);
                return date.format(DateTimeFormatter.ofPattern("h:mm a"));
            } catch (DateTimeException e) {
                // try other formats
            }
        }

        // if failed all formats throw exception
        throw new IllegalDateTimeException("Time or time format is invalid");
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
            return time.format(DateTimeFormatter.ofPattern("HHmm"));
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
        String[] dateTime = input.split(", ");
        String exceptionMessage = "";
        boolean firstElementNotDate = false;

        try {
            out[0] = parseDateOut(dateTime[0]);
        } catch (IllegalDateTimeException e) {
            exceptionMessage += (e.getMessage() + "\n");
            firstElementNotDate = true;
        }
        if (firstElementNotDate) {
            try {
                out[1] = parseTimeOut(dateTime[0]);
            } catch (IllegalDateTimeException e) {
                exceptionMessage += (e.getMessage() + "\n");
            }
        } else {
            if (dateTime.length == 2) {
                out[1] = parseTimeOut(dateTime[1]);
            }
        }
        if (out[0] == null && out[1] == null) {
            throw new IllegalDateTimeException(exceptionMessage
                    + "Common date time format errors are missing commas between date and time "
                    + "or invalid date or time formats. An example of valid date time format:\n"
                    + "\"1 Jan, 5pm\"\n"
                    + "try \"help datetime\" to learn more about accepted date time formats");
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
            throw new IllegalDateTimeException("to date cannot be earlier than from date\n"
                    + toDate + " is before " + fromDate);
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
            throw new IllegalDateTimeException("sorry but to time cannot be before from time\n"
                    + toTime + " is before " + fromTime);
        }
    }

    public static String getTodayString() {
        return today.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
