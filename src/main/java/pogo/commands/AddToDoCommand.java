package pogo.commands;

import pogo.common.Messages;
import pogo.tasks.Task;
import pogo.tasks.ToDo;

/**
 * Represents a command to add a ToDo task.
 */
public class AddToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a ToDo task to the task list. "
            + "Parameters: DESCRIPTION"
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD
            + " Eat eggs";

    public final String description;

    /**
     * Creates an AddToDoCommand to add a ToDo task.
     *
     * @param description Description of the ToDo task.
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a ToDo task and adds it to the task list.
     *
     * @return CommandResult containing a success message for the task.
     */
    @Override
    public CommandResult execute() {
        Task todo = new ToDo(description);
        tasks.add(todo);
        return new CommandResult(String.format(Messages.ADD_TASK_SUCCESS, todo.getStatusMessage()));
    }

}
