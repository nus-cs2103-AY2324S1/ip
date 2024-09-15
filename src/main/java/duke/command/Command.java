package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * The abstract class of all the different commands given by the users.
 */
public abstract class Command {
    /**
     * Executes the command. Specific implementation depends on the type on command.
     *
     * @param tasks   The list of tasks stored in TaskList object.
     * @param ui      The Ui object to interact with user.
     * @param storage The object used to store the tasks in case of changes
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
