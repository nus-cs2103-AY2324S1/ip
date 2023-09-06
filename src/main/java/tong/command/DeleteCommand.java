package tong.command;

import tong.command.Command;
import tong.command.CommandResult;
import tong.exception.TaskNotFoundException;
import tong.task.*;

/**
 * Deletes a tong.task.Task according to its index from the tong.TaskList.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    /** The Index representing the task to be deleted which is visible to the users (starting from 1) */
    private int targetVisibleIndex = 0;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number from your tasklist.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1\n";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "tong.task.Task deleted: %1$s";

    public DeleteCommand(int targetVisibleIndex) {
        this.targetVisibleIndex = targetVisibleIndex;
    }

    @Override
    public CommandResult execute() {
        try {
            Task toDelete = taskList.getTask(targetVisibleIndex);
            taskList.deleteTask(toDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, toDelete));
        } catch (TaskNotFoundException e) {
            return new CommandResult("You don't seem to have so many tasks. "
                    + "Which task do you want to delete?");
        }
    }
}
