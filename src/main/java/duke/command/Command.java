package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Abstract class for Duke commands.
 */
public abstract class Command {
    /**
     * Executes the user command.
     * @param taskList TaskList of the session
     * @param ui Ui of the session
     * @param storage Storage of the session
     * @throws DukeException if errors occur
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns if app should quit after executing this command.
     * @return true if user inputs "bye", false otherwise
     */
    public abstract boolean isExit();
}
