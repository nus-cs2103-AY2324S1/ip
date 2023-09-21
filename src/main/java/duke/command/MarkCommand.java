package duke.command;

import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Represents a command to mark the specified task as done.
 */
public class MarkCommand extends ModifyTaskCommand {

    public static final String COMMAND_WORD = "mark";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Marks the given task as done.";
    private static final String COMMAND_RESPONSE = "Nice! I've marked this task as done:\n\t";

    public MarkCommand(String taskIndex) {
        super(taskIndex);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.mark(taskIndex);
        storage.save(tasks);
        return new CommandResult(COMMAND_RESPONSE + task);
    }

}
