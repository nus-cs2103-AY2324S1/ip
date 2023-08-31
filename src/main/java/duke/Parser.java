package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static LocalDateTime formatDate(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime parseDate = LocalDateTime.parse(dateTimeString, formatter);
        return parseDate;
    }

    public static String dateToString(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        String formattedDate = dateTime.format(outputFormatter);
        return formattedDate;
    }
}
