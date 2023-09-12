package duke.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.EmptyFieldException;
import duke.exception.InvalidDatetimeFormatException;

/** A helper class containing methods to help parse and format string datetimes. */
public class DatetimeHelper {

    private static String inputFormat = "yyyy-MM-dd HHmm";
    private static String exampleDatetime = "2015-09-15 1300";

    /**
     * Returns the input format
     *
     * @return input format
     */
    public static String getInputFormat() {
        return inputFormat;
    }

    /**
     * Returns an example date time
     *
     * @return example date time following the input format
     */
    public static String getExampleDatetime() {
        return exampleDatetime;
    }

    /**
     * Returns a LocalDateTime from datetime argument
     *
     * @param datetime the string to convert to LocalDateTime
     * @return a LocalDateTime object
     * @throws DateTimeParseException when datetime is not the correct format
     */
    public static LocalDateTime parse(String datetime) {
        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(inputFormat));
    }

    /**
     * Returns a string representation of the LocalDateTime object for printing.
     *
     * @param datetime the LocalDateTime object to convert to string
     * @return a string representation of datetime to print
     */
    public static String format(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
    }

    /**
     * Returns a string representation of the LocalDateTime object in its input format.
     *
     * @param datetime the LocalDateTime object to convert to string
     * @return a string representation of datetime similar to the input format
     */
    public static String commandFormat(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern(inputFormat));
    }

    /**
     * Parses a string datetime.
     *
     * @param input the string datetime
     * @param fieldName the name of the field
     * @param object the name of the object
     * @return a LocalDateTime of the input
     * @throws EmptyFieldException when input == ""
     * @throws InvalidDatetimeFormatException when input is not a valid datetime format
     */
    public static LocalDateTime parseField(String input, String fieldName, String object) {
        if (input.isEmpty()) {
            throw new EmptyFieldException(object, fieldName);
        }
        try {
            return parse(input);
        } catch (DateTimeParseException e) {
            throw new InvalidDatetimeFormatException(fieldName, object);
        }

    }
}
