package command;

import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;


/**
 * A general interface that represents a command, which executes,
 * and returns a boolean that represents if the program is shutting down.
 * if the execution causes the program to shut down. Also, able to throw a FailureInExecuteException if the execution
 * happens to fail for one reason or another.
 */
public interface Executable {
    boolean execute(TaskList list, UserInterface ui) throws FailureInExecuteException;

    // TODO: implement a help() function that makes the HelpCommand's job easier
    // void help();
}
