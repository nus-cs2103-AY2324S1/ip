package command;

import java.io.IOException;

import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;

public class DeleteCommand implements Commandable {
    private int index;
    public void setDelete(int index) {
        this.index = index;
    }
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
