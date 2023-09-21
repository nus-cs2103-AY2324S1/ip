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
        String formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));

        return "[D]" + super.toString() + " (by: "
                + formattedBy + ") " + tagsToString();
    }

    /**
     * Converts to string to save to file.
     *
     * @return The string to be saved.
     */
    @Override
    public String printToFile() {
        String formattedBy = this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        return "D|" + super.printToFile() + "|"
                + formattedBy + "|" + tagsToString();
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
