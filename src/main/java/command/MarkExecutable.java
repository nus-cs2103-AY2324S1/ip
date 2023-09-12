package command;

import java.io.IOException;

import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;

/**
 * Represents the marking or unmarking of a certain task.
 */
public class MarkExecutable implements Executable {
    private final boolean isToBeMarked;
    private int targetIndex;

    public MarkExecutable(boolean isToBeMarked) {
        this.isToBeMarked = isToBeMarked;
    }

    /**
     * Sets the index in the list that we plan to set the mark of.
     * @param targetIndex the index in the list that is to be marked.
     */
    public void setMarkTarget(int targetIndex) {
        assert targetIndex > 0; // negative indexes are never valid and should not have been passed!
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the marking/unmarking of a given task.
     * @param list the list that contains the task.
     * @param ui the interface that prints out any necessary outputs.
     * @return false, since the execution does not end the bot.
     * @throws FailureInExecuteException when there is a failure in marking the task, or the task does not exist.
     */
    @Override
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
