package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the Command for the Duke application
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public abstract class Command {

    /**
     * Check if it is an ExitCommand
     *
     * @return a boolean that represent whether this is an ExitCommand or not.
     */
    public abstract boolean isExit();

    /**
     * Executes the Command.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui that used as user interface.
     * @param storage The Storage that used to store, read and write data.
     * @throws DukeException If there are an invalid Input.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
