import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TimeParser {
    public static String parseDateOut(String input) throws IllegalDateTimeException {
        String modifiedInput = input.replace("/", "-");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date;
        try {
            date = LocalDate.parse(modifiedInput, format);
        } catch (DateTimeException e) {
            throw new IllegalDateTimeException("Date or date format is invalid\n" +
                    "try dd/mm/yyyy format instead");
        }
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public static String parseDateForFile(String input) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate date = LocalDate.parse(input, format);
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static String parseTimeOut(String input) throws IllegalDateTimeException{
        LocalTime time;
        try {
            time = LocalTime.parse(input,
                    DateTimeFormatter.ofPattern("HHmm"));
        } catch (DateTimeException e) {
            throw new IllegalDateTimeException(("Time or time format is invalid\n" +
                    "try HHmm format instead"));
        }
        return time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    public static String parseTimeForFile(String input) {
        if (input == null) {
            return "";
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("h:mm a");
            LocalTime time = LocalTime.parse(input, format);
            return " " + time.format(DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    public static String[] parseInputOut(String input) throws IllegalDateTimeException {
        String[] out = new String[2];
        String[] dateTime = input.split("\\s+");
        out[0] = parseDateOut(dateTime[0]);
        if (dateTime.length == 2) {
            out[1] = parseTimeOut(dateTime[1]);
            return out;
        }
        return out;
    }

    public static void checkValidEventDate(String fromDate, String toDate) throws IllegalDateTimeException {
        LocalDate from = LocalDate.parse(fromDate,
                DateTimeFormatter.ofPattern("MMM d yyyy"));
        LocalDate to = LocalDate.parse(toDate,
                DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (to.isBefore(from)) {
            throw new IllegalDateTimeException("to cannot be earlier than from");
        }
    }
    public static void checkValidEventTime(String fromTime, String toTime) throws IllegalDateTimeException {
        LocalTime from = LocalTime.parse(fromTime,
                DateTimeFormatter.ofPattern("h:mm a"));
        LocalTime to = LocalTime.parse(toTime,
                DateTimeFormatter.ofPattern("h:mm a"));
        if (to.isBefore(from)) {
            throw new IllegalDateTimeException("end time cannot be before start time");
        }
    }
}
