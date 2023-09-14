package Eddie.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor for Deadline class.
     * @param name The description for the task.
     * @param deadline Date given in LocalDate format.
     */
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Prints out the type of Task.
     * @return The type of task.
     */
    public String getType() {
        return "D";
    }

    /**
     * Returns the deadline in terms of MMM d yyyy format to be printed
     * @return String of formatted date.
     */
    public String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Prints out the whole task along with the type, status, description and deadline for listing.
     * @return The string to be printed.
     */
    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatus() + "]" + this.getName() + " (by: " + this.getDeadline() + ")";
    }
}
