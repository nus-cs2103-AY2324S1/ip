import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TimeParser {
    private static String parseDate(String input) {
        LocalDate date = LocalDate.parse(input);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private static  String parseTime(String input) {
        LocalTime time = LocalTime.parse(input,
                DateTimeFormatter.ofPattern("HHmm"));
        return time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    public static String parseInput(String input) {
        Scanner sc = new Scanner(input);
        String formattedResult = parseDate(sc.next());
        if (sc.hasNext()) {
            String time = sc.next();
            String hour = time.substring(0, 2);
            String min = time.substring(2);
            String formattedTime = parseTime(hour + min);
            formattedResult += (" " + formattedTime);
        }
        return formattedResult;
    }
}
