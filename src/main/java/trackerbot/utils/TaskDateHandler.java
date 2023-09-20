package trackerbot.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import trackerbot.exception.TrackerBotException;

/**
 * Handles Date-related methods for Task.
 * @author WZWren
 * @version A-Assertions
 */
public class TaskDateHandler {
    private static final String DATE_INPUT_FORMAT = "d/M[/yyyy][ HHmm]";
    private static final String DATE_UI_FORMAT = "dd MMM yyyy, EEE @ hh:mma";

    /** Prevent the instantiation of TaskDateHandler object. **/
    private TaskDateHandler() {}

    /**
     * Converts the Input String into a LocalDateTime object.
     *
     * @param input The input String to parse into a Date object.
     *              The String should be in the format: [d/M/yyyy HHmm]<br>
     *              The year and time are optional. If not given, the year is assumed
     *              to be the current year, and the time is assumed to be 0000.
     * @return The LocalDateTime object from the parsed String
     * @throws TrackerBotException if the input does not match the format string
     */
    public static LocalDateTime convertInputToDate(String input)
            throws TrackerBotException {
        try {
            DateTimeFormatter format = new DateTimeFormatterBuilder()
                    .append(DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT))
                    .parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDateTime.now().get(ChronoField.YEAR_OF_ERA))
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter();
            return LocalDateTime.parse(input, format);
        } catch (DateTimeParseException e) {
            throw new TrackerBotException("Error in parsing input to date: " + e.getMessage()
                    + "\nAdditional Date Fields should be in the format DD/MM(/YYYY)( HHmm)");
        }
    }

    /**
     * Converts the start date and end date inputs into a pair of LocalDateTime.
     * <p>The inputs should be in the format: [d/M/yyyy HHmm]</p>
     * <p>The year and time are optional. If not given, the year is assumed
     * to be the current year, and the time is assumed to be 0000.</p>
     *
     * @param fromStr The start date input String to parse into a Date object.
     * @param toStr The end date input String to parse into a Date object.
     * @return A LocalDateTime array of length 2, parsed from the input Strings
     * @throws TrackerBotException if the input does not match the format string, or
     *                             if the start date is not earlier than the end date.
     */
    public static LocalDateTime[] convertInputToDate(String fromStr, String toStr)
            throws TrackerBotException {
        LocalDateTime startDate = convertInputToDate(fromStr);
        LocalDateTime endDate = convertInputToDate(toStr);

        if (!(endDate.isAfter(startDate))) {
            throw new TrackerBotException("Start date should be before end date.");
        }

        return new LocalDateTime[] {startDate, endDate};
    }

    /**
     * Converts the Save String into a LocalDateTime object.
     *
     * @param input The input String to parse into a Date object.
     *              The String should be in epoch timestamp format.
     * @return The LocalDateTime object from the parsed String
     * @throws TrackerBotException if the input is not in valid epoch format, or
     *                             if the input is not parsable as a Long
     */
    public static LocalDateTime convertSaveToDate(String input) throws TrackerBotException {
        try {
            long epoch = Long.parseLong(input);
            return LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.UTC);
        } catch (NumberFormatException e) {
            throw new TrackerBotException("Cannot parse time field.");
        } catch (DateTimeParseException e) {
            throw new TrackerBotException("Time field is out of range.");
        }
    }

    /**
     * Converts the start date and end save date input into a pair of LocalDateTime.
     *
     * <p>The String should be in epoch timestamp format.</p>
     * @param fromStr The start date input String to parse into a Date object.
     * @param toStr The end date input String to parse into a Date object.
     * @return A LocalDateTime array of length 2, parsed from the input Strings
     * @throws TrackerBotException if the input does not match the format string, or
     *                             if the start date is not earlier than the end date.
     */
    public static LocalDateTime[] convertSaveToDate(String fromStr, String toStr)
            throws TrackerBotException {
        LocalDateTime startDate = convertSaveToDate(fromStr);
        LocalDateTime endDate = convertSaveToDate(toStr);

        if (!(endDate.isAfter(startDate))) {
            throw new TrackerBotException("Save date violates time constraints.");
        }

        return new LocalDateTime[] {startDate, endDate};
    }

    /**
     * Converts the LocalDateTime object into a UI-friendly String format.
     *
     * @param date The LocalDateTime to parse as a String.
     * @return The parsed String, in [dd MMM yyyy, EEE @ hh:mma] format.
     */
    public static String convertDateToUi(LocalDateTime date) {
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern(DATE_UI_FORMAT))
                .toFormatter();
        return date.format(format);
    }
}
