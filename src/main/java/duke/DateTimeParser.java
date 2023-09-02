package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is used to parse the date and time from the user input.
 */
public class DateTimeParser {
    // Note that we take "MMM-dd-yyyy HHmm a" and "MMM-d-yyyy HHmm a" as valid date and time formats because
    // we store the date and time in this format in the storage file. Therefore, in order to convert it back
    // to DateTime, we need to take these formats as valid.
    public static final String[] VALID_DATE_TIME_FORMAT = {"dd/MM/yyyy HHmm", "d/MM/yyyy HHmm",
        "MMM-dd-yyyy HHmm a", "MMM-d-yyyy HHmm a"};

    public static final String[] VALID_DATE_TIME_FORMAT_OUTPUT = {"MMM-dd-yyyy HHmm a", "MMM-d-yyyy HHmm a"};

    /**
     * Checks if the input is a valid date and time format
     * @param input the user's input
     * @return  true if the input is a valid date and time format, false otherwise
     */
    public static boolean isValidDateTime(String input) {
        for (String format : VALID_DATE_TIME_FORMAT) {
            try {
                LocalDateTime.parse(input, DateTimeFormatter.ofPattern(format));
                return true;
            } catch (Exception e) {
                // do nothing
            }
        }
        return false;
    }

    /**
     * Creates a LocalDateTime object based on the input
     * @param validDateTime a valid Date Time formatted String already checked by isValidDateTime
     * @return  a LocalDateTime object
     * @throws WrongInputException   if the input is not a valid date and time format
     */
    public static LocalDateTime createLocalDateTime(String validDateTime) throws WrongInputException {
        for (String format : VALID_DATE_TIME_FORMAT) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(validDateTime, DateTimeFormatter.ofPattern(format));
                return dateTime;
            } catch (Exception e) {
                // do nothing
            }
        }
        throw new WrongInputException("Invalid date and time format",
                "Please enter a valid date and time format");
    }

    /**
     *  Warns the user to follow a certain date and time format
     *  @return a String message to warn the user to follow a certain date and time format
     */
    public static String getValidDateTimeFormat() {
        String message = "";
        message += "Please enter a valid date and time format seen below\n";
        for (int i = 0; i < VALID_DATE_TIME_FORMAT.length; i++) {
            message += VALID_DATE_TIME_FORMAT[i] + "\n";
        }
        return message;
    }

}
