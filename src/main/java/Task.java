import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is a class representing a single task with fields to represent details and done state
 * @author Selwyn
 */
public class Task {
    /**
     * Field representing details of the task
     */
    protected String detail;

    /**
     * Field representing whether the task is done or not
     */
    protected boolean isDone;

    /**
     * Constructor for a task
     * @param detail
     */
    public Task(String detail) {
        this.detail = detail;
        this.isDone = false;
    }

    public String getDisplayDateTime(LocalDateTime dateTime) {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dateTime.format(displayFormat);
    }

    /**
     * This method returns string representation of this task
     * @return String representation of this task
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
     * Mark the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as undone
     */
    public void markUndone() {
        this.isDone = false;
    }
}