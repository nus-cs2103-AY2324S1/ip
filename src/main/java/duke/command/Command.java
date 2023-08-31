package duke.command;
import duke.exception.DukeException;
import duke.main.TaskList;
import duke.main.Storage;
import duke.main.Ui;

/**
 * Represents a base class for different commands for Duke.
 */
public abstract class Command {

    /**
     * Executes the command with the given parameters.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving tasks to a file.
     * @throws DukeException If there's an error during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
