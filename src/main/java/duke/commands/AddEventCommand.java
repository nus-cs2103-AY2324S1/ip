package duke.commands;

import duke.data.task.Task;

/**
 * Represents a command for adding events.
 */
public class AddEventCommand extends AddTaskCommand {
    public static final String COMMAND_WORD = "event";

    public AddEventCommand(Task task) {
        super(task);
    }
}
