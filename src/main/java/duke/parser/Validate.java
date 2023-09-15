package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.error.DukeException;

/**
 * Provides validation methods for date-time and number formats.
 */
public class Validate {

    /**
     * Validates and converts a string to a LocalDateTime object.
     *
     * @param input The string to be validated and converted.
     * @return LocalDateTime object parsed from the input string.
     * @throws DukeException If the input has an invalid date-time format.
     */
    public static LocalDateTime validateLocalDateTime(String input) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException error) {
            throw new DukeException("Invalid date format. <yyyy-MM-dd HH:mm> expected");
        }
    }

    /**
     * Validates and converts a string to an integer.
     *
     * @param input The string to be validated and converted.
     * @return Integer value parsed from the input string.
     * @throws DukeException If the input is not a valid number format.
     */
    public static int validateNumber(String input) throws DukeException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid number format");
        }
    }
}
