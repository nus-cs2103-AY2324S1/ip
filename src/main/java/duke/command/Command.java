package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract class representing an input command.
 */
public abstract class Command {
    /**
     * Returns whether the duke.command.Command instance is a duke.command.ExitCommand instance.
     *
     * @return A boolean representing if the duke.command.Command instance is a duke.command.ExitCommand instance.
     */
    public abstract boolean isExit();

    /**
     * Executes the required actions for the specific duke.command.Command instance
     * and returns the response.
     *
     * @param tasks   The existing duke.TaskList instance containing all the tasks.
     * @param ui      The existing duke.Ui instance.
     * @param storage The existing duke.Storage instance.
     * @return        The response of the command.
     * @throws DukeException if anything goes wrong in the execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
