package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, a subclass of Task.
 * A Deadline task has a description and a specific deadline date/time.
 *
 * <p>Each Deadline task can be stored or printed in specific formats as defined by its constants.</p>
 */
public class Deadline extends Task {

    /** Format to print a Deadline task to the user. */
    private static final String PRINT_FORMAT = "[D]%s %s (%s)";

    /** Format to store a Deadline task in storage. */
    private static final String STORE_FORMAT = "[D] | %s | %s | %s";

    /** The deadline date/time for this task. */
    private final LocalDateTime end;

    /**
     * Initializes a Deadline task with the provided description and deadline date/time.
     *
     * @param info The description of the Deadline task.
     * @param by The deadline date/time.
     */
    public Deadline(String info, LocalDateTime by) {
        super(info, TaskType.DEADLINE);
        this.end = by;
    }

    /**
     * Returns the deadline date/time of this task.
     *
     * @return The deadline date/time.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Returns the string representation of this Deadline task in a format suitable for storage.
     *
     * @return The storage-friendly string representation of this Deadline task.
     */
    @Override
    public String saveString() {
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return String.format(STORE_FORMAT, getFlag(), this.getDescription().trim(), end.format(dtFormat));
    }

    /**
     * Returns the string representation of this Deadline task.
     *
     * @return The string representation of this Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return String.format(PRINT_FORMAT, getFlag(), this.getDescription(), end.format(dtFormat));
    }
}
