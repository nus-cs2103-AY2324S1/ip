import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {
    public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static LocalDate convertToLocalDate(String date) {
        return LocalDate.parse(date, format);
    }
    public static String convertDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public static boolean isValidFormat(String date) {
        try {
            System.out.println("here");
            LocalDate.parse(date, format);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
