package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The base abstract class for different types of commands.
 */
public abstract class Command {

    /**
     * Executes the command given.
     *
     */
    public String execute() {
        return null;
    };

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTimeString the date and time string to parse.
     * @return the parsed LocalDateTime object, or null if the string is in an invalid format.
     */
    public LocalDateTime parseDateTime(String dateTimeString) {
        String[] possibleFormats = {"yyyy-MM-dd HHmm", "yyyy/MM/dd HHmm", "dd-MM-yyyy HHmm", "dd/MM/yyyy HHmm",
            "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm", "dd-MM-yyyy HH:mm", "dd/MM/yyyy HH:mm", "MMM dd yyyy HH:mm"};
        LocalDateTime dateTime = null;
        for (String format : possibleFormats) {
            try {
                dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(format));
                return dateTime;
            } catch (DateTimeParseException e) {
                // do nothing, try the next format.
            }
        }
        System.out.println("DateTime in an invalid format. Please enter datetime in the following format: \n"
                + "YYYY/MM/DD HH:MM");
        return null;
    }

}
