package ally.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline Class inherits from Task.
 */
public class Deadline extends Task {
    protected LocalDateTime byDateTime;
    /**
     * Constructor for Deadline.
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("'by 'yyyy-MM-dd HHmm");
        byDateTime = LocalDateTime.parse(by, inputFormatter);

    }

    /**
     * Formats the tasks for the saved.txt
     * @return String
     */
    @Override
    public String formatFile() {
        return "D" + " | " + (isDone ? "1" : "0") + " | " + description + " "
                + byDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

    }

    /**
     * Overrides the toString() method in Ally.Tasks.Task.
     * @return the string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy ha")) + ")";
    }

    public LocalDateTime getByDateTime() {
        return byDateTime;
    }
}
