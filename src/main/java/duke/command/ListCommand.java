package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to list the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the "list" command, and lists all the tasks.
     *
     * @param tasks   The task list that the command may operate on.
     * @param ui      The user interface to interact with the user or display messages.
     * @param storage The storage handler to manage data persistence.
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showList();
        ui.showMessage(tasks.toString());
    }
}
