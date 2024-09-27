package command;

import duke.Ui;
import storage.FileHandler;
import storage.TaskList;

/**
 * Abstract class for few types of command that
 * can be executed in the application.
 */
public abstract class Command {

    /**
     * Executes the command with the provided task list, user interface, and file handler.
     *
     * @param t  The task list.
     * @param ui The user interface.
     * @param f  The file handler for storing tasks.
     * @return   A string representation of message.
     */
    public abstract String execute(TaskList t, Ui ui, FileHandler f);

}
