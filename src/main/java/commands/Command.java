package commands;

import components.DukeException;
import components.Storage;
import components.Ui;
import tasks.TaskList;

public abstract class Command {
    /**
     * Constructor for Command.
     */
    public Command() {

    }

    /**
     * Executes the command.
     * @param list
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if the command is an exit command.
     * @return boolean isExit
     */
    public boolean isExit() {
        return false;
    }
}
