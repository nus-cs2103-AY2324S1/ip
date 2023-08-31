import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {
    private final static String dateFormat = "yyyy-MM-dd HHmm";

    public static LocalDateTime parse(String datetime) {
        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(dateFormat));
    }

    public static String format(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mma"));
    }

    public static String saveFormat(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern(dateFormat));
    }
}
