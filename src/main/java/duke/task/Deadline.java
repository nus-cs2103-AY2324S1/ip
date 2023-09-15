package duke.task;

import java.time.LocalDateTime;
import java.util.Map;

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

    @Override
    protected boolean before(LocalDateTime before) {
        return by.isBefore(before);
    }

    @Override
    public void edit(Map<String, String> arguments) {
        if (!arguments.containsKey("by")) {
            throw new DukeException("deadline requires a by argument");
        }
        if (arguments.containsKey("by")) {
            by = DatetimeHelper.parseField(arguments.get("by"), "by", "deadline");
        }
    }
}
