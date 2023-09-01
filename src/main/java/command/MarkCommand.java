package command;

import java.io.IOException;

import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;

public class MarkCommand implements Commandable {
    private boolean isToBeMarked;
    private int targetIndex;

    public MarkCommand(boolean isToBeMarked) {
        this.isToBeMarked = isToBeMarked;
    }

    public void setMark(int targetIndex) {
        this.targetIndex = targetIndex;
    }

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
