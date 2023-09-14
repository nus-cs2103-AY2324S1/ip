package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.util.Formatter;

/**
 * Represents a deadline task, containing a description and a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Returns a new Deadline task containing the message and the deadline date.
     *
     * @param message The description for the deadline.
     * @param deadline The deadline, as a LocalDateTime.
     */
    public Deadline(String message, LocalDateTime deadline) {
        super(message);
        this.deadline = deadline;
    }

    /**
     * Updates a deadline task based on the specified update type and value.
     *
     * @param type The UpdateType to update the task with.
     * @param newValue The new value to update the task with.
     * @throws DukeException If the type and new value parameters are invalid.
     */
    @Override
    public void update(UpdateType type, String newValue) throws DukeException {
        switch (type) {
        case DESCRIPTION:
            message = newValue;
            break;
        case DATE1:
            try {
                deadline = LocalDateTime.parse(newValue);
            } catch (DateTimeParseException e) {
                throw new DukeException("Cannot parse date/time of new deadline!");
            }
            break;
        case DATE2:
            throw new DukeException("Cannot update: Deadlines have only one deadline date!");
            // exception thrown, no break statement needed
        default:
            throw new DukeException("Invalid update type!");
            // exception thrown, no break statement needed
        }
    }

    /**
     * Returns a String containing information within the Deadline task, formatted to be saved.
     *
     * @return The deadline, formatted as a String to be saved in the save file.
     */
    @Override
    public String toSaveFormatString() {
        return "D | " + getStatusNumber() + " | " + message + " | " + deadline;
    }

    /**
     * Returns a String representation of the Deadline task, formatted for output in the application.
     *
     * @return The deadline, formatted as a String for output in the application.
     */
    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + message
                + " (by: " + Formatter.formatDateTime(deadline) + ")";
    }

    @Override
    public Deadline clone() {
        return new Deadline(message, deadline);
    }
}
