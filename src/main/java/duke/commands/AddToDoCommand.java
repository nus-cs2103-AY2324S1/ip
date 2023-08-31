package duke.commands;

import duke.data.task.Task;

/**
 * Represents a command for adding todos.
 */
public class AddToDoCommand extends AddTaskCommand{

    public static final String COMMAND_WORD = "todo";

    public AddToDoCommand(Task task) {
        super(task);
    }
}