package duke.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;

/**
 * Custom class to handle date and time
 */
public class DateAndTimeHandler {

    /**
     * Converts the given date to a specific format
     * @param date given date
     * @param inputPattern given input pattern
     * @param outputPattern expected output pattern
     * @return
     */
    public static String convertDateToFormat(String date, String inputPattern, String outputPattern) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputPattern);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputPattern);
        LocalDate localDate = LocalDate.parse(date, inputFormatter);
        return localDate.format(outputFormatter);
    }
    /**
     * Throws exception if the date is in valid format
     * @param date date as a string
     * @throws DukeException
     */
    public static void checkIfDateIsValid(String date) throws DukeException {
        try {
            LocalDate.parse(date);
        } catch (Exception e) {
            throw new DukeException("SUI, Invalid date type! Specify date in yyyy-mm-dd format");
        }
    }

    /**
     * Throws exception if the end date and end time is valid
     * @param byDate the end date for the deadline event
     * @param endTime the end time for the deadline event
     * @throws DukeException
     */
    public static void checkIfDeadlineTimelineIsValid(String byDate, String endTime) throws DukeException {
        LocalDate todayDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        if (todayDate.toString().compareTo(byDate) > 0
                || (todayDate.toString().compareTo(byDate) == 0 && nowTime.toString().compareTo(endTime) > 0)) {
            throw new DukeException("SUI, Enter a date and time from now");
        }
    }
}
