package command;

import ui.Ui;

import storage.TaskList;
import storage.FileHandler;

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
     */
    public abstract void execute(TaskList t, Ui ui, FileHandler f);

    /**
     * Check whether the command is an exit command.
     */
    public abstract boolean isExit();

}
