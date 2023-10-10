package socrates.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import socrates.data.exception.InvalidDateException;
import socrates.data.exception.SocratesException;
import socrates.data.task.Deadline;

/**
 * Represents a command to add a new deadline to the list of tasks.
 */
public class AddDeadlineCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "deadline";

    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Adds a new deadline.";

    /**
     * Returns an instance of {@code AddDeadlineCommand} with the given description, by dates.
     * If there is an error in parsing the date, throws a {@code SocratesException}.
     *
     * @param description The description of the deadline.
     * @param by The due date of the event in \'yyyy-mm-dd\' format.
     * @throws SocratesException If there is an error in parsing the by field as a date.
     */
    public AddDeadlineCommand(String description, String by) throws SocratesException {
        try {
            this.toAdd = new Deadline(description, LocalDate.parse(by));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

}
