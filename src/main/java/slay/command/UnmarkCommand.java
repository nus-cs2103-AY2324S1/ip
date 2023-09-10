package slay.command;

import slay.exception.TaskNotFoundException;
import slay.task.*;
import slay.exception.DuplicatedMarkException;

/**
 * Marks a tong.task.Task as not done.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    /** The Index representing the task to be deleted which is visible to the users (starting from 1) */
    private int targetVisibleIndex = 0;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number as not done.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1\n";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "It's okay. Work on it now: %1$s";
    public static final String MESSAGE_UNMARK_TASK_FAILURE = "Oops! You have never finished the task before";

    public UnmarkCommand(int targetVisibleIndex) {
        this.targetVisibleIndex = targetVisibleIndex;
    }

    @Override
    public CommandResult execute() {
        try {
            Task toUnmark = taskList.getTask(targetVisibleIndex);
            taskList.unmarkTask(toUnmark);
            return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, toUnmark));
        } catch (TaskNotFoundException e) {
            return new CommandResult("You don't seem to have so many tasks. " +
                    "Which task have you not done?");
        } catch (DuplicatedMarkException e) {
            return new CommandResult(MESSAGE_UNMARK_TASK_FAILURE);
        }
    }
}
