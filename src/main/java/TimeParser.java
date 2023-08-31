import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TimeParser {
    public static String parseDateOut(String input) {
        LocalDate date = LocalDate.parse(input);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public static String parseDateForFile(String input) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate date = LocalDate.parse(input, format);
        return date.toString();
    }

    public static String parseTimeOut(String input) {
        LocalTime time = LocalTime.parse(input,
                DateTimeFormatter.ofPattern("HHmm"));
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

    public static String[] parseInputOut(String input) {
        String[] out = new String[2];
        String[] dateTime = input.split("\\s+");
        out[0] = parseDateOut(dateTime[0]);
        if (dateTime.length == 2) {
            out[1] = parseTimeOut(dateTime[1]);
        }
        return out;
    }
}
