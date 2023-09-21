package duke.dates;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Execute any date related parsing task
 */
public class Dates {
    public static final DateTimeFormatter DATE_PRINTING_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
    static final Pattern DATEPATTERN = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])"
            + "/(\\d{4}) (\\d{4}$)");

    static final Pattern DATE_CHECKING_PATTERN = Pattern.compile("\\d{1,2} "
            + "(January|February|March|April|May|June|July|August|"
            + "September|October|November|December) \\d{4} \\d{2}:\\d{2}");
    static final DateTimeFormatter INPUT_TO_DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    static final DateTimeFormatter DATALIST_TO_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

    public static boolean checkDateinput(String date) {
        Matcher matcher = Dates.DATEPATTERN.matcher(date);
        return matcher.matches();
    }

    /**
     * Convert a date string from line into a LocalDateTime class
     *
     * @param date the string of a data in the format specified by input requirements
     * @return LocalDateTime class based on the date String
     */
    public static LocalDateTime convertToDateTime(String date) {

        return LocalDateTime.parse(date, INPUT_TO_DATE_FORMATTER);
    }
    /**
     * Check of the date String from the list matches the pattern
     *
     * @return True if the date String matches the pattern
     */
    public static boolean checkDateString(String date) {
        Matcher matcher = DATE_CHECKING_PATTERN.matcher(date);
        return matcher.matches();
    }

    /**
     * Convert a date string from data list into a LocalDateTime class
     *
     * @return LocalDateTime class based on the date String
     */
    public static LocalDateTime createDateTime(String date) {

        return LocalDateTime.parse(date, DATALIST_TO_DATE_FORMATTER);
    }





}
