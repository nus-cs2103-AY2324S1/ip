package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the IncorrectCommand Class.
 * Responsible for handling incorrect operations.
 *
 * @author Shishir
 */
public class IncorrectCommand extends Command {

    /** Description of incorrect operation. */
    private String message;

    /**
     * Constructs the IncorrectCommand Class.
     * @param message Description of the incorrect operation.
     */
    public IncorrectCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the required command.
     * @param tasks List of all the tasks.
     * @param ui Ui for interacting with the user.
     * @param storage Storage of the tasks.
     * @throws DukeException Throws DukeException on invalid input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(this.message);
    }

    /**
     * Returns the exit status of the command.
     * @return Exit status of the command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
