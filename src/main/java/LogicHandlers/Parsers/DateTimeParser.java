package LogicHandlers.Parsers;

import Exceptions.DukeInvalidDateTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static Printers.ErrorOutputPrinter.printErrorOutput;

/**
 * This class parses datetimes in String and returns them in DateTime formats.
 */
public class DateTimeParser {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy Hmm");
    private static DateTimeFormatter outputDateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
    public static LocalDateTime parseDateTimeFromString(String dateTimeString) throws DukeInvalidDateTimeException {
        try {
            return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException("Invalid date and time format! Please try again.");
        }
    }

    public static String parseStringFromDateTime(LocalDateTime dateTime) {
        return dateTime.format(dateTimeFormatter);
    }

    public static String parseDateTimeForOutput(LocalDateTime dateTime) {
        return dateTime.format(outputDateTimeFormatter);
    }

}
