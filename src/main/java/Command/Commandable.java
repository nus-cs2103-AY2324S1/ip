package Command;
import DukeException.FailureInExecuteException;
import Duke.UserInterface;
import Duke.TaskList;


public interface Commandable {
    boolean execute(TaskList list, UserInterface ui) throws FailureInExecuteException;
}
