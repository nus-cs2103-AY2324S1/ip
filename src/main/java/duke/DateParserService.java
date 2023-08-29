package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateParserService {
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        date = date.strip();
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("[MM/dd/yyyy]" + "[dd-MM-yyyy]" + "[yyyy-MM-dd]"
                        + "[MMM dd yyyy]" + "[dd MMM yyyy]" + "[dd/MM/yyyy]" + "[yyyy/MM/dd]"));
        DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
