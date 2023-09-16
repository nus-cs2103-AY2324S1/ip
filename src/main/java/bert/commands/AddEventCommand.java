package bert.commands;

import bert.tasks.Event;

import java.time.LocalDate;

/**
 * Represents a command that adds an event task.
 */
public class AddEventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";

    /**
     * Constructs an AddEventCommand instance containing the event task to be added.
     *
     * @param description the description of the event task
     * @param start the starting time of the event
     * @param end the ending time of the event
     */
    public AddEventCommand(String description, LocalDate start, LocalDate end) {
        super(new Event(description, start, end));
    }
}
