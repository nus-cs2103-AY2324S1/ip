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
 * Implements class that handles parsing of time input.
 *
 * @author Daniel Loh
 * @version 03/09/2023
 */
public class TimeParser {
    public static LocalDate today = LocalDate.now();

    public static LocalTime timeNow = LocalTime.now();

    /**
     * Parses date input for display.
     *
     * @param input date input.
     * @return output date.
     * @throws IllegalDateTimeException if invalid format.
     */
    private static String parseDateOut(String input) throws IllegalDateTimeException {
        String[] formatsWithYear = {"d-M-yyyy", "d-M-yy", "d/M/yyyy", "d/M/yy",
                "MMM d yyyy", "MMM d yy", "d MMM yyyy", "d MMM yy", "yyyy-M-d", "yy-M-d"
        };
        String[] formatsWithoutYear = {"d/M", "d-M", "MMM d", "d MMM"};

        if (input.contains(" ")) {
            input = formatDateWithWords(input);
        }

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
                return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeException e) {
                // try other formats
            }
        }
        // if failed all formats throw exception
        throw new IllegalDateTimeException("Date or date format is invalid");
    }

    /**
     * Formats date input with "MMM" format to the first letter to be upper case and
     * the rest, smaller case. For example, sep -> Sep to enable the parse to recognise the month.
     * This allows input to not case-sensitive.
     *
     * @param date date input from user.
     * @return date in corrected format (if there contains words).
     */
    public static String formatDateWithWords(String date) {
        StringBuilder result = new StringBuilder();

        // Split the input into words
        String[] words = date.split("\\s+");
        for (String word : words) {
            if (!word.isEmpty()) {
                // Capitalize the first letter and convert the rest to lowercase
                String capitalizedWord = Character.toUpperCase(word.charAt(0)) +
                        word.substring(1).toLowerCase();

                result.append(capitalizedWord).append(" ");
            }
        }
        return result.toString().trim(); // Remove trailing space
    }

    /**
     * Parses date for file data.
     *
     * @param input date as displayed.
     * @return output date for file data.
     * @throws IllegalDateTimeException if invalid format.
     */
    public static String parseDateForFile(String input) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate date = LocalDate.parse(input, format);
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Parses time input for display.
     *
     * @param input time input.
     * @return output time.
     * @throws IllegalDateTimeException if invalid format.
     */
    public static String parseTimeOut(String input) throws IllegalDateTimeException{
        String[] formatsWithoutEnglish = {
                "HHmm", "HH:mm", "HH.mm"
        };
        String[] formatsWithEnglish = {
                "h a", "ha", "h:mm a", "h:mma", "h.mm a", "h.mma"
        };

        for (String format : formatsWithoutEnglish) {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
                LocalTime date = LocalTime.parse(input, dtf);
                return date.format(DateTimeFormatter.ofPattern("h:mm a"));
            } catch (DateTimeException e) {
                // try other formats
            }
        }
        for (String format : formatsWithEnglish) {
            try {
                DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern(format)
                        .toFormatter(Locale.ENGLISH);
//                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
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
     * Parses time for file data.
     *
     * @param input time input.
     * @return output time.
     * @throws IllegalDateTimeException if invalid format.
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
     * Parses input for display.
     *
     * @param input date time input by user.
     * @return date time format for display.
     * @throws IllegalDateTimeException invalid format.
     */
    public static String[] parseInputOut(String input) throws IllegalDateTimeException {
        String[] out = new String[2];
        String[] dateTime = input.split(", ");
        String exceptionMessage = "";
        boolean firstElementNotDate = false;

        String missingComma = "If you are putting both date and time, you might have missed \", \" "
                + "between the date and the time\n (e.g. \"1 Jan, 5pm\").\n"
                + "Try \"help datetime\" to learn more about accepted date time formats";

        if (dateTime.length ==  2) {
            try {
                out[0] = parseDateOut(dateTime[0]);
            } catch (IllegalDateTimeException e) {
                exceptionMessage += (e.getMessage() + ". ");
            }
            try {
                out[1] = parseTimeOut(dateTime[1]);
            } catch (IllegalDateTimeException e) {
                exceptionMessage += (e.getMessage() + ". ");
            }
            if (exceptionMessage.isEmpty()) {
                return out;
            } else {
                throw new IllegalDateTimeException(exceptionMessage);
            }
        } else if (dateTime.length == 1){
            try {
                out[0] = parseDateOut(dateTime[0]);
            } catch (IllegalDateTimeException e) {
                exceptionMessage += (e.getMessage() + ". ");
                firstElementNotDate = true;
            }
            if (firstElementNotDate) {
                try {
                    out[1] = parseTimeOut(dateTime[0]);
                } catch (IllegalDateTimeException e) {
                    exceptionMessage += (e.getMessage() + ". ");
                }
            }
            if (out[0] == null && out[1] == null) {
                throw new IllegalDateTimeException(exceptionMessage += missingComma);
            } else {
                return out;
            }
        } else {
            throw new IllegalDateTimeException("Not sure what you did there but I couldn't understand "
                    + "your date time input");
        }
    }

    /**
     * Checks if to date is after from date.
     *
     * @param fromDate start date.
     * @param toDate end date.
     * @throws IllegalDateTimeException if end date is before start date.
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
     * Checks if to time is after from time.
     *
     * @param fromTime start time.
     * @param toTime end time.
     * @throws IllegalDateTimeException if end time is before start time in the same day.
     */
    public static void checkValidEventTime(String fromTime, String toTime) throws IllegalDateTimeException {
        LocalTime from = LocalTime.parse(fromTime,
                DateTimeFormatter.ofPattern("h:mm a"));
        LocalTime to = LocalTime.parse(toTime,
                DateTimeFormatter.ofPattern("h:mm a"));
        if (to.isBefore(from)) {
            throw new IllegalDateTimeException("Sorry but to time cannot be before from time\n"
                    + toTime + " is before " + fromTime);
        }
    }

    public static String getTodayString() {
        return today.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Gets the next occurrence of the date for a specified time.
     *
     * @param timeString specified time to check.
     * @return Date of next occurrence of time.
     */
    public static String getNextDateOfTime(String timeString) {
        LocalTime time = LocalTime.parse(timeString,
                DateTimeFormatter.ofPattern("h:mm a"));
        if (time.isBefore(timeNow)) {
            return today.plusDays(1).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return getTodayString();
    }

    /**
     * Gets the next occurrence of date considering both start and end time.
     * Used for the logic for event task.
     *
     * @param startTimeString From time from event.
     * @param endtimeString To time from event.
     * @param endDateString To date from event
     * @return Date of next occurrence.
     */
    public static String getNextDateOfTime(String startTimeString, String endtimeString,String endDateString) {
        if (endDateString == null) {
            return getNextDateOfTime(endtimeString);
        } else {
            return getNextDateOfTime(startTimeString);
        }
    }

    /**
     * Returns the date with year set to the next occurrence w.r.t the current date
     * Used for deadline task logic and event end time
     *
     * @param dateString
     * @return
     */
    public static String getNextDate(String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate date = LocalDate.parse(dateString, dtf);
        if (date.isBefore(today)) {
            return date.plusYears(1).format(dtf);
        } else {
            return date.format(dtf);
        }
    }

    /**
     * Used for when there is a startDate and endDate in an event, to consider valid durations.
     *
     * @param startDate start date of event
     * @param endDate end date of event
     * @return valid start date
     */
    public static String getNextValidDuration(String startDate, String endDate) {
        if (endDate == null) {
            return startDate;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate end = LocalDate.parse(endDate, dtf);
        LocalDate start = LocalDate.parse(startDate, dtf);
        if (end.isBefore(today)) {
            return start.plusYears(1).format(dtf);
        } else {
            return startDate;
        }
    }
}
