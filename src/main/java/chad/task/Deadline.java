package chad.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a due date.
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Constructs a new Deadline instance.
     * @param description the description of the task.
     * @param dueDate the due date for the task.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns a string representation of the deadline task.
     * @return the string format of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return String.format("[D][%s] %s (by: %s)", super.getStatusIcon(), description, dueDate.format(formatter));
    }

    /**
     * Returns the deadline task in a format suitable for file storage.
     * @param formatter the DateTimeFormatter to use for the due date.
     * @return the string format of the deadline task for file storage.
     */
    @Override
    public String toFileFormat(DateTimeFormatter formatter) {
        return String.format("D | %s | %s | %s", super.isDoneString(), description, dueDate.format(formatter));
    }

    /**
     * Gets the due date of the deadline task.
     * @return the due date as a LocalDateTime object.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }
}
