import java.util.regex.Pattern;
import java.time.LocalDateTime;

public class DateTimeParser {
    private static final String DATETIME_REGEX = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) \\d{2}:\\d{2}";;

    public static boolean isValidDateTime(String datetime) {
        datetime = datetime.strip();
        return Pattern.matches(DateTimeParser.DATETIME_REGEX, datetime);
    }

    public static LocalDateTime parse(String datetime) throws DateTimeFormatException {
        if (DateTimeParser.isValidDateTime(datetime)) {
            datetime = datetime.strip();
            String[] arr = datetime.split(" ");
            String[] date = arr[0].split("-");
            String[] time = arr[1].split(":");
            return LocalDateTime.of(
                    Integer.parseInt(date[0]),
                    Integer.parseInt(date[1]),
                    Integer.parseInt(date[2]),
                    Integer.parseInt(time[0]),
                    Integer.parseInt(time[1]));
        }
        throw new DateTimeFormatException("Your date format sucks");
    }
}
