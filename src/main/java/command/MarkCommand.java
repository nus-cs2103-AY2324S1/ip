package command;

import java.io.IOException;

import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;

/**
 * Represents the marking or unmarking of a certain task.
 */
public class MarkCommand implements Commandable {
    private boolean isToBeMarked;
    private int targetIndex;

    public MarkCommand(boolean isToBeMarked) {
        this.isToBeMarked = isToBeMarked;
    }

    public void setMark(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the marking/unmarking of a given task.
     * @param list the list that contains the task.
     * @param ui the interface that prints out any necessary outputs.
     * @return false, since the execution does not end the bot.
     * @throws FailureInExecuteException when there is a failure in marking the task, or the task does not exist.
     */
    public boolean execute(TaskList list, UserInterface ui) throws FailureInExecuteException {
        try {
            if (!list.setMark(targetIndex, isToBeMarked)) {
                throw new FailureInExecuteException("Out of list index");
            }
        } catch (IOException e) {
            throw new FailureInExecuteException(e.getMessage());
        }
        return false;
    }
}
