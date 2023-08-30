import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeParser {

    public static LocalDateTime parseTime(String input) {
        // formatting
        String formatPattern = "yyyy-MM-dd HHmm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        try {
            LocalDateTime parsedTime = LocalDateTime.parse(input, formatter);
            return parsedTime;
        } catch (DateTimeParseException e) {
            System.out.println("Date Time should be in yyyy-MM-dd HHmm format!\ndefault time used\n" + e.getMessage());
            return LocalDateTime.of(2000, 1, 1, 0, 0);
        }

    }


    public static String formatTime(LocalDateTime input) {
        // format time into a string object
        String year = String.valueOf(input.getYear());
        String month = input.getMonth().toString();
        String day = String.valueOf(input.getDayOfMonth());
        int hourAndTime = input.getHour() * 100 + input.getMinute();
        int  min = input.getMinute();
        return hourAndTime < 1000
                ? month + " " + day + " " + year + " 0" + (hourAndTime)
                : month + " " + day + " " + year + " " + (hourAndTime);

    }

    public static String getCmd(LocalDateTime savedTime) {
        int year = savedTime.getYear();
        String month = savedTime.getMonthValue() >= 10 ? String.valueOf(savedTime.getMonthValue())
                : "0" + savedTime.getMonthValue();
        int day = savedTime.getDayOfMonth();
        String hourAndTime = savedTime.getHour() >= 10 ? String.valueOf(savedTime.getHour() * 100 + savedTime.getMinute())
                : "0" + (savedTime.getHour() * 100 + savedTime.getMinute());
        return year + "-" + month + "-" + day + " " + hourAndTime;
    }

}