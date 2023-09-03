package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * The Command class is an abstract class for commands.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The TaskList to be worked on.
     * @param ui The Ui to be worked on.
     * @param storage The Storage to be worked on.
     * @throws DukeException On input or file error.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks whether it is an Exit command.
     *
     * @return Returns whether it is an Exit command.
     */
    public abstract boolean isExit();
}
