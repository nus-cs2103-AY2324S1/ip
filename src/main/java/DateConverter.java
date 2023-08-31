import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DateConverter {
    private static String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";

    public static boolean isCorrectFormat(String dateTimeString) {
        Pattern pattern = Pattern.compile(DATE_REGEX);
        Matcher matcher = pattern.matcher(dateTimeString);
        return matcher.matches();
    }

    public static LocalDateTime convertToDateTime(String dateTimeString) throws DateTimeFormatException {
        if (isCorrectFormat(dateTimeString)) {
            dateTimeString = dateTimeString.trim();
            String[] dateTimeParts = dateTimeString.split(" ");
            String[] dateParts = dateTimeParts[0].split("-");
            String[] timeParts = dateTimeParts[1].split(":");
            LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(dateParts[0]),
                    Integer.parseInt(dateParts[1]),
                    Integer.parseInt(dateParts[2]),
                    Integer.parseInt(timeParts[0]),
                    Integer.parseInt(timeParts[1]));
            return localDateTime;
        }
        throw new DateTimeFormatException("Please format your date time to be yyyy-mm-dd HH:mm");
    }

}
