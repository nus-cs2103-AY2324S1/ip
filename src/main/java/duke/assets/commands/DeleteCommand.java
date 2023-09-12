package duke.assets.commands;

import duke.assets.storage.TaskList;

/**
 * Represents a command to delete a task from the task list
 */
public class DeleteCommand extends OperationOnListCommandAbstract {

    /**
     * Constructs a new DeleteCommand object with the given input command string
     *
     * @param input the input command string
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Completes the operation specified by the input command on the specified task list
     *
     * @param tasklist the task list to operate on
     */
    @Override
    protected void completeOperation(TaskList tasklist) {
        tasklist.deleteTaskAt(Integer.parseInt(input.split(" ")[1]) - 1);;
    }
}