package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * one of the Tasks that user's can add into their list
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * constructor to initialise a Deadline object
     * @param description the Task description that is obtained from the Task class
     * @param by the deadline time component that is stored as a LocalDateTime
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + localDatetoString(by) + ")";
    }
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + "| " + localDatetoString(by);
    }

    /**
     * A toString method to convert the LocalDateTime to a String
     * @param dateTime the stored LocalDateTime
     * @return a String
     */
    public static String localDatetoString(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        String formattedDateTime = dateTime.format(outputFormatter);
        return formattedDateTime;
    }
}
