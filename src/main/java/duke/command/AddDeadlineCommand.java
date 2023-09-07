package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.data.exception.DukeException;
import duke.data.exception.InvalidDateException;
import duke.data.task.Deadline;

/**
 * Represents a command to add a new deadline to the list of tasks.
 */
public class AddDeadlineCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "deadline";

    /**
     * Returns an instance of {@code AddDeadlineCommand} with the given description, by dates.
     * If there is an error in parsing the date, throws a {@code DukeException}.
     *
     * @param description The description of the deadline.
     * @param by The due date of the event in \'yyyy-mm-dd\' format.
     * @throws DukeException If there is an error in parsing the by field as a date.
     */
    public AddDeadlineCommand(String description, String by) throws DukeException {
        try {
            this.toAdd = new Deadline(description, LocalDate.parse(by));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }
}
