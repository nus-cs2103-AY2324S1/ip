package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {
    public static LocalDateTime convertStringToDateTime(String datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            return LocalDateTime.parse(datetime, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            System.out.println("Invalid date format. Please enter in the format 01-01-2001 01:01");
        }
        return null;
    }


    public static String convertDateTimeToString(LocalDateTime datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return datetime.format(formatter);
    }
}
