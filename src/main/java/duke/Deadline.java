package duke;

import java.time.DateTimeException;
/**
 * This represents a Deadline which extends Task.
 */
public class Deadline extends Task {
    /** The date to complete the task by. */
    private DateTimeOptional deadline;
    /**
     * Parses a raw string containing description and date information into a deadline.
     * The raw string must be in the format "description /by deadline".
     * If the raw string is empty, it throws a DukeException.
     * If the raw string does not contain a deadline, it throws a DukeException.
     * If the deadline cannot be parsed into a DateTimeOptional object, it throws a DukeException.
     *
     * @param rawLine The raw string to be parsed into a Deadline object.
     * @return A Deadline object representing the parsed task.
     * @throws DukeException If the raw string is empty or does not contain a deadline or contains invalid deadline.
     */
    public static Deadline create(String rawLine) throws DukeException {
        if (rawLine.length() == 0) {
            throw new DukeException("Err: Empty Description");
        }
        String[] instructions = rawLine.split(" /by ");
        if (instructions.length != 2) {
            throw new DukeException("Err: No deadline given. Format - deadline <description> /by <deadline>");
        }
        try {
            DateTimeOptional deadline = DateTimeOptional.parseDateTime(instructions[1]);
            return new Deadline(instructions[0], deadline);
        } catch (DateTimeException e) {
            throw new DukeException.DukeDateTimeException(instructions[1]);
        }
    }
    /**
     * Constructs a deadline with the description and a DateTimeOptional object.
     *
     * @param item The description of the task.
     * @param deadline The deadline of the task as a DateTimeOptional object.
     */
    public Deadline(String item, DateTimeOptional deadline) {
        super(item);
        this.deadline = deadline;
    }
    /**
     * Gives a string representation of the task in file format.
     *
     * @return A string representation of the task in file format.
     */
    @Override
    public String fileString() {
        return String.format(
                "deadline %d %s /by %s",
                super.isDone ? 1 : 0,
                super.description,
                this.deadline
        );
    }
    /**
     * Gives a string representation of the task in display format.
     *
     * @return A string representation of the task in display format.
     */
    @Override
    public String toString() {
        return String.format(
                "[D][%s] %s (by %s)",
                super.getStatusIcon(),
                super.description,
                this.deadline.displayText()
        );
    }
}