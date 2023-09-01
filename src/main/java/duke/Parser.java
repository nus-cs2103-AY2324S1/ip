package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static LocalDateTime formatDate(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime parseDate = LocalDateTime.parse(dateTimeString, formatter);
        return parseDate;
    }

    public static String dateToString(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        String formattedDate = dateTime.format(outputFormatter);
        return formattedDate;
    }

    public static String[] splitInput(String oriInput) {
        // split the input into at most 2 parts to get the task type and the task details
        return oriInput.split(" ", 2);
    }

    public static String[] splitDeadlineDetails(String oriInput) {
        // split the input to get the deadline task description and deadline time
        return oriInput.split("/by ", 2);
    }

    public static String[] splitEventDetails(String oriInput) {
        // split the input to get the event task description and the date time details
        return oriInput.split("/from ", 2);
    }

    public static String[] splitEventDateTime(String input) {
        // split the input again to get the start time of the event and the end time of the event
        return input.split(" /to ", 2);
    }
}