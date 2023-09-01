package noac.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task which contains an addition deadline compared to task.
 */
public class Deadline extends Task {


    protected LocalDateTime by;

    /**
     * Constructor for the Deadline.
     *
     * @param description Description on what the task is.
     * @param by When the task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the task to string.
     *
     * @return The string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    /**
     * Converts to string to save to file.
     *
     * @return The string to be saved.
     */
    @Override
    public String printToFile() {
        return "D|" + super.printToFile() + "|" +  this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Getter function for the deadline.
     *
     * @return the LocalDateTime when the task is due.
     */
    public LocalDateTime getBy() {
        return by;
    }

}
