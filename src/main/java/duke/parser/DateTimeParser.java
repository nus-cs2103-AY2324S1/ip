package duke.parser;

import duke.exception.InvalidInputException;
import duke.templates.MessageTemplates;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private static final String inputFormat = "yyyy-MM-dd HHmm";
    private static final String outputFormat = "MMM dd yyyy ha";
    public static String parseDateTime(String s) throws InvalidInputException {
        DateTimeFormatter in = DateTimeFormatter.ofPattern(inputFormat);
        DateTimeFormatter out = DateTimeFormatter.ofPattern(outputFormat);
        try {
            return LocalDateTime.parse(s, in).format(out);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(MessageTemplates.MESSAGE_PARSE_FAIL);
        }
    }
    public static boolean isValidPeriod(String from, String to) {
        LocalDateTime before = LocalDateTime.parse(from, DateTimeFormatter.ofPattern(outputFormat));
        LocalDateTime after = LocalDateTime.parse(to, DateTimeFormatter.ofPattern(outputFormat));
        return before.isBefore(after);
    }
}
