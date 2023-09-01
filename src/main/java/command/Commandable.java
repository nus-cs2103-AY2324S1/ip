package command;

import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;


public interface Commandable {
    boolean execute(TaskList list, UserInterface ui) throws FailureInExecuteException;
}
