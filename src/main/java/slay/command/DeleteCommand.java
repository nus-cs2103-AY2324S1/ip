package slay.command;

import slay.Message;
import slay.exception.InvalidTaskIndexException;
import slay.task.*;

/**
 * Deletes a tong.task.Task according to its index from the tong.TaskList.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number from your tasklist.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1\n";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Ok, I have deleted the task: %1$s";

    /** The Index representing the task to be deleted which is visible to the users (starting from 1) */
    private int targetVisibleIndex = 0;

    public DeleteCommand(int targetVisibleIndex) {
        this.targetVisibleIndex = targetVisibleIndex;
    }

    @Override
    public CommandResult execute() {
        try {
            Task toDelete = taskList.getTask(targetVisibleIndex);
            taskList.deleteTask(toDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, toDelete));
        } catch (InvalidTaskIndexException e) {
            return new CommandResult(Message.MESSAGE_INVALID_TASK_INDEX);
        }
    }
}
