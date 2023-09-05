package duke.task;

import duke.utility.DateParser;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a Deadline object.
     *
     * @param name The name or description of the task.
     * @param by   The deadline of the task.
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = DateParser.convertStringToDateTime(by);
    }

    /**
     * Converts the Deadline object to its string representation.
     *
     * @return The string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateParser.convertDateTimeToString(this.by) + ")";
    }

    /**
     * Converts the Deadline object to a formatted string for storage.
     *
     * @return The formatted string for storage.
     */
    public String convertTaskToStorageFormat() {
        return "D | " + (super.getIsDone() ? "1" : "0") + " | " + super.getName() + " | "
                + DateParser.convertDateTimeToString(this.by);
    }
}
