import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
public class DateParser {
    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter INPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER =  DateTimeFormatter.ofPattern("MMM dd yyyy");
private static final DateTimeFormatter OUTPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private static final Pattern DATETIME_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{4}$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    public static String transformDateTimeFormat(String dateTimeStr) throws InvalidDateTimeException{
        if (DATETIME_PATTERN.matcher(dateTimeStr).matches()) {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, INPUT_DATETIME_FORMATTER);
            return dateTime.format(OUTPUT_DATETIME_FORMATTER);
        } else if (DATE_PATTERN.matcher(dateTimeStr).matches()) {
            LocalDate date = LocalDate.parse(dateTimeStr, INPUT_DATE_FORMATTER);
            return date.format(OUTPUT_DATE_FORMATTER);
        } else {
            throw new InvalidDateTimeException();
        }
    }

}
