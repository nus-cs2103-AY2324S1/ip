package duke.task;

import java.time.LocalDateTime;

/**
 * Represents the deadline tasks and its methods.
 */
public class Deadline extends Task {
    /** Deadline due date. */
    protected LocalDateTime deadline;

    /**
     * Constructor for a deadline when the due date is represented in a String.
     * It then converts the string into a LocalDateTime.
     * 
     * @param description Description of the Deadline task.
     * @param by String representation of the deadline date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.deadline = this.convertToDateTime(by);
    }

    /**
     * Constructor for a deadline when the due date is in LocalDateTime type.
     * 
     * @param description Description of the Deadline task.
     * @param deadline Deadline due date.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the date.
     * 
     * @return Deadline date.
     */
    public String displayDeadline() {
        return this.displayTime(this.deadline);
    }

    /**
     * Returns a string representation of the date to be saved.
     * 
     * @return The saveable form of the date.
     */
    public String saveDeadline() {
        return this.saveTime(this.deadline);
    }

    @Override
    public String getOutputString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, this.saveDeadline());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.displayDeadline() + ")";
    }
}
