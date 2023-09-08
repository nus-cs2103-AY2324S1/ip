package avalon;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline, which is a type of task with a description, completion status, and a deadline timestamp.
 */
public class Deadline extends Task {

    /**
     * The deadline timestamp for the task.
     */
    protected LocalDateTime by;

    /**
     * Creates a new deadline task with the given description and deadline timestamp and sets its initial completion status to false.
     *
     * @param description The description of the deadline task.
     * @param by The deadline timestamp in string format (to be parsed into a LocalDateTime).
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = DateTimeParser.stringToDateTime(by);
    }

    /**
     * Returns a string representation of the deadline task, including its status icon, description, and deadline timestamp.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.printDateTimeToString(by) + ")";
    }
}
