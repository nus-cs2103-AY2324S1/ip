package jarvis.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline Class.
 *
 * @author Shishir
 **/
public class Deadline extends Task {

    /** Deadline of the task. */
    private LocalDateTime deadline;

    /**
     * Constructs the deadline class.
     * @param description Description of the task.
     * @param deadline Deadline of the task.
     * @param status Status of completion.
     */
    public Deadline(String description, LocalDateTime deadline, boolean status) {
        super(description, status);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of the deadline task.
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "\u23F0 " + super.toString()
                + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")\n";
    }

    /**
     * Returns the string representation of the deadline task in file format.
     * @return String representation of the deadline task in file format.
     */
    @Override
    public String toFile() {
        return "D" + super.toFile() + " | " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    /**
     * Checks whether the deadline has passed.
     * @return True if the deadline has passed, false otherwise.
     */
    public boolean hasPassed() {
        return this.deadline.isBefore(LocalDateTime.now()) && !this.isCompleted();
    }
}
