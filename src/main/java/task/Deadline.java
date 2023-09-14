package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an extension of a task, containing a deadline in terms of LocalDate.
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * This is the constructor for a deadline.
     * @param name name of the task.
     * @param deadline date at which the task is due.
     */
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * An overloading of the above method, for when the isComplete is needed as well.
     * @param name name of the task.
     * @param isComplete whether the task is complete.
     * @param deadline date at which the task is due.
     */
    public Deadline(String name, boolean isComplete, LocalDate deadline) {
        super(name, isComplete);
        this.deadline = deadline;
    }

    /**
     * Creates a readable string interpretation of the Deadline.
     * @return a readable Deadline in String form.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Produces a savable format of the Deadline.
     * (We use dividers to minimize the confusion caused by spaces in names.)
     * @return a String format of Deadline with minimal ambiguity in format.
     */
    @Override
    public String fileFormat() {
        return "DL" + DIVIDER + super.fileFormat() + DIVIDER + deadline;
    }
}
