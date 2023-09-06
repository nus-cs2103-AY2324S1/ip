package avalon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    public static LocalDateTime stringToDateTime(String inputDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try{
            return LocalDateTime.parse(inputDateTime, formatter);
        } catch (DateTimeParseException e){
            System.out.println("Invalid date format. Please follow the format: YYYY-MM-DD hhmm");
        }
        return null;
    }

    public static String dateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String formattedDateTimeString = dateTime.format(formatter);
        return formattedDateTimeString;
    }

    public static String printDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String formattedDateTimeString = dateTime.format(formatter);
        return formattedDateTimeString;
    }
}
