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

    /**
     * Parses a String into a LocalDateTime format for storage.
     *
     * @param dateTimeString The string.
     * @return The string parsed into a LocalDateTime.
     * @throws DukeInvalidDateTimeException In the event where the String is in the incorrect format.
     */
    public static LocalDateTime parseDateTimeFromString(String dateTimeString) throws DukeInvalidDateTimeException {
        try {
            return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException("Invalid date and time format! Please try again.");
        }
    }

    /**
     * Parses a LocalDateTime into a String format for storage in memory.txt.
     *
     * @param dateTime The LocalDateTime.
     * @return A String based on the LocalDateTime.
     */
    public static String parseStringFromDateTime(LocalDateTime dateTime) {
        return dateTime.format(dateTimeFormatter);
    }

    /**
     * Parses a LocalDateTime into a String format for outputting.
     *
     * @param dateTime The LocalDateTime.
     * @return A String based on the LocalDateTime.
     */
    public static String parseDateTimeForOutput(LocalDateTime dateTime) {
        return dateTime.format(outputDateTimeFormatter);
    }

}
