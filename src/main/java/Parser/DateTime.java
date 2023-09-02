package Parser;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The `DateTime` class provides methods for parsing and formatting date and time strings.
 */
public class DateTime {
    private DateTimeFormatter inputFormatter; //The input format of the date and time.
    private DateTimeFormatter outputFormatter; //The output format of the date and time.

    /**
     * Constructs a `DateTime` object with a date and time formatters.
     */
    public DateTime() {
        this.inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");
    }

    /**
     * Formats a date and time string from the input format to the output format.
     *
     * @param dateTimeStr The date and time string to be formatted.
     * @return The formatted date and time string in the output format or "Invalid format" if parsing fails.
     */
    public String formatDateTime(String dateTimeStr) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, inputFormatter);
            return dateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            return "Invalid format";
        }
    }

}
