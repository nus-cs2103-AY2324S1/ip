package chad.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo task.
     * @param description the description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     * @return the string format of the ToDo task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", super.getStatusIcon(), description);
    }

    /**
     * Returns the ToDo task in a format suitable for file storage.
     * @param formatter the DateTimeFormatter to use for date and time representation.
     * @return the string format of the ToDo task for file storage.
     */
    @Override
    public String toFileFormat(DateTimeFormatter formatter) {
        return String.format("T | %s | %s", super.isDoneString(), description);
    }
}
