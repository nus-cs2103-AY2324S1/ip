import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Task class represents a task with a description and completion status.
 *
 * @author selwyn
 */
public class Task {
    /** The description of the task. */
    private String detail;

    /** The completion status of the task. */
    private boolean isDone;

    /**
     * Constructs a Task object with the specified description and sets its completion status to false.
     *
     * @param detail The description of the task.
     */
    public Task(String detail) {
        this.detail = detail;
        this.isDone = false;
    }

    /**
     * Returns a formatted display string of the provided LocalDateTime object.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return A formatted date and time string.
     */
    public String getDisplayDateTime(LocalDateTime dateTime) {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dateTime.format(displayFormat);
    }

    /**
     * Returns a string representation of the Task object, including its completion status and description.
     *
     * @return A string representation of the Task object.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.detail;
        } else {
            return "[-] " + this.detail;
        }
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone by setting its completion status to false.
     */
    public void markUndone() {
        this.isDone = false;
    }
}