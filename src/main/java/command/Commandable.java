package command;

import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;


/**
 * A general interface that represents a command, which is able to be executed and returns a boolean that represents
 * if the execution causes the program to shut down. Also able to throw a FailureInExecuteException if the execution
 * happens to fail for one reason or another.
 */
public interface Commandable {
    boolean execute(TaskList list, UserInterface ui) throws FailureInExecuteException;
}
