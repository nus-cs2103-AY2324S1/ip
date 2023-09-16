package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeIllegalArgumentException;

/**
 * A Task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    // Error messages
    private static final String ERROR_MESSAGE_INVALID_DEADLINE_FORMAT =
            "The deadline of a Deadline task must follow the format yyyy-MM-dd HH:mm.";

    // Template Strings
    private static final String DEADLINE_DISPLAY_TEMPLATE = "[D]%s (by: %s)";
    private static final String DEADLINE_EXPORT_TEMPLATE = "DEADLINE || %s || %s || %s || %s";


    // The deadline of the Deadline task, stored as a LocalDateTime Object.
    protected LocalDateTime by;

    /**
     * Constructor for a Deadline task.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline of the Deadline task. Must follow the format yyyy-MM-dd HH:mm.
     * @throws DukeIllegalArgumentException If the deadline does not follow the format yyyy-MM-dd HH:mm.
     */
    public Deadline(String description, String by) throws DukeIllegalArgumentException {
        super(description);
        this.updateDeadline(by);
    }

    /**
     * Returns the String representation of the Deadline task.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return String.format(DEADLINE_DISPLAY_TEMPLATE, super.toString(), this.by.format(printFormatter));
    }

    /**
     * Exports the Deadline task to a String to be saved.
     *
     * @return String representation of the Deadline task to be saved.
     */
    @Override
    public String export() {
        return String.format(DEADLINE_EXPORT_TEMPLATE, super.export(), this.by.format(parseFormatter), "", "");
    }

    /**
     * Updates the deadline of the task.
     *
     * @param newDeadline The new deadline of the task.
     * @throws DukeIllegalArgumentException If the deadline does not follow the format yyyy-MM-dd HH:mm.
     */
    public void updateDeadline(String newDeadline) throws DukeIllegalArgumentException {
        try {
            this.by = LocalDateTime.parse(newDeadline, parseFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_INVALID_DEADLINE_FORMAT);
        }
    }
}
