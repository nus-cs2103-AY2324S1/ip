package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    /**
     * Parses a dateInput in the format dd/MM/yyyy into a LocalDateTime
     * We will standardise hhmmss as 000000
     * @param dateInput in the format dd/MM/yyyy into a LocalDateTime
     * @return corresponding LocalDateTime object
     */
    public static LocalDateTime parseDateInputIntoDateTime(String dateInput) {
        String[] dayMonthYear = dateInput.split("/");
        return LocalDateTime.parse(String.format("%s-%02d-%02dT00:00:00", dayMonthYear[2],
                Integer.valueOf(dayMonthYear[1]), Integer.valueOf(dayMonthYear[0])));
    }

    // TODO: abstract out the parser from user input datetime format to display format

    /**
     * Parses from MMM dd yyyy HHmm to yyyy-MM-dd HHmm
     * Reference: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/format/DateTimeFormatter.html
     * @param displayDateTime of format MMM dd yyyy h a
     * @return String in format yyyy-MM-dd HHmm
     */
    public static String parseDisplayDatetimeToStorageDatetime(String displayDateTime) {
        try {
            DateTimeFormatter displayDateTimeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy ha");
            LocalDateTime displayLocalDateTime = LocalDateTime.parse(displayDateTime, displayDateTimeFormat);
            DateTimeFormatter storageDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hhmm");
            return displayLocalDateTime.format(storageDateTimeFormat);
        } catch (DateTimeParseException e) {
            return displayDateTime;
        }

    }
}
