package tong.command;

import tong.command.Command;
import tong.command.CommandResult;
import tong.exception.DuplicatedMarkException;
import tong.exception.TaskNotFoundException;
import tong.task.*;

/**
 * Marks a tong.task.Task as done.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    /** The Index representing the task to be deleted which is visible to the users (starting from 1) */
    private int targetVisibleIndex = 0;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number as done.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1\n";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Good Job! You have finished the task: %1$s";
    public static final String MESSAGE_MARK_TASK_FAILURE = "Oops! You have already done the task.";

    public MarkCommand(int targetVisibleIndex) {
        this.targetVisibleIndex = targetVisibleIndex;
    }

    @Override
    public CommandResult execute() {
        try {
            Task toMark = taskList.getTask(targetVisibleIndex);
            taskList.markTask(toMark);
            return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, toMark));
        }  catch (TaskNotFoundException e) {
            return new CommandResult("You don't seem to have so many tasks. " +
                    "Which task have you not done?");
        } catch (DuplicatedMarkException e) {
            return new CommandResult(MESSAGE_MARK_TASK_FAILURE);
        }
    }
}
