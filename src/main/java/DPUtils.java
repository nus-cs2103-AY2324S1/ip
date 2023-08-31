import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DPUtils {
    public static String dPFormatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    public static String saveFormatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    public static LocalDateTime dPTryParseDateTime(String dateTime) throws DateTimeParseException {
        //TODO: add different formats
        return LocalDateTime.parse(dateTime+ "T00:00:00");
    }
}
