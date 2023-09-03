package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an abstract command, the basis for specific command implementation
 * like ByeCommand etc.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList List of Task objects.
     * @param ui UI that the user interact with.
     * @param storage Storage to handle data to and from an external file.
     * @throws DukeException If any error occurs.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public abstract String executeGui(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    /**
     * Checks whether the command is to exit the bot ("bye").
     *
     * @return Whether the command is "bye".
     */
    public boolean isExit() {
        return false;
    }
}
