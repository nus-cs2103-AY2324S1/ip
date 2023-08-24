import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DatetimeParser {
    /**
     * Formats the given date.
     * @param s Date to be formatted.
     * @return
     * @throws DateTimeParseException
     */
    private static String formatDate(String s) throws DateTimeParseException {
        return LocalDate.parse(s, DateTimeFormatter.ofPattern("d/M/yyyy")).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Formats the given time.
     * @param s Time to be formatted.
     * @return
     * @throws DateTimeParseException
     */
    private static String formatTime(String s) throws DateTimeParseException {
        return LocalTime.parse(s, DateTimeFormatter.ofPattern("HHmm")).format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    /**
     * Parses the given datetime.
     * @param arr An array of strings of datetime.
     * @return
     * @throws IllegalValueException
     */
    public static String parseDatetime(String[] arr) throws IllegalValueException {
        String datetime = "";
        try {
            datetime = formatDate(arr[0]);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException("Unable to parse date. Ensure it is of the form dd/mm/yyyy!");
        }
        if (arr.length > 1) {
            datetime += " ";
            try {
                datetime += formatTime(arr[1]);
            } catch (DateTimeParseException e) {
                throw new IllegalValueException("Unable to parse time. Ensure it is form HHmm!");
            }
        }
        return datetime;
    }
}
