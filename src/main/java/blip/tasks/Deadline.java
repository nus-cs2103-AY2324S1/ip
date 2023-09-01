package blip.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    /**
     * The date time of deadline.
     */
    protected LocalDateTime deadline;

    /**
     * Constructor of Deadline.
     * @param description The description of the deadline task
     * @param deadline The date time of deadline
     * @param isDone Boolean that represents whether task is done
     */
    public Deadline(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Saves the deadline task to data file in this specified format.
     * @return String representation of deadline task to save in data file
     */
    @Override
    public String saveToFileString(){
        return "D " + (super.isDone ? "| 1 | " : "| 0 | ") + super.toString() + " | "
                + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a String representation of the deadline task.
     * @return String representation of deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }
}
