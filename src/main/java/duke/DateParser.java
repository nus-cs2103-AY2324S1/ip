package duke;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class DateParser {

    private static final Pattern DATE_PATTERN_DASH = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Pattern DATE_PATTERN_SLASH = Pattern.compile("^\\d{1,2}/\\d{1,2}/\\d{4} .*\\d{4}$");

    private static boolean isDashedDate(String date) {
        return DATE_PATTERN_DASH.matcher(date).matches();
    }

    private static boolean isSlashedDate(String date) {
        return DATE_PATTERN_SLASH.matcher(date).matches();
    }

    public static boolean isEitherDate(String date) {
        return isDashedDate(date) || isSlashedDate(date);
    }

    private static LocalDate formatSlashedDate(String date) {
        String[] dateParts = date.split("/");
        int day = dateParts[0].length() == 1 ? Integer.valueOf("0" + dateParts[0]) : Integer.valueOf(dateParts[0]);
        int month = dateParts[1].length() == 1 ? Integer.valueOf("0" + dateParts[1]) : Integer.valueOf(dateParts[1]);
        String[] yearParts = dateParts[2].split(" ");

        return LocalDate.of(Integer.valueOf(yearParts[0]), month, day);
    }

    private static LocalDate formatDashedDate(String date) {
        String[] dateParts = date.split("-");
        return LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]),
                Integer.parseInt(dateParts[2]));
    }

    public static LocalDate formatDate(String date) {
        if (isDashedDate(date)) {
            return formatDashedDate(date);
        } else {
            return formatSlashedDate(date);
        }
    }


}
