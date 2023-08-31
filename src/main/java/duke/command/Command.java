package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Command class is an abstract base class for different types of commands in the Duke application.
 */
public abstract class Command {

    /**
     * Executes the command, performing specific actions based on the type of command.
     *
     * @param tasks The TaskList containing tasks to be manipulated.
     * @param ui The Ui instance for displaying messages.
     * @param storage The Storage instance for loading and saving tasks.
     * @throws DukeException If there is an error while executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an ExitCommand, false otherwise.
     */
    public boolean isExit() {
        return (this instanceof ExitCommand);
    }
}