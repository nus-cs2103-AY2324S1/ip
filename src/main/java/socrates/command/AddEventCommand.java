package socrates.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import socrates.data.exception.InvalidDateException;
import socrates.data.exception.SocratesException;
import socrates.data.task.Event;

/**
 * Represents a command to add a new event to the list of tasks.
 */
public class AddEventCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Adds a new event.";

    /**
     * Returns an instance of {@code AddEventCommand} with the given description, from and to dates.
     * If there is an error in parsing the date, throws a {@code SocratesException}.
     *
     * @param description The description of the event.
     * @param from The start date of the event in \'yyyy-mm-dd\' format.
     * @param to The end date of the event in \'yyyy-mm-dd\' format.
     * @throws SocratesException If there is an error in parsing from and to as dates.
     */
    public AddEventCommand(String description, String from, String to) throws SocratesException {
        try {
            this.toAdd = new Event(description, LocalDate.parse(from), LocalDate.parse(to));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

}
