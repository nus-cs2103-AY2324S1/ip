package commands;

import components.DukeException;
import components.Storage;
import components.Ui;
import tasks.Task;
import tasks.TaskList;

/**
 * Represents a command.
 */
public abstract class Command {
    /**
     * Contains the newly created Task object that has not been added to list.
     */
    protected static Task tempTask;
    /**
     * Constructor for Command.
     */
    public Command() {
        // Assuming that whenever a Command object is created, it should not be null
        assert this != null : "Command object should not be null";
    }

    /**
     * Executes the command.
     * @param list
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract String execute(TaskList list, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if the command is an exit command.
     * @return boolean isExit
     */
    public boolean isExit() {
        return false;
    }
}
