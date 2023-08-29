package pogo.commands;

import pogo.common.Messages;
import pogo.tasks.Event;
import pogo.tasks.Task;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event task to the task list. "
            + "Parameters: DESCRIPTION /from DATE TIME /to DATE TIME"
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD
            + "Blockchain Conference /from 2023-09-18 0800 /to 2023-09-19 1800";

    private final String description;
    private final String from;
    private final String to;

    /**
     * Creates an AddEventCommand to add an event task.
     *
     * @param description Description of the event task.
     * @param from        Start time of the event.
     * @param to          End time of the event.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an event task and adds it to the task list.
     *
     * @return CommandResult containing a success message for the task.
     */
    @Override
    public CommandResult execute() {
        Task event = new Event(description, from, to);
        tasks.add(event);
        return new CommandResult(String.format(Messages.ADD_TASK_SUCCESS, event.getStatusMessage()));
    }
}
