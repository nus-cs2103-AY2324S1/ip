package alice.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import alice.exception.DukeException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    public static final String TASK_LABEL = "D";

    protected LocalDateTime by; // The deadline of the task in LocalDateTime format.

    /**
     * Constructs a deadline with the given description and deadline.
     *
     * @param description The description of the deadline.
     * @param by          The deadline of the deadline.
     * @throws DukeException If there are problems constructing the deadline.
     */
    public Deadline(String description, String by) throws DukeException {
        this(description, by, false);
    }

    /**
     * Constructs a deadline with the given description, deadline and status.
     *
     * @param description The description of the deadline.
     * @param by          The deadline of the deadline.
     * @param isDone      The status of the deadline.
     * @throws DukeException If there are problems constructing the deadline.
     */
    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super(description, isDone);
        this.by = LocalDateTime.parse(by);
    }

    /**
     * Constructs a deadline with the given argument.
     *
     * @param argument The argument from the command.
     * @throws DukeException If there are problems constructing the deadline.
     */
    public static Deadline fromArgument(String argument) throws DukeException {
        try {
            String[] inputs = argument.split(" /", 2);
            String description = inputs[0];
            String by = inputs[1].split(" ", 2)[1];
            return new Deadline(description, by);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The deadline of a deadline cannot be empty.");
        } catch (DateTimeParseException e) {
            throw new DukeException(Task.DATE_TIME_FORMAT_PARSING_ERROR_MESSAGE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + TASK_LABEL + "]" + super.toString() + " (by: " + Task.getDate(this.by) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return TASK_LABEL + " | " + super.toFileString() + " | " + this.by;
    }
}
