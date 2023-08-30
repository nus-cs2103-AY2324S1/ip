package duke.dates;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Execute any date related parsing task
 */
public class Dates {

    static final Pattern DATEPATTERN = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])"
            + "/(\\d{4}) (\\d{4}$)");


    public static boolean checkDateinput(String date) {
        Matcher matcher = Dates.DATEPATTERN.matcher(date);
        return matcher.matches();
    }

    /**
     * Convert a date string from line into a LocalDateTime class
     *
     * @return LocalDateTime class based on the date String
     */
    public static LocalDateTime convertToDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return dateTime;
    }
    /**
     * Check of the date String from the list matches the pattern
     *
     * @return True if the date String matches the pattern
     */
    public static boolean checkDateString(String date) {
        String pattern = "\\d{1,2} (January|February|March|April|May|June|July|August|"
                + "September|October|November|December) \\d{4} \\d{2}:\\d{2}";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(date);
        return matcher.matches();
    }

    /**
     * Convert a date string from data list into a LocalDateTime class
     *
     * @return LocalDateTime class based on the date String
     */
    public static LocalDateTime createDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return dateTime;
    }





}
