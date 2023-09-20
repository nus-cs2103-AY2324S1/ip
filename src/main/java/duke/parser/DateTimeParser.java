package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.KoraException;

/**
 * Parser class for parsing DateTime variables.
 */
public class DateTimeParser {
    private DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm");
    private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public DateTimeParser() {
    }

    public String outFormatted(String dateTime) throws KoraException {
        try {
            return LocalDateTime.parse(dateTime, saveFormatter).format(outFormatter);
        } catch (DateTimeParseException e) {
            throw new KoraException("Omo! The date format should be yyyy-MM-dd HH-mm!");
        }
    }

    public String saveFormatted(String dateTime) throws KoraException {
        try {
            return LocalDateTime.parse(dateTime, saveFormatter).format(saveFormatter);
        } catch (DateTimeParseException e) {
            throw new KoraException("Omo! The date format should be yyyy-MM-dd HH-mm!");
        }
    }
}
