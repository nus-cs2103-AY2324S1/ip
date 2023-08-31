import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateManager {
    private final static String DATE_FORMAT = "dd-MM-yyyy";
    private final static String TIME_FORMAT = "HHmm";
    private final static String DATETIME_OUTPUT_FORMAT = "d MMMM yyyy h:mma";
    private final static String DATETIME_STORAGE_FORMAT = "ddMMyyyy HHmm";
    private final static String STORAGE_DATE_FORMAT = "ddMMyyyy";

    // Parse the date and time strings into a dateTime object
    public static LocalDateTime parseDateString(String dateString, String timeString) {
        LocalDate date = null;
        LocalTime time = null;
        LocalDateTime dateTime =  null;

        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_FORMAT));
            time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern(TIME_FORMAT));
            dateTime = LocalDateTime.of(date, time);
        } catch (DateTimeParseException e) {
            System.out.println("Error in parsing date string: " + e);
        }
        return dateTime;
    }

    // Parse the date and time strings from storage into a dateTime object
    public static LocalDateTime parseStorageDateString(String dateString, String timeString) {
        LocalDate date = null;
        LocalTime time = null;
        LocalDateTime dateTime =  null;

        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(STORAGE_DATE_FORMAT));
            time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern(TIME_FORMAT));
            dateTime = LocalDateTime.of(date, time);
        } catch (DateTimeParseException e) {
            System.out.println("Error in parsing date string: " + e);
        }
        return dateTime;
    }

    // Return String representation of a dateTime in the chatbot
    public static String dateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DATETIME_OUTPUT_FORMAT));
    }

    // Return String representation of a dateTime in the storage
    public static String dateTimeToStringStorage(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DATETIME_STORAGE_FORMAT));
    }

}
