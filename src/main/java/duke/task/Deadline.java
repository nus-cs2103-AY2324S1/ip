package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;


/**
 * Represents a Deadline task in the Duke program.
 * Each deadline has a description and a specific due time.
 */
public class Deadline extends Task {
    /** Formatter to display the due time in the desired format. */
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /** Due date/time for the deadline. */
    private LocalDate deadlineTime;


    /**
     * Constructs a new Deadline task.
     *
     * @param description Description of the deadline.
     * @param deadlineTime Due time for the deadline in "yyyy-MM-dd" format.
     * @throws DukeException If the due time is not in the expected format.
     */
    public Deadline(String description, String deadlineTime) throws DukeException {
        super(description, TaskType.DEADLINE);
        try {
            this.deadlineTime = LocalDate.parse(deadlineTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format for input: " + deadlineTime);
        }
    }

    /**
     * Constructs a new Deadline task with an isDone status.
     *
     * @param description Description of the deadline.
     * @param deadlineTime Due time for the deadline in "yyyy-MM-dd" format.
     * @param isDone Boolean indicating if the task has been completed.
     * @throws DukeException If the due time is not in the expected format.
     */
    public Deadline(String description, String deadlineTime, boolean isDone) throws DukeException {
        super(description, TaskType.DEADLINE, isDone);
        try {
            this.deadlineTime = LocalDate.parse(deadlineTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format for input: " + deadlineTime);
        }
    }

    public LocalDate getTime() {
        return this.deadlineTime;
    }
    /**
     * Returns the string representation of the deadline.
     *
     * @return Formatted string representing the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineTime.format(OUTPUT_FORMATTER) + ")";
    }
}
