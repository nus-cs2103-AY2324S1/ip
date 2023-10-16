package task;
import java.time.LocalDateTime;

/**
 * The `DeadLine` class represents a deadline task in the BloopBot application.
 * It extends the base `Task` class and inherits its properties and methods.
 * A `DeadLine` task includes a description and a deadline represented by a "by" string.
 * It can be marked as completed or not completed.
 * This class provides methods to create and format `DeadLine` tasks.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class DeadLine extends Task {
    protected LocalDateTime byDateTime;
    protected String by;

    /**
     * Constructs a new `DeadLine` task with the specified description and deadline.
     *
     * @param description   The description of the `DeadLine` task.
     * @param by The deadline represented as a string.
     */
    public DeadLine(String description, String by) {
        super(description);
        this.by = by;
        this.byDateTime = parseDateTime(by);
    }

    public String getBy() {
        return this.by;
    }
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the `DeadLine` task.
     * The representation includes the task's type icon, status icon, description,
     * and the deadline in a human-readable format.
     *
     * @return The string representation of the `DeadLine` task.
     */
    @Override
    public String toString() {
        if (byDateTime != null) {
            return getTask() + getStatusIcon() + " " + description + " (by: "
                    + super.printDateTime(this.byDateTime) + ")";
        } else {
            return getTask() + getStatusIcon() + " " + description + " (by: " + by + ")";
        }
    }
}
