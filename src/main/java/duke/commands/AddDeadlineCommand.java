package duke.commands;

import duke.data.task.Task;

/**
 * Represents a command for adding deadlines.
 */
public class AddDeadlineCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "deadline";
    public AddDeadlineCommand(Task task) {
        super(task);
    }
}