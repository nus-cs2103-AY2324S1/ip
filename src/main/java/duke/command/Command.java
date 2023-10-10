package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command that can be executed by Duke.
 */
public abstract class Command {

    /**
     * Executes the command with the given TaskList, Ui, and Storage.
     *
     * @param tasks   The TaskList containing tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for loading and saving tasks.
     * @throws DukeException If there is an error while executing the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, otherwise false.
     */
    public abstract boolean isExit();
}

