import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static LocalDateTime parseDateTime(String userInput) throws DateTimeParseException {
            return LocalDateTime.parse(userInput, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public static String dateTimeFormatter(LocalDateTime userInput) {
        return userInput.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }
}
