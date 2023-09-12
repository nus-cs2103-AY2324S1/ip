package duke.task;

import java.time.LocalDateTime;
import java.util.Optional;

import duke.exception.DukeException;
import duke.exception.InvalidDatetimeFormatException;
import duke.helper.DatetimeHelper;

/**
 * Represents a deadline task, which has an additional by datetime in addition
 * to task arguments.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Returns a Deadline
     *
     * @param description the task description
     * @param isMarked    if the task is marked
     * @param by          the by datetime
     * @return the constructed Deadline
     * @throws DukeException                  if by is empty
     * @throws InvalidDatetimeFormatException if format of by is not valid
     */
    public Deadline(String description, boolean isMarked, String by) {
        super(description, isMarked, 'D');
        this.by = DatetimeHelper.parseField(by, "by", "deadline");

    }

    /**
     * Returns a string representation of Deadline. Adds by argument to the Task
     * string.
     *
     * @return a string representation of Deadline
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), DatetimeHelper.format(by));
    }

    /**
     * Returns a string representation of the command used to construct Deadline.
     *
     * @return a string of the command used.
     */
    @Override
    public String toCommand() {
        return String.format("%s /by %s", super.toCommand(), DatetimeHelper.commandFormat(by));
    }

    /**
     * Returns if the Deadline should be filtered
     *
     * @param before the comparison date
     * @return returns if Deadline is by before
     */
    @Override
    public boolean filter(Optional<LocalDateTime> before) {
        return before.filter((datetime) -> datetime.isBefore(by)).isEmpty(); // if is empty return true
    }
}
