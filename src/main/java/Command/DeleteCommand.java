package Command;
import DukeException.FailureInExecuteException;
import Duke.TaskList;
import Duke.UserInterface;
import java.io.IOException;

public class DeleteCommand implements Commandable {
    int index;
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