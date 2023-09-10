package commands;

import functions.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public abstract class Command {

    /**
     * Executes the command given.
     *
     */
    public void execute() {};

    public LocalDateTime parseDateTime(String dateTimeString) {
        String[] possibleFormats = {"yyyy-MM-dd HHmm", "yyyy/MM/dd HHmm","dd-MM-yyyy HHmm","dd/MM/yyyy HHmm",
                "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm","dd-MM-yyyy HH:mm","dd/MM/yyyy HH:mm"};
        LocalDateTime dateTime = null;
        for (String format : possibleFormats) {
            try {
                dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(format));
                return dateTime;
            } catch (DateTimeParseException e) {
                // do nothing, try the next format
            }
        }
        System.out.println("DateTime in an invalid format. Please enter datetime in the following format: \n" +
                "YYYY/MM/DD HH:MM");
        return null;
    }

}
