import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    public static String toLocalDateTimeString(String datetime) throws DateTimeParseException {
        DateTimeFormatter readFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime ld = LocalDateTime.parse(datetime, readFormatter);
        DateTimeFormatter writeFormatter = DateTimeFormatter.ofPattern("LLL dd yyyy HH:mm");
        return ld.format(writeFormatter);
    }

    public static LocalDateTime toLocalDateTimeFromWrongFormat(String datetime) throws DateTimeParseException {
        DateTimeFormatter readFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(datetime, readFormatter);
    }

    public static LocalDateTime toLocalDateTimeFromCorrectFormat(String datetime) throws DateTimeParseException {
        DateTimeFormatter readFormatter = DateTimeFormatter.ofPattern("LLL dd yyyy HH:mm");
        return LocalDateTime.parse(datetime, readFormatter);
    }

    public static String fromLocalDateTimeToString(LocalDateTime ldt) {
        DateTimeFormatter writeFormatter = DateTimeFormatter.ofPattern("LLL dd yyyy HH:mm");
        return ldt.format(writeFormatter);
    }
}
