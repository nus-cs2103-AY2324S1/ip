package duke.parser;

import duke.error.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validate {
    public static LocalDateTime validateLocalDateTime(String input) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException error) {
            throw new DukeException("Invalid date format. <yyyy-MM-dd HH:mm> expected");
        }
    }

    public static int validateNumber(String input) throws DukeException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid number format");
        }
    }
}
