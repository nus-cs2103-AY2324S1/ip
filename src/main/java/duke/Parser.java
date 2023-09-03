package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Parses the input date from String to LocalDateTime format
     *
     * @param dateTimeString the date and time details in String
     * @return the date and time in LocalDateTime format
     */
    public static LocalDateTime formatDate(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime parseDate = LocalDateTime.parse(dateTimeString, formatter);
        return parseDate;
    }

    /**
     * Parses the input date from LocalDateTime format to String
     *
     * @param dateTime the date and time details in LocalDateTime format
     * @return the date and time in String format
     */
    public static String dateToString(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        String formattedDate = dateTime.format(outputFormatter);
        return formattedDate;
    }

    /**
     * Split the user input into 2 parts, the keyword and the other details, including task description
     *
     * @param oriInput the original input by user
     * @return a String array with the keyword at index 0 and the other details at index 1
     */
    public static String[] splitInput(String oriInput) {
        // split the input into at most 2 parts to get the task type and the task details
        return oriInput.split(" ", 2);
    }

    /**
     * split the input into 2 parts, the task description and the deadline of the task
     *
     * @param oriInput the input which includes the task description and the deadline of the task
     * @return a String[] with the task description at index 0 and deadline at index 1
     */
    public static String[] splitDeadlineDetails(String oriInput) {
        // split the input to get the deadline task description and deadline time
        return oriInput.split("/by ", 2);
    }

    /**
     * split the input into 2 parts, the event task description and the start and end details of the event
     *
     * @param oriInput the input which includes the task description and the start and end details
     * @return a String[] with the event task description at index 0 and the start and end details at index 1
     */
    public static String[] splitEventDetails(String oriInput) {
        // split the input to get the event task description and the date time details
        return oriInput.split("/from ", 2);
    }

    /**
     * split the start and end details into 2 parts
     *
     * @param input the input which is a String with the start and end details of the event task
     * @return a String[] with the start detail at index 0 and the end detail at index 1
     */
    public static String[] splitEventDateTime(String input) {
        // split the input again to get the start time of the event and the end time of the event
        return input.split(" /to ", 2);
    }
}