package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represent commands for the Chat bot.
 *
 * @author Armando Jovan Kusuma
 */
public abstract class Command {


    /**
     * Executes the command.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The UI which functions as the user interface of the Chat bot.
     * @param storage The storage file to store the list of tasks.
     * @throws DukeException If the command execution fails.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
