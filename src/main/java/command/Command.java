package command;

import storage.FileHandler;
import storage.TaskList;

import duke.Ui;

/**
 * Abstract class for few types of command that
 * can be executed in the application.
 */
public abstract class Command {

    private boolean isExit = false;

    /**
     * Executes the command with the provided task list, user interface, and file handler.
     *
     * @param t  The task list.
     * @param ui The user interface.
     * @param f  The file handler for storing tasks.
     * @return   A string representation of message.
     */
    public abstract String execute(TaskList t, Ui ui, FileHandler f);

    /**
     * Checks whether the command is an exit command.
     */
    public abstract boolean isExit();

}
