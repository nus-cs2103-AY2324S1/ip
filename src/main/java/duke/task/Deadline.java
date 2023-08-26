package duke.task;
import duke.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task in the Duke program.
 * Each deadline has a description and a specific due time.
 */
public class Deadline extends Task {

    /** Due date/time for the deadline. */
    public LocalDate d_time;

    /** Formatter to display the due time in the desired format. */
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a new Deadline task.
     *
     * @param description Description of the deadline.
     * @param d_time Due time for the deadline in "yyyy-MM-dd" format.
     * @throws DukeException If the due time is not in the expected format.
     */
    public Deadline(String description, String d_time) throws DukeException {
        super(description, TaskType.DEADLINE);
        try {
            this.d_time = LocalDate.parse(d_time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format for input: " + d_time);
        }
    }

    /**
     * Constructs a new Deadline task with an isDone status.
     *
     * @param description Description of the deadline.
     * @param d_time Due time for the deadline in "yyyy-MM-dd" format.
     * @param isDone Boolean indicating if the task has been completed.
     * @throws DukeException If the due time is not in the expected format.
     */
    public Deadline(String description, String d_time, boolean isDone) throws DukeException {
        super(description, TaskType.DEADLINE, isDone);
        try {
            this.d_time = LocalDate.parse(d_time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format for input: " + d_time);
        }
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return Formatted string representing the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + d_time.format(OUTPUT_FORMATTER) + ")";
    }
}
