package duke.task;

import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class that inherits from Task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor with description and by.
     * @param description the description of the deadline being added.
     * @param by the deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Constructor with description, by and whether it has been marked as done.
     * @param description the description of the deadline being added.
     * @param by the deadline of the task.
     * @param isDone whether it has been marked as done.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the toString representation of a deadline task.
     * @return the String representation of the task.
     */

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy ha")) + ")";
    }

    /**
     * Returns the String representation of the task that will be written to the
     * text file.
     * @return The String representation of the task for the text file.
     */
    @Override
    public String toWriteString() {
        return "D | " + (isDone ? "X" : "0") + " | " + description + " | "
                +  by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

}