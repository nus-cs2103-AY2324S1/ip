package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidInputException;
import duke.templates.MessageTemplates;


/**
 * Represents the DateTimeParser.
 */
public class DateTimeParser {
    private static final String inputFormat = "yyyy-MM-dd HHmm";
    private static final String outputFormat = "MMM dd yyyy h:mma";

    /**
     * Parses the date and time.
     * @param s Date and time.
     * @return Parsed date and time.
     * @throws InvalidInputException If date and time is invalid.
     */
    public static String parseDateTime(String s) throws InvalidInputException {
        DateTimeFormatter in = DateTimeFormatter.ofPattern(inputFormat);
        DateTimeFormatter out = DateTimeFormatter.ofPattern(outputFormat);
        try {
            return LocalDateTime.parse(s, in).format(out);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(MessageTemplates.MESSAGE_INVALID_DATETIME);
        }
    }

    /**
     * Checks if the period is valid.
     * @param from Start of period.
     * @param to End of period.
     * @return True if period is valid.
     */
    public static boolean isValidPeriod(String from, String to) {
        LocalDateTime before = LocalDateTime.parse(from, DateTimeFormatter.ofPattern(outputFormat));
        LocalDateTime after = LocalDateTime.parse(to, DateTimeFormatter.ofPattern(outputFormat));
        return before.isBefore(after);
    }
}
