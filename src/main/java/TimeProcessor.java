import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;

public class TimeProcessor {
    private static DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern(
                    "[MM/dd/yyyy]" + "[dd-MM-yyyy]" + "[yyyy-MM-dd]" +
                    "[MM-dd-yyyy]" + "[dd/MM/yyyy]"+ "[yyyy/MM/dd]"));
    public static DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();
    public static final DateTimeFormatter DEFAULT_PATTERN = DateTimeFormatter.ofPattern("[dd-MM-yyyy]");
    public static LocalDate getLocalDateFromString(String str) {
        return LocalDate.parse(str, dateTimeFormatter);
    }

    public static String getStringFromLocalDate(LocalDate localDate) {
        return localDate.format(TimeProcessor.DEFAULT_PATTERN);
    }
}
