package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
/**
 * Represents an abstract command in the Duke application.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to update the tasks in the file.
     * @throws DukeException If there's an error while parsing the user input or updating the storage.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    /**
     * Indicates whether this command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}
