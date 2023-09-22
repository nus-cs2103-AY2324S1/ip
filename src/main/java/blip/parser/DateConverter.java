package blip.parser;

import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import blip.exceptions.DateTimeFormatException;

/**
 * Represents the parser that converts date time string into DateTime based on format.
 */
public class DateConverter {
    private static String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";

    public static boolean isCorrectFormat(String dateTimeString) {
        assert dateTimeString != null : "dateTimeString input cannot be null";
        Pattern pattern = Pattern.compile(DATE_REGEX);
        Matcher matcher = pattern.matcher(dateTimeString);
        return matcher.matches();
    }

    public static LocalDateTime convertToDateTime(String dateTimeString) throws DateTimeFormatException {
        assert dateTimeString != null : "dateTimeString input cannot be null";

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
        throw new DateTimeFormatException("Error with date time format: ");
    }

}
