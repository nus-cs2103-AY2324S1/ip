package monke.commands;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.Ui;

/**
 * The Command class represents an abstract command that can be executed.
 * Subclasses of Command define specific actions to be performed.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param ui The user interface.
     * @param storage The storage to read and write data.
     * @param tasks The list of tasks.
     * @throws MonkeException If there issues executing the command.
     */
    public abstract void execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException;

    /**
     * Indicates whether the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
