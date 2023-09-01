package command;

import java.io.IOException;

import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;

/**
 * Represents a deletion command of a particular task.
 */
public class DeleteCommand implements Commandable {
    private int index;
    public void setDelete(int index) {
        this.index = index;
    }

    /**
     * Executes the deletion of the task from the list provided, and provides outputs for the interface to print.
     * @param list the list that contains the task.
     * @param ui the interface that prints out any necessary outputs.
     * @return false, since the execution does not end the bot.
     * @throws FailureInExecuteException when there is a failure in writing to the list or its associated file,
     *     and when the index does not exist in the list.
     */
    public boolean execute(TaskList list, UserInterface ui) throws FailureInExecuteException {
        try {
            if (!list.removeTask(index)) {
                throw new FailureInExecuteException("Out of list index");
            }
        } catch (IOException e) {
            throw new FailureInExecuteException(e.getMessage());
        }
        return false;
    }
}
