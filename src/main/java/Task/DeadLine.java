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
    protected String byDescription;

    /**
     * Constructs a new `DeadLine` task with the specified description and deadline.
     *
     * @param description   The description of the `DeadLine` task.
     * @param byDescription The deadline represented as a string.
     */
    public DeadLine(String description, String byDescription) {
        super(description);
        this.byDescription = byDescription;
        this.byDateTime = parseDateTime(byDescription);
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
            return getTask() + getStatusIcon() + " " + description + " (by: " + byDescription + ")";
        }
    }
}
