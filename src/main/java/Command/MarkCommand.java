package Command;
import DukeException.FailureInExecuteException;
import Duke.TaskList;
import Duke.UserInterface;
import java.io.IOException;

public class MarkCommand implements Commandable {
    boolean isToBeMarked;
    int targetIndex;
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