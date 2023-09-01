package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an extension of a task, containing a deadline in terms of LocalDate.
 */
public class Deadline extends Task {
    private LocalDate deadline;

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
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
    public String fileFormat() {
        return "DL" + DIVIDER + super.fileFormat() + DIVIDER + deadline;
    }
}
