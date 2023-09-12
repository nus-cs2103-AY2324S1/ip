package duke.assets.commands;

import duke.assets.storage.TaskList;

/**
 * Represents a command to unmark a task as done in the task list
 */
public class UnmarkCommand extends OperationOnListCommandAbstract {

    /**
     * Constructs a new UnmarkCommand object with the given input command string
     *
     * @param input the input command string
     */
    public UnmarkCommand(String input) {
        super(input);
    }

    /**
     * Completes the operation specified by the input command on the specified task list
     *
     * @param tasklist the task list to operate on
     */
    @Override
    protected void completeOperation(TaskList tasklist) {
        tasklist.unmarkTaskAt(Integer.parseInt(input.split(" ")[1]) - 1);
    }
}
